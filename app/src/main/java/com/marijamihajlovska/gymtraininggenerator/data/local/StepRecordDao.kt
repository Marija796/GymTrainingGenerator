package com.marijamihajlovska.gymtraininggenerator.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.marijamihajlovska.gymtraininggenerator.model.StepRecord

@Dao
interface StepRecordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(record: StepRecord)

    @Query("SELECT * FROM step_records WHERE userId = :userId ORDER BY date DESC LIMIT :limit")
    fun getRecentDays(userId: String, limit: Int): LiveData<List<StepRecord>>

    @Query("DELETE FROM step_records WHERE userId = :userId AND date < :cutoffDate")
    suspend fun deleteOlderThan(userId: String, cutoffDate: String)

    @Query("DELETE FROM step_records WHERE userId = :userId")
    suspend fun deleteAllByUser(userId: String)
}
