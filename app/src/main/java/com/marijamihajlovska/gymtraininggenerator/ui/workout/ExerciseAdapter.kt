package com.marijamihajlovska.gymtraininggenerator.ui.workout

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.marijamihajlovska.gymtraininggenerator.R

class ExerciseAdapter(private val exercises: List<String>) :
    RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>() {

    class ExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNumber: TextView = itemView.findViewById(R.id.tvWorkoutExerciseNumber)
        val tvName: TextView = itemView.findViewById(R.id.tvWorkoutExerciseName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_workout_exercise, parent, false)
        return ExerciseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        holder.tvNumber.text = "${position + 1}"
        holder.tvName.text = exercises[position]
    }

    override fun getItemCount() = exercises.size
}
