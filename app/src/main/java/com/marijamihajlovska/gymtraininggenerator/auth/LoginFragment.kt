package com.marijamihajlovska.gymtraininggenerator.auth

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.marijamihajlovska.gymtraininggenerator.R
import com.marijamihajlovska.gymtraininggenerator.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth
    private lateinit var analytics: FirebaseAnalytics
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var callbackManager: CallbackManager

    private val googleSignInLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                Toast.makeText(requireContext(), getString(R.string.google_sign_in_failed), Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        analytics = FirebaseAnalytics.getInstance(requireContext())

        if (auth.currentUser != null) {
            findNavController().navigate(R.id.action_loginFragment_to_dashboardFragment)
            return
        }

        setupGoogleSignIn()
        setupFacebookSignIn()
        setupEmailValidation()

        binding.btnLogin.setOnClickListener { handleEmailLogin() }
        binding.tvForgotPassword.setOnClickListener { handleForgotPassword() }
        binding.btnGoogleLogin.setOnClickListener {
            googleSignInClient.signOut().addOnCompleteListener {
                googleSignInLauncher.launch(googleSignInClient.signInIntent)
            }
        }
        binding.btnFacebookLogin.setOnClickListener {
            LoginManager.getInstance().logOut()
            LoginManager.getInstance().logInWithReadPermissions(
                requireActivity(), callbackManager, listOf("email", "public_profile")
            )
        }
        binding.btnAnonymousLogin.setOnClickListener { handleAnonymousLogin() }
        binding.tvRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    private fun setupEmailValidation() {
        binding.etEmail.addTextChangedListener { text ->
            val email = text.toString().trim()
            if (email.isNotEmpty() && !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.tilEmail.error = getString(R.string.invalid_email)
            } else {
                binding.tilEmail.error = null
            }
        }
    }

    private fun handleForgotPassword() {
        val email = binding.etEmail.text.toString().trim()
        if (email.isEmpty()) {
            binding.tilEmail.error = getString(R.string.enter_email_for_reset)
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.tilEmail.error = getString(R.string.invalid_email)
            return
        }
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(requireContext(), getString(R.string.reset_email_sent), Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(requireContext(), getString(R.string.reset_email_failed), Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun setupGoogleSignIn() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
    }

    private fun setupFacebookSignIn() {
        callbackManager = CallbackManager.Factory.create()
        LoginManager.getInstance().registerCallback(callbackManager,
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(result: LoginResult) {
                    firebaseAuthWithFacebook(result.accessToken.token)
                }
                override fun onCancel() {}
                override fun onError(error: FacebookException) {
                    Toast.makeText(requireContext(), getString(R.string.facebook_login_failed), Toast.LENGTH_SHORT).show()
                }
            })
    }

    private fun handleEmailLogin() {
        val email = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()
        var hasError = false

        if (email.isEmpty()) {
            binding.tilEmail.error = getString(R.string.fill_all_fields)
            hasError = true
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.tilEmail.error = getString(R.string.invalid_email)
            hasError = true
        } else {
            binding.tilEmail.error = null
        }

        if (password.isEmpty()) {
            binding.tilPassword.error = getString(R.string.fill_all_fields)
            hasError = true
        } else {
            binding.tilPassword.error = null
        }

        if (hasError) return

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    analytics.logEvent(FirebaseAnalytics.Event.LOGIN, bundleOf("method" to "email"))
                    navigateToDashboard()
                } else {
                    Toast.makeText(requireContext(), "${getString(R.string.login_failed)}: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun handleAnonymousLogin() {
        auth.signInAnonymously()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    analytics.logEvent(FirebaseAnalytics.Event.LOGIN, bundleOf("method" to "anonymous"))
                    navigateToDashboard()
                } else {
                    Toast.makeText(requireContext(), getString(R.string.anonymous_login_failed), Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    analytics.logEvent(FirebaseAnalytics.Event.LOGIN, bundleOf("method" to "google"))
                    navigateToDashboard()
                } else {
                    Toast.makeText(requireContext(), getString(R.string.auth_failed), Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun firebaseAuthWithFacebook(token: String) {
        val credential = FacebookAuthProvider.getCredential(token)
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    analytics.logEvent(FirebaseAnalytics.Event.LOGIN, bundleOf("method" to "facebook"))
                    navigateToDashboard()
                } else {
                    Toast.makeText(requireContext(), getString(R.string.auth_failed), Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun navigateToDashboard() {
        findNavController().navigate(R.id.action_loginFragment_to_dashboardFragment)
    }

    @Suppress("OVERRIDE_DEPRECATION")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
