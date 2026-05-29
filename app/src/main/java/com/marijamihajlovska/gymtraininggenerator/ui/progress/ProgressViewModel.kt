package com.marijamihajlovska.gymtraininggenerator.ui.progress

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.marijamihajlovska.gymtraininggenerator.data.local.AppDatabase
import com.marijamihajlovska.gymtraininggenerator.model.StepRecord
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class ProgressViewModel(application: Application) : AndroidViewModel(application) {

    private val stepManager = StepCounterManager(application)
    private val dao = AppDatabase.getDatabase(application).stepRecordDao()

    val todaySteps = stepManager.todaySteps
    val isAvailable get() = stepManager.isAvailable

    var counterActive = false
        private set

    val stepHistory: LiveData<List<StepRecord>> = dao.getRecentDays(7)

    val averageSteps: LiveData<Int> = stepHistory.map { list ->
        if (list.isEmpty()) 0 else list.sumOf { it.stepCount } / list.size
    }

    init {
        viewModelScope.launch {
            stepManager.dayCompleted.collect { (date, count) ->
                if (count > 0) {
                    dao.upsert(StepRecord(date, count))
                }
                dao.deleteOlderThan(daysAgo(30))
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
