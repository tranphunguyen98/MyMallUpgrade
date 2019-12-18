package com.example.mymallupgrade.ui.movie.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mymallupgrade.R
import com.example.mymallupgrade.databinding.VideoCellBinding
import com.example.mymallupgrade.presentation.entities.Video

/**
 * Created by Tran Phu Nguyen on 12/18/2019.
 */

class VideoAdapter : RecyclerView.Adapter<VideoAdapter.VideoHolder>() {
    private var layoutInflater: LayoutInflater? = null
    private var videos = emptyList<Video>()

    fun setData(videos: List<Video>) {
        this.videos = videos
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoHolder {
        if(layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.context)
        }
        val binding = DataBindingUtil.inflate<VideoCellBinding>(layoutInflater!!,R.layout.video_cell,parent,false)
        return VideoHolder(binding)
    }

    override fun getItemCount(): Int = videos.size

    override fun onBindViewHolder(holder: VideoHolder, position: Int) {
        holder.bind(videos[position])
    }

    class VideoHolder(val binding: VideoCellBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(video: Video) {
            this.binding.video = video
        }
    }
}