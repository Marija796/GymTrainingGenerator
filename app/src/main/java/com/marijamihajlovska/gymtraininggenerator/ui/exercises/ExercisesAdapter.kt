package com.marijamihajlovska.gymtraininggenerator.ui.exercises

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.imageview.ShapeableImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.marijamihajlovska.gymtraininggenerator.R
import com.marijamihajlovska.gymtraininggenerator.model.ExerciseItem

class ExercisesAdapter : ListAdapter<ExerciseItem, ExercisesAdapter.ViewHolder>(DIFF) {

    private val expandedIds = mutableSetOf<String>()

    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<ExerciseItem>() {
            override fun areItemsTheSame(a: ExerciseItem, b: ExerciseItem) = a.id == b.id
            override fun areContentsTheSame(a: ExerciseItem, b: ExerciseItem) = a == b
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivThumbnail: ShapeableImageView = view.findViewById(R.id.ivThumbnail)
        val tvName: TextView = view.findViewById(R.id.tvExerciseName)
        val tvMuscles: TextView = view.findViewById(R.id.tvMuscles)
        val tvEquipment: TextView = view.findViewById(R.id.tvEquipment)
        val tvDifficulty: TextView = view.findViewById(R.id.tvDifficulty)
        val tvSets: TextView = view.findViewById(R.id.tvSets)
        val tvDescription: TextView = view.findViewById(R.id.tvDescription)
        val ivChevron: ImageView = view.findViewById(R.id.ivChevron)
        val layoutDetails: View = view.findViewById(R.id.layoutDetails)
        val wvVideo: WebView = view.findViewById(R.id.wvVideo)

        init {
            wvVideo.settings.apply {
                javaScriptEnabled = true
                mediaPlaybackRequiresUserGesture = false
                loadWithOverviewMode = true
                useWideViewPort = true
            }
            wvVideo.webChromeClient = WebChromeClient()
            wvVideo.webViewClient = WebViewClient()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_exercise, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val exercise = getItem(position)
        val isExpanded = expandedIds.contains(exercise.id)

        holder.tvName.text = exercise.name
        holder.tvMuscles.text = exercise.muscles
        holder.tvEquipment.text = exercise.equipment
        holder.tvDifficulty.text = exercise.difficulty.displayName
        holder.tvSets.text = "${exercise.sets} sets · ${exercise.reps} reps · ${exercise.restSeconds}s rest"
        holder.tvDescription.text = exercise.description

        Glide.with(holder.itemView.context)
            .load(exercise.imageUrl)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.drawable.baseline_fitness_center_24)
            .error(R.drawable.baseline_fitness_center_24)
            .into(holder.ivThumbnail)

        holder.layoutDetails.visibility = if (isExpanded) View.VISIBLE else View.GONE
        holder.ivThumbnail.visibility = if (isExpanded) View.GONE else View.VISIBLE
        holder.ivChevron.rotation = if (isExpanded) 180f else 0f

        if (isExpanded) {
            holder.wvVideo.loadData(
                "<html><body style='margin:0;padding:0;background:#000'>" +
                "<iframe width='100%' height='100%' src='${exercise.videoUrl}?autoplay=1&mute=1&rel=0' " +
                "frameborder='0' allow='autoplay; encrypted-media' allowfullscreen></iframe>" +
                "</body></html>",
                "text/html", "utf-8"
            )
        } else {
            holder.wvVideo.loadUrl("about:blank")
        }

        holder.itemView.setOnClickListener {
            val id = exercise.id
            if (expandedIds.contains(id)) expandedIds.remove(id) else expandedIds.add(id)
            notifyItemChanged(position)
        }
    }

    override fun onViewRecycled(holder: ViewHolder) {
        super.onViewRecycled(holder)
        holder.wvVideo.loadUrl("about:blank")
    }

    fun pauseAllVideos(recyclerView: RecyclerView) {
        for (i in 0 until recyclerView.childCount) {
            val vh = recyclerView.getChildViewHolder(recyclerView.getChildAt(i)) as? ViewHolder
            vh?.wvVideo?.onPause()
        }
    }

    fun resumeAllVideos(recyclerView: RecyclerView) {
        for (i in 0 until recyclerView.childCount) {
            val vh = recyclerView.getChildViewHolder(recyclerView.getChildAt(i)) as? ViewHolder
            vh?.wvVideo?.onResume()
        }
    }
}
