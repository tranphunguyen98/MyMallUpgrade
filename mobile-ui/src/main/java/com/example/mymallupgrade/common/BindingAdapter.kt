package com.example.mymallupgrade.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mymallupgrade.presentation.entities.Video
import com.example.mymallupgrade.ui.movie.detail.VideoAdapter
import com.squareup.picasso.Picasso
import timber.log.Timber

/**
 * Created by Tran Phu Nguyen on 12/17/2019.
 */
class BindingAdapter {
    companion object {
        @JvmStatic
        @BindingAdapter("loadImage")
        fun loadImageSource(view: ImageView, url: String?) {
            url?.let {
                Picasso.get()
                    .load(it)
                    .into(view)
            }
        }

        @JvmStatic
        @BindingAdapter("loadData")
        fun setRecyclerViewProperties(recyclerView: RecyclerView, videos: List<Video>?) {
            Timber.d("loadData ${videos?.get(0)?.name}")
            videos?.let {
                if(recyclerView.adapter is VideoAdapter) {
                    (recyclerView.adapter as VideoAdapter).setData(it)
                }
            }

        }
    }
}
