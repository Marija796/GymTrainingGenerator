package com.marijamihajlovska.gymtraininggenerator.ui.dashboard

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.google.android.gms.location.LocationServices
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.marijamihajlovska.gymtraininggenerator.R
import com.marijamihajlovska.gymtraininggenerator.data.local.AppDatabase
import com.marijamihajlovska.gymtraininggenerator.databinding.FragmentDashboardBinding
import kotlinx.coroutines.launch

class DashboardFragment : Fragment() {
    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth

    private val locationPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        if (permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true ||
            permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true) {
            openNearbyGyms()
        } else {
            Toast.makeText(requireContext(), getString(R.string.location_permission_denied), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()

        val user = auth.currentUser
        val name = user?.displayName ?: user?.email ?: getString(R.string.default_athlete_name)
        binding.tvWelcome.text = getString(R.string.welcome_user, name)

        binding.btnGenerate.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_generatorFragment)
        }
        binding.btnProgress.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_progressFragment)
        }
        binding.btnHistory.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_historyFragment)
        }
        binding.btnSettings.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_settingsFragment)
        }
        binding.btnProfile.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_profileFragment)
        }
        binding.btnLogout.setOnClickListener {
            val user = auth.currentUser
            if (user != null && user.isAnonymous) {
                val uid = user.uid
                // Firestore guest cleanup — fire and forget
                val firestoreDb = FirebaseFirestore.getInstance()
                firestoreDb.collection("workouts").whereEqualTo("userId", uid).get()
                    .addOnSuccessListener { docs ->
                        if (!docs.isEmpty) {
                            val batch = firestoreDb.batch()
                            docs.forEach { batch.delete(it.reference) }
                            batch.commit()
                        }
                    }
                // Room + SharedPrefs cleanup, then sign out
                lifecycleScope.launch {
                    val appDb = AppDatabase.getDatabase(requireContext())
                    appDb.workoutDao().deleteAllByUser(uid)
                    appDb.stepRecordDao().deleteAllByUser(uid)
                    requireContext()
                        .getSharedPreferences("step_prefs_$uid", android.content.Context.MODE_PRIVATE)
                        .edit().clear().apply()
                    auth.signOut()
                    if (isAdded) navigateToLogin()
                }
            } else {
                auth.signOut()
                navigateToLogin()
            }
        }
        binding.btnNearbyGyms?.setOnClickListener {
            checkLocationAndOpenGyms()
        }
    }

    private fun navigateToLogin() {
        findNavController().navigate(
            R.id.action_dashboardFragment_to_loginFragment,
            null,
            NavOptions.Builder().setPopUpTo(R.id.nav_graph, true).build()
        )
    }

    private fun checkLocationAndOpenGyms() {
        val fine = Manifest.permission.ACCESS_FINE_LOCATION
        val coarse = Manifest.permission.ACCESS_COARSE_LOCATION
        val hasFine = ContextCompat.checkSelfPermission(requireContext(), fine) == PackageManager.PERMISSION_GRANTED
        val hasCoarse = ContextCompat.checkSelfPermission(requireContext(), coarse) == PackageManager.PERMISSION_GRANTED

        if (hasFine || hasCoarse) {
            openNearbyGyms()
        } else {
            locationPermissionLauncher.launch(arrayOf(fine, coarse))
        }
    }

    private fun openNearbyGyms() {
        val fusedClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        try {
            fusedClient.lastLocation.addOnSuccessListener { location ->
                if (!isAdded) return@addOnSuccessListener
                val mapsIntent = if (location != null) {
                    Intent(Intent.ACTION_VIEW, Uri.parse("geo:${location.latitude},${location.longitude}?q=gyms+near+me"))
                        .apply { setPackage("com.google.android.apps.maps") }
                } else {
                    Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=gyms+near+me"))
                        .apply { setPackage("com.google.android.apps.maps") }
                }
                try {
                    startActivity(mapsIntent)
                } catch (e: Exception) {
                    val webUri = if (location != null) {
                        Uri.parse("https://www.google.com/maps/search/gyms/@${location.latitude},${location.longitude},14z")
                    } else {
                        Uri.parse("https://www.google.com/maps/search/gyms+near+me")
                    }
                    try {
                        startActivity(Intent(Intent.ACTION_VIEW, webUri))
                    } catch (e2: Exception) {
                        Toast.makeText(requireContext(), getString(R.string.maps_unavailable), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        } catch (e: SecurityException) {
            Toast.makeText(requireContext(), getString(R.string.location_permission_denied), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
