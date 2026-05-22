package com.marijamihajlovska.gymtraininggenerator.model

data class WorkoutPlan(
    val id: String = "",
    val userId: String = "",
    val name: String = "",
    val exercises: List<Exercise> = emptyList(),
    val date: Long = System.currentTimeMillis(),
    val completed: Boolean = false
)