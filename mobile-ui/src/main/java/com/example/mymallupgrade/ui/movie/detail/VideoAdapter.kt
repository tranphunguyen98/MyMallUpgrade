package com.example.mymallupgrade.ui.movie.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mymallupgrade.R
import com.example.mymallupgrade.presentation.entities.Video

/**
 * Created by Tran Phu Nguyen on 12/18/2019.
 */

class VideoAdapter : RecyclerView.Adapter<VideoAdapter.VideoHolder>() {

    var videos = emptyList<Video>()

    fun setData(videos: List<Video>) {
        this.videos = videos
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.video_cell,parent,false)
        return VideoHolder(view)
    }

    override fun getItemCount(): Int = videos.size

    override fun onBindViewHolder(holder: VideoHolder, position: Int) {
        holder.bind(videos[position])
    }

    class VideoHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(video: Video) {
            itemView.findViewById<TextView>(R.id.video_adapter_name).text = video.name
        }
    }
}