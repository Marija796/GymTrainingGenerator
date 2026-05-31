package com.marijamihajlovska.gymtraininggenerator.model

import androidx.room.Entity

@Entity(tableName = "step_records", primaryKeys = ["date", "userId"])
data class StepRecord(
    val date: String,
    val userId: String,
    val stepCount: Int
)
