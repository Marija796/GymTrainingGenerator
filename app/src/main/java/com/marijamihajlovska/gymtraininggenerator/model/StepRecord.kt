package com.marijamihajlovska.gymtraininggenerator.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "step_records")
data class StepRecord(
    @PrimaryKey val date: String,
    val stepCount: Int
)
