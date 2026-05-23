package com.marijamihajlovska.gymtraininggenerator.ui.generator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.marijamihajlovska.gymtraininggenerator.R
import com.marijamihajlovska.gymtraininggenerator.databinding.FragmentGeneratorBinding

class GeneratorFragment : Fragment() {
    private var _binding: FragmentGeneratorBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGeneratorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        setupSpinners()

        binding.btnGenerate.setOnClickListener {
            generateWorkout()
        }
    }

    private fun setupSpinners() {
        val goals = listOf("Lose Weight", "Build Muscle", "Stay Fit", "Increase Strength")
        val levels = listOf("Beginner", "Intermediate", "Advanced")
        val days = listOf("2", "3", "4", "5", "6")
        val muscles = listOf("Full Body", "Upper Body", "Lower Body", "Push", "Pull", "Legs")

        binding.spinnerGoal.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, goals)
        binding.spinnerLevel.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, levels)
        binding.spinnerDays.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, days)
        binding.spinnerMuscle.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, muscles)
    }

    private fun generateWorkout() {
        val goal = binding.spinnerGoal.selectedItem.toString()
        val level = binding.spinnerLevel.selectedItem.toString()
        val days = binding.spinnerDays.selectedItem.toString().toInt()
        val muscle = binding.spinnerMuscle.selectedItem.toString()

        val exercises = getExercisesForPlan(goal, level, muscle)
        val uid = auth.currentUser?.uid ?: "anonymous"

        val workoutPlan = hashMapOf(
            "userId" to uid,
            "goal" to goal,
            "level" to level,
            "days" to days,
            "muscleFocus" to muscle,
            "exercises" to exercises,
            "date" to System.currentTimeMillis(),
            "completed" to false
        )

        db.collection("workouts").add(workoutPlan)
            .addOnSuccessListener {
                Toast.makeText(requireContext(), "Workout plan generated!", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_generatorFragment_to_workoutFragment)
            }
            .addOnFailureListener {
                Toast.makeText(requireContext(), "Error generating workout", Toast.LENGTH_SHORT).show()
            }
    }

    private fun getExercisesForPlan(goal: String, level: String, muscle: String): List<String> {
        return when (muscle) {
            "Full Body" -> listOf("Squats", "Push-ups", "Pull-ups", "Deadlift", "Plank", "Burpees")
            "Upper Body" -> listOf("Push-ups", "Pull-ups", "Shoulder Press", "Bicep Curls", "Tricep Dips", "Bench Press")
            "Lower Body" -> listOf("Squats", "Lunges", "Deadlift", "Leg Press", "Calf Raises", "Glute Bridges")
            "Push" -> listOf("Bench Press", "Shoulder Press", "Push-ups", "Tricep Dips", "Lateral Raises")
            "Pull" -> listOf("Pull-ups", "Rows", "Bicep Curls", "Face Pulls", "Lat Pulldown")
            "Legs" -> listOf("Squats", "Lunges", "Leg Press", "Deadlift", "Leg Curls", "Calf Raises")
            else -> listOf("Squats", "Push-ups", "Plank", "Burpees", "Jumping Jacks")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}