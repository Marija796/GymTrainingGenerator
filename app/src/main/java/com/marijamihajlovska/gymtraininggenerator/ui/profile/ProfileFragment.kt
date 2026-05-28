package com.marijamihajlovska.gymtraininggenerator.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.marijamihajlovska.gymtraininggenerator.R
import com.marijamihajlovska.gymtraininggenerator.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        setupSpinners()
        loadProfile()

        binding.btnSaveProfile.setOnClickListener {
            saveProfile()
        }
    }

    private fun setupSpinners() {
        val goals = listOf("Lose Weight", "Build Muscle", "Stay Fit", "Increase Strength")
        val levels = listOf("Beginner", "Intermediate", "Advanced")
        val days = listOf("2", "3", "4", "5", "6")

        (binding.spinnerGoal as AutoCompleteTextView).apply {
            setAdapter(ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, goals))
            setText(goals[0], false)
        }
        (binding.spinnerLevel as AutoCompleteTextView).apply {
            setAdapter(ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, levels))
            setText(levels[0], false)
        }
        (binding.spinnerDays as AutoCompleteTextView).apply {
            setAdapter(ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, days))
            setText(days[1], false)
        }
    }

    private fun loadProfile() {
        val uid = auth.currentUser?.uid ?: return
        db.collection("users").document(uid).get()
            .addOnSuccessListener { doc ->
                if (doc.exists()) {
                    binding.etName.setText(doc.getString("name") ?: "")
                    binding.etAge.setText(doc.getLong("age")?.toString() ?: "")
                    binding.etHeight.setText(doc.getDouble("height")?.toString() ?: "")
                    binding.etWeight.setText(doc.getDouble("weight")?.toString() ?: "")
                }
            }
    }

    private fun saveProfile() {
        val uid = auth.currentUser?.uid ?: return
        val name = binding.etName.text.toString().trim()
        val age = binding.etAge.text.toString().trim()
        val height = binding.etHeight.text.toString().trim()
        val weight = binding.etWeight.text.toString().trim()
        val goal = (binding.spinnerGoal as AutoCompleteTextView).text.toString()
        val level = (binding.spinnerLevel as AutoCompleteTextView).text.toString()
        val days = (binding.spinnerDays as AutoCompleteTextView).text.toString().toIntOrNull() ?: 3

        if (name.isEmpty() || age.isEmpty() || height.isEmpty() || weight.isEmpty()) {
            Toast.makeText(requireContext(), getString(R.string.fill_all_fields), Toast.LENGTH_SHORT).show()
            return
        }

        val user = hashMapOf(
            "uid" to uid,
            "name" to name,
            "age" to age.toInt(),
            "height" to height.toFloat(),
            "weight" to weight.toFloat(),
            "fitnessGoal" to goal,
            "fitnessLevel" to level,
            "trainingDays" to days
        )

        db.collection("users").document(uid).set(user)
            .addOnSuccessListener {
                Toast.makeText(requireContext(), getString(R.string.profile_saved), Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(requireContext(), getString(R.string.error_saving_profile), Toast.LENGTH_SHORT).show()
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}