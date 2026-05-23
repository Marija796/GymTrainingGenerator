package com.marijamihajlovska.gymtraininggenerator.ui.progress

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.marijamihajlovska.gymtraininggenerator.databinding.FragmentProgressBinding

class ProgressFragment : Fragment() {
    private var _binding: FragmentProgressBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProgressBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()
        loadProgress()
    }

    private fun loadProgress() {
        val uid = auth.currentUser?.uid ?: return
        db.collection("workouts")
            .whereEqualTo("userId", uid)
            .get()
            .addOnSuccessListener { documents ->
                val total = documents.size()
                val completed = documents.count { it.getBoolean("completed") == true }
                val goals = documents.mapNotNull { it.getString("goal") }
                val favoriteGoal = goals.groupingBy { it }.eachCount().maxByOrNull { it.value }?.key ?: "-"

                binding.tvTotalWorkouts.text = total.toString()
                binding.tvCompletedWorkouts.text = completed.toString()
                binding.tvFavoriteGoal.text = favoriteGoal
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}