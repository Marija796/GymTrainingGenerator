package com.marijamihajlovska.gymtraininggenerator.ui.exercises

import androidx.lifecycle.ViewModel
import com.marijamihajlovska.gymtraininggenerator.data.ExerciseRepository
import com.marijamihajlovska.gymtraininggenerator.model.ExerciseCategory
import com.marijamihajlovska.gymtraininggenerator.model.ExerciseItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ExerciseViewModel : ViewModel() {

    private val _exercises = MutableStateFlow(ExerciseRepository.all)
    val exercises: StateFlow<List<ExerciseItem>> = _exercises

    private var activeCategory: ExerciseCategory? = null
    private var activeQuery: String = ""

    fun filterByCategory(category: ExerciseCategory?) {
        activeCategory = category
        applyFilters()
    }

    fun search(query: String) {
        activeQuery = query.trim()
        applyFilters()
    }

    private fun applyFilters() {
        _exercises.value = ExerciseRepository.all.filter { ex ->
            (activeCategory == null || ex.category == activeCategory) &&
            (activeQuery.isEmpty() ||
                ex.name.contains(activeQuery, ignoreCase = true) ||
                ex.muscles.contains(activeQuery, ignoreCase = true) ||
                ex.equipment.contains(activeQuery, ignoreCase = true))
        }
    }
}
