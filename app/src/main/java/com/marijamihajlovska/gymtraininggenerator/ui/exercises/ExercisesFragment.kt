package com.marijamihajlovska.gymtraininggenerator.ui.exercises

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marijamihajlovska.gymtraininggenerator.databinding.FragmentExercisesBinding

class ExercisesFragment : Fragment() {
    private var _binding: FragmentExercisesBinding? = null
    private val binding get() = _binding!!

    private val exerciseList = listOf(
        "Squats - Legs, Glutes",
        "Push-ups - Chest, Triceps",
        "Pull-ups - Back, Biceps",
        "Deadlift - Back, Legs",
        "Bench Press - Chest, Shoulders",
        "Shoulder Press - Shoulders, Triceps",
        "Lunges - Legs, Glutes",
        "Plank - Core, Abs",
        "Burpees - Full Body",
        "Bicep Curls - Biceps",
        "Tricep Dips - Triceps",
        "Leg Press - Legs",
        "Lat Pulldown - Back",
        "Rows - Back, Biceps",
        "Calf Raises - Calves",
        "Glute Bridges - Glutes",
        "Face Pulls - Shoulders",
        "Lateral Raises - Shoulders",
        "Jumping Jacks - Cardio",
        "Mountain Climbers - Core, Cardio"
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExercisesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvExercises.layoutManager = LinearLayoutManager(requireContext())
        binding.rvExercises.adapter = ExercisesAdapter(exerciseList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

class ExercisesAdapter(private val items: List<String>) :
    RecyclerView.Adapter<ExercisesAdapter.ExercisesViewHolder>() {

    class ExercisesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvItem: TextView = itemView.findViewById(android.R.id.text1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExercisesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_1, parent, false)
        return ExercisesViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExercisesViewHolder, position: Int) {
        holder.tvItem.text = items[position]
    }

    override fun getItemCount() = items.size
}