package com.marijamihajlovska.gymtraininggenerator.ui.progress

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class StepCounterManager(context: Context, userId: String) : SensorEventListener {

    private val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private val prefs = context.getSharedPreferences("step_prefs_$userId", Context.MODE_PRIVATE)

    private val _todaySteps = MutableLiveData(0)
    val todaySteps: LiveData<Int> = _todaySteps

    private val _dayCompleted = MutableSharedFlow<Pair<String, Int>>(extraBufferCapacity = 10)
    val dayCompleted: SharedFlow<Pair<String, Int>> = _dayCompleted

    val isAvailable: Boolean
        get() = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null

    fun start() {
        val sensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) ?: return
        try {
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI)
        } catch (e: SecurityException) {
            // ACTIVITY_RECOGNITION permission not granted
        }
    }

    fun stop() {
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent) {
        val totalSteps = event.values[0].toLong()
        val today = today()

        val savedDate = prefs.getString("step_date", "")
        val baseline = prefs.getLong("step_baseline", totalSteps)

        if (savedDate != today) {
            // Day rolled over — emit completed day's count before resetting
            if (!savedDate.isNullOrEmpty()) {
                val prevSteps = (totalSteps - baseline).coerceAtLeast(0).toInt()
                _dayCompleted.tryEmit(savedDate to prevSteps)
            }
            prefs.edit()
                .putString("step_date", today)
                .putLong("step_baseline", totalSteps)
                .apply()
            _todaySteps.postValue(0)
        } else {
            val steps = (totalSteps - baseline).coerceAtLeast(0).toInt()
            _todaySteps.postValue(steps)
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) = Unit

    private fun today(): String =
        SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
}
