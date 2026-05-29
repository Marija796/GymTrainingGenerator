package com.marijamihajlovska.gymtraininggenerator.ui.progress

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.marijamihajlovska.gymtraininggenerator.R
import com.marijamihajlovska.gymtraininggenerator.model.StepRecord
import java.text.SimpleDateFormat
import java.util.Locale

class StepDayAdapter : ListAdapter<StepRecord, StepDayAdapter.ViewHolder>(DIFF) {

    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<StepRecord>() {
            override fun areItemsTheSame(a: StepRecord, b: StepRecord) = a.date == b.date
            override fun areContentsTheSame(a: StepRecord, b: StepRecord) = a == b
        }
        private val inputFmt = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        private val displayFmt = SimpleDateFormat("EEE, d MMM", Locale.getDefault())
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvDate: TextView = view.findViewById(R.id.tvDate)
        val tvStepCount: TextView = view.findViewById(R.id.tvStepCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_step_day, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val record = getItem(position)
        val date = try { inputFmt.parse(record.date)?.let { displayFmt.format(it) } ?: record.date }
                   catch (e: Exception) { record.date }
        holder.tvDate.text = date
        holder.tvStepCount.text = holder.itemView.context.getString(R.string.steps_count, record.stepCount)
    }
}
