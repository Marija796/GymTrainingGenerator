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
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGeneratorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()
        setupSpinners()
        binding.btnGenerate.setOnClickListener { generateWorkout() }
    }

    private fun setupSpinners() {
        val goals = resources.getStringArray(R.array.fitness_goals).toList()
        val levels = resources.getStringArray(R.array.fitness_levels).toList()
        val days = resources.getStringArray(R.array.training_days_options).toList()
        val muscles = resources.getStringArray(R.array.muscle_focus_options).toList()

        binding.spinnerGoal.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, goals)
        binding.spinnerLevel.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, levels)
        binding.spinnerDays.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, days)
        binding.spinnerMuscle.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, muscles)
    }

    private fun generateWorkout() {
        val goal = binding.spinnerGoal.selectedItem.toString()
        val level = binding.spinnerLevel.selectedItem.toString()
        val days = binding.spinnerDays.selectedItem.toString().toIntOrNull() ?: 3
        val muscle = binding.spinnerMuscle.selectedItem.toString()
        val uid = auth.currentUser?.uid ?: "anonymous"

        val exercises = getExercisesForPlan(goal, level, muscle)

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
                Toast.makeText(requireContext(), getString(R.string.workout_generated), Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_generatorFragment_to_workoutFragment)
            }
            .addOnFailureListener {
                Toast.makeText(requireContext(), getString(R.string.error_generating), Toast.LENGTH_SHORT).show()
            }
    }

    private fun getExercisesForPlan(goal: String, @Suppress("UNUSED_PARAMETER") level: String, muscle: String): List<String> {
        val base = when (muscle) {
            "Full Body" -> listOf("Barbell Squat", "Push-ups", "Pull-ups", "Deadlift", "Plank", "Burpees")
            "Upper Body" -> listOf("Push-ups", "Pull-ups", "Overhead Press", "Bicep Curls", "Tricep Dips", "Bench Press")
            "Lower Body" -> listOf("Barbell Squat", "Lunges", "Deadlift", "Leg Press", "Calf Raises", "Glute Bridges")
            "Push" -> listOf("Bench Press", "Overhead Press", "Push-ups", "Tricep Dips", "Lateral Raises", "Incline Dumbbell Press")
            "Pull" -> listOf("Pull-ups", "Barbell Rows", "Bicep Curls", "Face Pulls", "Lat Pulldown", "Cable Rows")
            "Legs" -> listOf("Barbell Squat", "Lunges", "Leg Press", "Romanian Deadlift", "Leg Curl", "Calf Raises")
            else -> listOf("Barbell Squat", "Push-ups", "Plank", "Burpees", "Jumping Jacks")
        }

        return when (goal) {
            "Lose Weight" -> base.takeLast(5) + listOf("Burpees")
            "Increase Strength" -> base.take(5)
            else -> base
        }.distinct().take(6)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
