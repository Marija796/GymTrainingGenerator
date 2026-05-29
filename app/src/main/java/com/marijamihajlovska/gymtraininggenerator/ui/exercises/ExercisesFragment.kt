package com.marijamihajlovska.gymtraininggenerator.ui.exercises

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.chip.Chip
import com.marijamihajlovska.gymtraininggenerator.R
import com.marijamihajlovska.gymtraininggenerator.databinding.FragmentExercisesBinding
import com.marijamihajlovska.gymtraininggenerator.model.ExerciseCategory
import kotlinx.coroutines.launch

class ExercisesFragment : Fragment() {

    private var _binding: FragmentExercisesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ExerciseViewModel by viewModels()
    private val adapter = ExercisesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExercisesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupCategoryChips()
        setupSearch()
        observeExercises()
        observeLoading()
        observeError()
    }

    private fun setupRecyclerView() {
        binding.rvExercises.layoutManager = LinearLayoutManager(requireContext())
        binding.rvExercises.adapter = adapter
    }

    private fun setupCategoryChips() {
        val allChip = Chip(requireContext()).apply {
            text = getString(R.string.category_all)
            isCheckable = true
            isChecked = true
        }
        allChip.setOnCheckedChangeListener { _, checked ->
            if (checked) viewModel.filterByCategory(null)
        }
        binding.chipGroupCategories.addView(allChip)

        ExerciseCategory.values().forEach { category ->
            val chip = Chip(requireContext()).apply {
                text = category.displayName
                isCheckable = true
            }
            chip.setOnCheckedChangeListener { _, checked ->
                if (checked) viewModel.filterByCategory(category)
            }
            binding.chipGroupCategories.addView(chip)
        }
    }

    private fun setupSearch() {
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.search(s?.toString() ?: "")
            }
            override fun afterTextChanged(s: Editable?) = Unit
        })
    }

    private fun observeExercises() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.exercises.collect { exercises ->
                adapter.submitList(exercises)
            }
        }
    }

    private fun observeLoading() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.isLoading.collect { loading ->
                binding.progressBar.visibility = if (loading) View.VISIBLE else View.GONE
            }
        }
    }

    private fun observeError() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.loadError.collect { hasError ->
                if (hasError) {
                    Toast.makeText(requireContext(), R.string.exercises_load_error, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        adapter.pauseAllVideos(binding.rvExercises)
    }

    override fun onResume() {
        super.onResume()
        adapter.resumeAllVideos(binding.rvExercises)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
