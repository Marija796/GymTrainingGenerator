package com.marijamihajlovska.gymtraininggenerator.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workouts")
data class WorkoutEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val userId: String,
    val exercises: String,
    val date: Long = System.currentTimeMillis(),
    val completed: Boolean = false
)