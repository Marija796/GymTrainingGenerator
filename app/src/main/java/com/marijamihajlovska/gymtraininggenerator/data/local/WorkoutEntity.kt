package com.marijamihajlovska.gymtraininggenerator.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workouts")
data class WorkoutEntity(
    @PrimaryKey
    val firestoreId: String,
    val userId: String,
    val goal: String,
    val level: String,
    val muscleFocus: String,
    val exercises: String,
    val date: Long = System.currentTimeMillis(),
    val completed: Boolean = false
)
