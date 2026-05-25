package com.marijamihajlovska.gymtraininggenerator.ui.exercises

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.marijamihajlovska.gymtraininggenerator.R
import com.marijamihajlovska.gymtraininggenerator.databinding.FragmentExercisesBinding

data class Exercise(
    val name: String,
    val muscles: String,
    val sets: String,
    val reps: String,
    val rest: String,
    val description: String,
    val gifUrl: String,
    val imageResId: Int
)

class ExercisesFragment : Fragment() {
    private var _binding: FragmentExercisesBinding? = null
    private val binding get() = _binding!!

    private val exerciseList = listOf(
        Exercise("Squats", "Legs, Glutes", "4", "12-15", "60s",
            "Stand with feet shoulder-width apart. Lower your body until thighs are parallel to floor. Keep back straight and knees behind toes.",
            "https://upload.wikimedia.org/wikipedia/commons/b/b4/Squat_01.gif",
            android.R.drawable.ic_menu_myplaces),
        Exercise("Push-ups", "Chest, Triceps", "3", "15-20", "45s",
            "Start in plank position. Lower chest to floor keeping elbows at 45 degrees. Push back up to starting position.",
            "https://upload.wikimedia.org/wikipedia/commons/6/6d/Pushup_2.gif",
            android.R.drawable.ic_menu_myplaces),
        Exercise("Pull-ups", "Back, Biceps", "3", "8-12", "90s",
            "Hang from bar with overhand grip shoulder-width apart. Pull body up until chin is over the bar. Lower slowly.",
            "https://upload.wikimedia.org/wikipedia/commons/e/ef/Pullup_2.gif",
            android.R.drawable.ic_menu_myplaces),
        Exercise("Deadlift", "Back, Legs", "4", "8-10", "120s",
            "Stand with feet hip-width apart. Hinge at hips keeping back flat. Lower the bar to the floor then drive hips forward to stand.",
            "https://upload.wikimedia.org/wikipedia/commons/b/b1/Conventional_deadlift.gif",
            android.R.drawable.ic_menu_myplaces),
        Exercise("Bench Press", "Chest, Shoulders", "4", "8-12", "90s",
            "Lie on bench with feet flat on floor. Lower bar to chest then press back up. Keep wrists straight throughout.",
            "https://upload.wikimedia.org/wikipedia/commons/8/8d/Bench_press.gif",
            android.R.drawable.ic_menu_myplaces),
        Exercise("Shoulder Press", "Shoulders, Triceps", "3", "10-12", "60s",
            "Hold dumbbells at shoulder height palms forward. Press overhead until arms fully extended. Lower slowly.",
            "https://upload.wikimedia.org/wikipedia/commons/3/30/Overhead_press.gif",
            android.R.drawable.ic_menu_myplaces),
        Exercise("Lunges", "Legs, Glutes", "3", "12-15", "60s",
            "Stand tall then step forward with one leg. Lower back knee toward the floor. Push through front heel to return.",
            "https://upload.wikimedia.org/wikipedia/commons/9/9e/Lunge_2.gif",
            android.R.drawable.ic_menu_myplaces),
        Exercise("Plank", "Core, Abs", "3", "30-60s", "45s",
            "Hold a push-up position with arms straight or on forearms. Keep body in straight line from head to heels. Breathe steadily.",
            "https://upload.wikimedia.org/wikipedia/commons/7/77/Plank_exercise.gif",
            android.R.drawable.ic_menu_myplaces),
        Exercise("Bicep Curls", "Biceps", "3", "12-15", "45s",
            "Hold dumbbells with palms facing forward. Keep elbows close to body. Curl weights up to shoulder level then lower slowly.",
            "https://upload.wikimedia.org/wikipedia/commons/b/b7/Bicep_curl.gif",
            android.R.drawable.ic_menu_myplaces),
        Exercise("Tricep Dips", "Triceps", "3", "12-15", "45s",
            "Place hands on bench or chair behind you. Lower body by bending elbows to 90 degrees then push back up.",
            "https://upload.wikimedia.org/wikipedia/commons/5/59/Dips.gif",
            android.R.drawable.ic_menu_myplaces),
        Exercise("Leg Press", "Legs", "4", "12-15", "90s",
            "Sit in leg press machine. Place feet shoulder-width on platform. Push platform away until legs extended then lower slowly.",
            "https://upload.wikimedia.org/wikipedia/commons/b/b4/Squat_01.gif",
            android.R.drawable.ic_menu_myplaces),
        Exercise("Lat Pulldown", "Back", "3", "10-12", "60s",
            "Sit at lat pulldown machine. Grip bar wider than shoulders. Pull bar down to upper chest while leaning slightly back.",
            "https://upload.wikimedia.org/wikipedia/commons/e/ef/Pullup_2.gif",
            android.R.drawable.ic_menu_myplaces),
        Exercise("Rows", "Back, Biceps", "3", "10-12", "60s",
            "Hold dumbbells with slight bend in knees. Hinge forward at hips. Pull weights to hip level squeezing shoulder blades together.",
            "https://upload.wikimedia.org/wikipedia/commons/b/b1/Conventional_deadlift.gif",
            android.R.drawable.ic_menu_myplaces),
        Exercise("Calf Raises", "Calves", "4", "15-20", "45s",
            "Stand with feet hip-width apart. Rise onto balls of feet as high as possible. Lower slowly for full stretch.",
            "https://upload.wikimedia.org/wikipedia/commons/9/9e/Lunge_2.gif",
            android.R.drawable.ic_menu_myplaces),
        Exercise("Glute Bridges", "Glutes", "3", "15-20", "45s",
            "Lie on back with knees bent feet flat on floor. Drive hips up squeezing glutes at the top. Hold 2 seconds then lower.",
            "https://upload.wikimedia.org/wikipedia/commons/7/77/Plank_exercise.gif",
            android.R.drawable.ic_menu_myplaces),
        Exercise("Face Pulls", "Shoulders, Rear Delts", "3", "15-20", "45s",
            "Attach rope to cable machine at face height. Pull rope toward face keeping elbows high. Squeeze rear delts at peak.",
            "https://upload.wikimedia.org/wikipedia/commons/3/30/Overhead_press.gif",
            android.R.drawable.ic_menu_myplaces),
        Exercise("Lateral Raises", "Shoulders", "3", "12-15", "45s",
            "Hold dumbbells at sides. Raise arms out to sides until parallel with floor. Lower slowly with control.",
            "https://upload.wikimedia.org/wikipedia/commons/3/30/Overhead_press.gif",
            android.R.drawable.ic_menu_myplaces),
        Exercise("Jumping Jacks", "Cardio, Full Body", "3", "30-60s", "30s",
            "Stand with feet together arms at sides. Jump feet apart while raising arms overhead. Return to start and repeat.",
            "https://upload.wikimedia.org/wikipedia/commons/6/6d/Pushup_2.gif",
            android.R.drawable.ic_menu_myplaces),
        Exercise("Mountain Climbers", "Core, Cardio", "3", "20-30", "45s",
            "Start in high plank position. Alternate driving knees toward chest as fast as possible while keeping hips level.",
            "https://upload.wikimedia.org/wikipedia/commons/7/77/Plank_exercise.gif",
            android.R.drawable.ic_menu_myplaces),
        Exercise("Burpees", "Full Body, Cardio", "3", "10-15", "60s",
            "From standing drop to push-up position. Do a push-up. Jump feet forward to hands then explode upward with arms overhead.",
            "https://upload.wikimedia.org/wikipedia/commons/6/6d/Pushup_2.gif",
            android.R.drawable.ic_menu_myplaces)
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
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

class ExercisesAdapter(private val items: List<Exercise>) :
    RecyclerView.Adapter<ExercisesAdapter.ExerciseViewHolder>() {

    private val expandedPositions = mutableSetOf<Int>()

    inner class ExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvExerciseName)
        val tvMuscles: TextView = itemView.findViewById(R.id.tvMuscles)
        val tvSets: TextView = itemView.findViewById(R.id.tvSets)
        val tvDescription: TextView = itemView.findViewById(R.id.tvDescription)
        val ivExercise: ImageView = itemView.findViewById(R.id.ivExercise)
        val ivGif: ImageView = itemView.findViewById(R.id.ivGif)
        val layoutDetails: View = itemView.findViewById(R.id.layoutDetails)
        val card: CardView = itemView.findViewById(R.id.cardExercise)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_exercise, parent, false)
        return ExerciseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val exercise = items[position]
        val isExpanded = expandedPositions.contains(position)

        holder.tvName.text = exercise.name
        holder.tvMuscles.text = "💪 ${exercise.muscles}"
        holder.tvSets.text = "Sets: ${exercise.sets} | Reps: ${exercise.reps} | Rest: ${exercise.rest}"
        holder.tvDescription.text = exercise.description
        holder.layoutDetails.visibility = if (isExpanded) View.VISIBLE else View.GONE

        holder.ivExercise.setImageResource(exercise.imageResId)

        if (isExpanded) {
            Glide.with(holder.itemView.context)
                .asGif()
                .load(exercise.gifUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(android.R.drawable.ic_menu_gallery)
                .error(android.R.drawable.ic_menu_gallery)
                .into(holder.ivGif)
        }

        holder.card.setOnClickListener {
            if (isExpanded) expandedPositions.remove(position)
            else expandedPositions.add(position)
            notifyItemChanged(position)
        }
    }

    override fun getItemCount() = items.size
}