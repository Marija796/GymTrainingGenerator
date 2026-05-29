package com.marijamihajlovska.gymtraininggenerator.model

data class ExerciseItem(
    val id: String,
    val name: String,
    val category: ExerciseCategory,
    val muscles: String,
    val equipment: String,
    val difficulty: Difficulty,
    val sets: String,
    val reps: String,
    val restSeconds: Int,
    val description: String,
    val imageUrl: String,
    val videoUrl: String
)

enum class ExerciseCategory(val displayName: String) {
    CHEST("Chest"),
    BACK("Back"),
    SHOULDERS("Shoulders"),
    ARMS("Arms"),
    LEGS("Legs"),
    CORE("Core"),
    CARDIO("Cardio"),
    FULL_BODY("Full Body"),
    PLUS_EXERCISES("Plus Exercises")
}

enum class Difficulty(val displayName: String) {
    BEGINNER("Beginner"),
    INTERMEDIATE("Intermediate"),
    ADVANCED("Advanced")
}
