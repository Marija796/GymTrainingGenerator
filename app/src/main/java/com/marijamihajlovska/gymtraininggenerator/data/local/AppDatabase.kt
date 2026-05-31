package com.marijamihajlovska.gymtraininggenerator.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.marijamihajlovska.gymtraininggenerator.model.StepRecord

@Database(entities = [WorkoutEntity::class, StepRecord::class], version = 4, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun workoutDao(): WorkoutDao
    abstract fun stepRecordDao(): StepRecordDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        private val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    "CREATE TABLE IF NOT EXISTS `step_records` (`date` TEXT NOT NULL, `stepCount` INTEGER NOT NULL, PRIMARY KEY(`date`))"
                )
            }
        }

        private val MIGRATION_3_4 = object : Migration(3, 4) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("DROP TABLE IF EXISTS `step_records`")
                database.execSQL(
                    "CREATE TABLE IF NOT EXISTS `step_records` (`date` TEXT NOT NULL, `userId` TEXT NOT NULL, `stepCount` INTEGER NOT NULL, PRIMARY KEY(`date`, `userId`))"
                )
            }
        }

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "gym_database"
                )
                    .addMigrations(MIGRATION_2_3, MIGRATION_3_4)
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
