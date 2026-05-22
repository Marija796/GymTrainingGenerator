package com.marijamihajlovska.gymtraininggenerator.model

data class Exercise(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val muscleGroup: String = "",
    val sets: Int = 3,
    val reps: Int = 10,
    val imageUrl: String = ""
)