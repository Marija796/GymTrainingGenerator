package com.marijamihajlovska.gymtraininggenerator.ui.exercises

import android.os.Build
import android.text.Html
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marijamihajlovska.gymtraininggenerator.data.ExerciseRepository
import com.marijamihajlovska.gymtraininggenerator.data.remote.RetrofitClient
import com.marijamihajlovska.gymtraininggenerator.data.remote.WgerExerciseInfo
import com.marijamihajlovska.gymtraininggenerator.model.Difficulty
import com.marijamihajlovska.gymtraininggenerator.model.ExerciseCategory
import com.marijamihajlovska.gymtraininggenerator.model.ExerciseItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ExerciseViewModel : ViewModel() {

    private val localExercises: List<ExerciseItem> = ExerciseRepository.all
    private var plusExercises: List<ExerciseItem> = emptyList()

    private var activeCategory: ExerciseCategory? = null
    private var activeQuery: String = ""

    private val _exercises = MutableStateFlow(localExercises)
    val exercises: StateFlow<List<ExerciseItem>> = _exercises

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _loadError = MutableStateFlow(false)
    val loadError: StateFlow<Boolean> = _loadError

    fun filterByCategory(category: ExerciseCategory?) {
        activeCategory = category
        if (category == ExerciseCategory.PLUS_EXERCISES && plusExercises.isEmpty()) {
            fetchPlusExercises()
        } else {
            applyFilters()
        }
    }

    fun search(query: String) {
        activeQuery = query.trim()
        applyFilters()
    }

    private fun applyFilters() {
        val source = if (activeCategory == ExerciseCategory.PLUS_EXERCISES) {
            plusExercises
        } else {
            localExercises
        }
        _exercises.value = source.filter { ex ->
            (activeCategory == null || ex.category == activeCategory) &&
            (activeQuery.isEmpty() ||
                ex.name.contains(activeQuery, ignoreCase = true) ||
                ex.muscles.contains(activeQuery, ignoreCase = true) ||
                ex.equipment.contains(activeQuery, ignoreCase = true))
        }
    }

    private fun fetchPlusExercises() {
        viewModelScope.launch {
            _isLoading.value = true
            _loadError.value = false
            try {
                val response = RetrofitClient.wgerApi.getExercises()
                plusExercises = response.results.mapNotNull { mapWgerToExerciseItem(it) }
            } catch (e: Exception) {
                _loadError.value = true
            } finally {
                _isLoading.value = false
                applyFilters()
            }
        }
    }

    private fun mapWgerToExerciseItem(wger: WgerExerciseInfo): ExerciseItem? {
        val translation = wger.translations.firstOrNull { it.language == 2 } ?: return null
        val name = translation.name.trim().ifBlank { return null }
        val description = stripHtml(translation.description)
        val muscles = wger.muscles.joinToString(", ") { it.nameEn }.ifBlank { "General" }
        val equipment = wger.equipment.joinToString(", ") { it.name }.ifBlank { "None" }
        return ExerciseItem(
            id = "wger_${wger.id}",
            name = name,
            category = ExerciseCategory.PLUS_EXERCISES,
            muscles = muscles,
            equipment = equipment,
            difficulty = Difficulty.BEGINNER,
            sets = "3",
            reps = "10-12",
            restSeconds = 60,
            description = description,
            imageUrl = "",
            videoUrl = ""
        )
    }

    @Suppress("DEPRECATION")
    private fun stripHtml(html: String): String {
        if (html.isBlank()) return ""
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT).toString().trim()
        } else {
            Html.fromHtml(html).toString().trim()
        }
    }
}
