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

    @Query("SELECT * FROM step_records ORDER BY date DESC LIMIT :limit")
    fun getRecentDays(limit: Int): LiveData<List<StepRecord>>

    @Query("DELETE FROM step_records WHERE date < :cutoffDate")
    suspend fun deleteOlderThan(cutoffDate: String)
}
