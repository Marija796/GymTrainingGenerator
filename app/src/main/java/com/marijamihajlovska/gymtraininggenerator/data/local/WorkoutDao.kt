package com.marijamihajlovska.gymtraininggenerator.data.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface WorkoutDao {
    @Query("SELECT * FROM workouts WHERE userId = :userId ORDER BY date DESC")
    fun getWorkoutsByUser(userId: String): LiveData<List<WorkoutEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkout(workout: WorkoutEntity)

    @Update
    suspend fun updateWorkout(workout: WorkoutEntity)

    @Delete
    suspend fun deleteWorkout(workout: WorkoutEntity)

    @Query("SELECT * FROM workouts WHERE firestoreId = :firestoreId")
    suspend fun getWorkoutById(firestoreId: String): WorkoutEntity?

    @Query("UPDATE workouts SET completed = 1 WHERE firestoreId = :id")
    suspend fun markComplete(id: String)
}
