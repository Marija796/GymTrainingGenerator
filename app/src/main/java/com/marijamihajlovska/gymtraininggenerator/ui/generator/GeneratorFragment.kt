package com.marijamihajlovska.gymtraininggenerator.ui.generator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.marijamihajlovska.gymtraininggenerator.R
import com.marijamihajlovska.gymtraininggenerator.data.local.AppDatabase
import com.marijamihajlovska.gymtraininggenerator.data.local.WorkoutEntity
import com.marijamihajlovska.gymtraininggenerator.databinding.FragmentGeneratorBinding
import kotlinx.coroutines.launch

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
        (binding.spinnerMuscle as AutoCompleteTextView).apply {
            setAdapter(ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, muscles))
            setText(muscles[0], false)
        }
    }

    private fun generateWorkout() {
        val goal = (binding.spinnerGoal as AutoCompleteTextView).text.toString()
        val level = (binding.spinnerLevel as AutoCompleteTextView).text.toString()
        val days = (binding.spinnerDays as AutoCompleteTextView).text.toString().toIntOrNull() ?: 3
        val muscle = (binding.spinnerMuscle as AutoCompleteTextView).text.toString()
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
            .addOnSuccessListener { docRef ->
                FirebaseAnalytics.getInstance(requireContext()).logEvent(
                    "generate_workout",
                    bundleOf("goal" to goal, "level" to level, "muscle" to muscle)
                )
                val entity = WorkoutEntity(
                    firestoreId = docRef.id,
                    userId = uid,
                    goal = goal,
                    level = level,
                    muscleFocus = muscle,
                    exercises = exercises.joinToString(","),
                    date = System.currentTimeMillis(),
                    completed = false
                )
                lifecycleScope.launch {
                    AppDatabase.getDatabase(requireContext()).workoutDao().insertWorkout(entity)
                }
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
