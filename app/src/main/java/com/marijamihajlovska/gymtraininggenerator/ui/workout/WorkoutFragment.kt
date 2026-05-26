package com.marijamihajlovska.gymtraininggenerator.ui.workout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.marijamihajlovska.gymtraininggenerator.R
import com.marijamihajlovska.gymtraininggenerator.databinding.FragmentWorkoutBinding

class WorkoutFragment : Fragment() {
    private var _binding: FragmentWorkoutBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private var currentDocId: String? = null
    private var isCompleted = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWorkoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()
        binding.rvExercises.layoutManager = LinearLayoutManager(requireContext())

        loadLatestWorkout()

        binding.btnComplete.setOnClickListener { markWorkoutComplete() }
        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_workoutFragment_to_dashboardFragment)
        }
    }

    private fun loadLatestWorkout() {
        val uid = auth.currentUser?.uid ?: return
        db.collection("workouts")
            .whereEqualTo("userId", uid)
            .orderBy("date", Query.Direction.DESCENDING)
            .limit(1)
            .get()
            .addOnSuccessListener { documents ->
                if (!documents.isEmpty) {
                    val doc = documents.first()
                    currentDocId = doc.id
                    val goal = doc.getString("goal") ?: ""
                    val level = doc.getString("level") ?: ""
                    val muscle = doc.getString("muscleFocus") ?: ""
                    val exercises = doc.get("exercises") as? List<String> ?: emptyList()
                    isCompleted = doc.getBoolean("completed") ?: false

                    binding.tvPlanInfo.text = getString(R.string.plan_info, goal, level, muscle)
                    binding.rvExercises.adapter = ExerciseAdapter(exercises)
                    updateCompleteButton()
                }
            }
    }

    private fun markWorkoutComplete() {
        if (isCompleted) {
            Toast.makeText(requireContext(), getString(R.string.workout_already_completed), Toast.LENGTH_SHORT).show()
            return
        }
        val docId = currentDocId ?: return
        db.collection("workouts").document(docId)
            .update("completed", true)
            .addOnSuccessListener {
                isCompleted = true
                updateCompleteButton()
                Toast.makeText(requireContext(), getString(R.string.workout_completed), Toast.LENGTH_SHORT).show()
            }
    }

    private fun updateCompleteButton() {
        binding.btnComplete.isEnabled = !isCompleted
        binding.btnComplete.text = if (isCompleted)
            getString(R.string.workout_already_completed)
        else
            getString(R.string.mark_complete)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
