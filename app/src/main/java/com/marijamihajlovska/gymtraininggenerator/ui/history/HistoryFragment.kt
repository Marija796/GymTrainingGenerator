package com.marijamihajlovska.gymtraininggenerator.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.marijamihajlovska.gymtraininggenerator.R
import com.marijamihajlovska.gymtraininggenerator.data.local.AppDatabase
import com.marijamihajlovska.gymtraininggenerator.data.local.WorkoutEntity
import com.marijamihajlovska.gymtraininggenerator.databinding.FragmentHistoryBinding
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class HistoryItem(
    val goal: String,
    val muscleFocus: String,
    val date: Long,
    val completed: Boolean
)

class HistoryFragment : Fragment() {
    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()
        binding.rvHistory.layoutManager = LinearLayoutManager(requireContext())

        val uid = auth.currentUser?.uid
        if (uid == null) {
            binding.tvEmptyHistory.visibility = View.VISIBLE
            return
        }

        val workoutDao = AppDatabase.getDatabase(requireContext()).workoutDao()

        // Observe Room LiveData — drives the RecyclerView
        workoutDao.getWorkoutsByUser(uid).observe(viewLifecycleOwner) { entities ->
            val b = _binding ?: return@observe
            if (entities.isEmpty()) {
                b.tvEmptyHistory.visibility = View.VISIBLE
                b.rvHistory.visibility = View.GONE
            } else {
                b.tvEmptyHistory.visibility = View.GONE
                b.rvHistory.visibility = View.VISIBLE
                val items = entities.map { e ->
                    HistoryItem(
                        goal = e.goal,
                        muscleFocus = e.muscleFocus,
                        date = e.date,
                        completed = e.completed
                    )
                }
                b.rvHistory.adapter = HistoryAdapter(items)
            }
        }

        // Sync from Firestore → Room so data is always current
        db.collection("workouts")
            .whereEqualTo("userId", uid)
            .orderBy("date", Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener { documents ->
                lifecycleScope.launch {
                    for (doc in documents) {
                        val exercises = (doc.get("exercises") as? List<*>)?.joinToString(",") ?: ""
                        val entity = WorkoutEntity(
                            firestoreId = doc.id,
                            userId = uid,
                            goal = doc.getString("goal") ?: "",
                            level = doc.getString("level") ?: "",
                            muscleFocus = doc.getString("muscleFocus") ?: "",
                            exercises = exercises,
                            date = doc.getLong("date") ?: 0L,
                            completed = doc.getBoolean("completed") ?: false
                        )
                        workoutDao.insertWorkout(entity)
                    }
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

class HistoryAdapter(private val items: List<HistoryItem>) :
    RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    private val dateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())

    class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvGoal: TextView = itemView.findViewById(R.id.tvHistoryGoal)
        val tvMuscle: TextView = itemView.findViewById(R.id.tvHistoryMuscle)
        val tvDate: TextView = itemView.findViewById(R.id.tvHistoryDate)
        val tvStatus: TextView = itemView.findViewById(R.id.tvHistoryStatus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_history, parent, false)
        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val item = items[position]
        holder.tvGoal.text = item.goal
        holder.tvMuscle.text = item.muscleFocus
        holder.tvDate.text = dateFormat.format(Date(item.date))
        if (item.completed) {
            holder.tvStatus.text = holder.itemView.context.getString(R.string.status_completed)
            holder.tvStatus.setBackgroundResource(R.drawable.bg_status_completed)
        } else {
            holder.tvStatus.text = holder.itemView.context.getString(R.string.status_in_progress)
            holder.tvStatus.setBackgroundResource(R.drawable.bg_badge)
        }
    }

    override fun getItemCount() = items.size
}
