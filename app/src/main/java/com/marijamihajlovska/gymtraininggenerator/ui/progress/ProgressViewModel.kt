package com.marijamihajlovska.gymtraininggenerator.ui.progress

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.marijamihajlovska.gymtraininggenerator.data.local.AppDatabase
import com.marijamihajlovska.gymtraininggenerator.model.StepRecord
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class ProgressViewModel(application: Application) : AndroidViewModel(application) {

    private val currentUserId: String =
        FirebaseAuth.getInstance().currentUser?.uid ?: ""

    private val stepManager = StepCounterManager(application, currentUserId)
    private val dao = AppDatabase.getDatabase(application).stepRecordDao()

    val todaySteps = stepManager.todaySteps
    val isAvailable get() = stepManager.isAvailable

    var counterActive = false
        private set

    val stepHistory: LiveData<List<StepRecord>> = dao.getRecentDays(currentUserId, 7)

    val averageSteps: LiveData<Int> = stepHistory.map { list ->
        if (list.isEmpty()) 0 else list.sumOf { it.stepCount } / list.size
    }

    init {
        viewModelScope.launch {
            stepManager.dayCompleted.collect { (date, count) ->
                if (count > 0 && currentUserId.isNotEmpty()) {
                    dao.upsert(StepRecord(date, currentUserId, count))
                }
                if (currentUserId.isNotEmpty()) {
                    dao.deleteOlderThan(currentUserId, daysAgo(30))
                }
            }
        }
    }

    fun startCounter() {
        counterActive = true
        stepManager.start()
    }

    fun stopCounter() {
        stepManager.stop()
    }

    fun setCounterUnavailable() {
        counterActive = false
    }

    override fun onCleared() {
        super.onCleared()
        stepManager.stop()
    }

    private fun daysAgo(days: Int): String {
        val cal = Calendar.getInstance()
        cal.add(Calendar.DAY_OF_YEAR, -days)
        return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(cal.time)
    }
}
