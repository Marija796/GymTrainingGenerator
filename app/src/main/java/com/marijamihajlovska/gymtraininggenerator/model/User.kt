package com.marijamihajlovska.gymtraininggenerator.model

data class User(
    val uid: String = "",
    val name: String = "",
    val email: String = "",
    val height: Float = 0f,
    val weight: Float = 0f,
    val age: Int = 0,
    val fitnessGoal: String = "",
    val fitnessLevel: String = "",
    val trainingDays: Int = 3
)