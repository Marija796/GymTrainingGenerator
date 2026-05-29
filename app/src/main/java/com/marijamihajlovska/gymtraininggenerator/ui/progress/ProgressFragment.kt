package com.marijamihajlovska.gymtraininggenerator.ui.progress

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.marijamihajlovska.gymtraininggenerator.R
import com.marijamihajlovska.gymtraininggenerator.databinding.FragmentProgressBinding

class ProgressFragment : Fragment() {
    private var _binding: FragmentProgressBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private val viewModel: ProgressViewModel by viewModels()
    private val stepDayAdapter = StepDayAdapter()

    private val activityRecognitionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) viewModel.startCounter()
        else showStepMessage(getString(R.string.step_permission_denied))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProgressBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        binding.rvStepHistory.layoutManager = LinearLayoutManager(requireContext())
        binding.rvStepHistory.adapter = stepDayAdapter
        binding.rvStepHistory.isNestedScrollingEnabled = false

        loadProgress()
        setupStepCounter()
        observeStepHistory()
    }

    private fun setupStepCounter() {
        if (!viewModel.isAvailable) {
            showStepMessage(getString(R.string.sensor_unavailable))
            return
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val permission = Manifest.permission.ACTIVITY_RECOGNITION
            if (ContextCompat.checkSelfPermission(requireContext(), permission)
                == PackageManager.PERMISSION_GRANTED) {
                startStepCounter()
            } else {
                activityRecognitionLauncher.launch(permission)
            }
        } else {
            startStepCounter()
        }
    }

    private fun startStepCounter() {
        viewModel.startCounter()
        viewModel.todaySteps.observe(viewLifecycleOwner) { steps ->
            binding.tvSteps.text = steps.toString()
        }
    }

    private fun showStepMessage(message: String) {
        viewModel.setCounterUnavailable()
        binding.tvSteps.visibility = View.GONE
        binding.tvStepsUnavailable.visibility = View.VISIBLE
        binding.tvStepsUnavailable.text = message
    }

    private fun observeStepHistory() {
        viewModel.stepHistory.observe(viewLifecycleOwner) { records ->
            stepDayAdapter.submitList(records)
            if (records.isEmpty()) {
                binding.rvStepHistory.visibility = View.GONE
                binding.tvStepHistoryEmpty.visibility = View.VISIBLE
            } else {
                binding.rvStepHistory.visibility = View.VISIBLE
                binding.tvStepHistoryEmpty.visibility = View.GONE
            }
        }
        viewModel.averageSteps.observe(viewLifecycleOwner) { avg ->
            if (avg > 0) {
                binding.tvAverageSteps.text = getString(R.string.avg_steps_per_day, avg)
                binding.tvAverageSteps.visibility = View.VISIBLE
            } else {
                binding.tvAverageSteps.visibility = View.GONE
            }
        }
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
                val favoriteGoal = goals.groupingBy { it }.eachCount()
                    .maxByOrNull { it.value }?.key ?: "-"

                binding.tvTotalWorkouts.text = total.toString()
                binding.tvCompletedWorkouts.text = completed.toString()
                binding.tvFavoriteGoal.text = favoriteGoal
            }
    }

    override fun onResume() {
        super.onResume()
        if (viewModel.counterActive) viewModel.startCounter()
    }

    override fun onPause() {
        super.onPause()
        viewModel.stopCounter()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
