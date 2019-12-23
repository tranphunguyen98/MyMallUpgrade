package com.example.mymallupgrade.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mymallupgrade.presentation.entities.Movie
import com.example.mymallupgrade.presentation.entities.Video
import com.example.mymallupgrade.ui.movie.detail.VideoAdapter
import com.example.mymallupgrade.ui.movie.favorite.FavoriteMoviesAdapter
import com.example.mymallupgrade.ui.movie.popular.PopularMoviesAdapter
import com.example.mymallupgrade.ui.movie.search.SearchMoviesAdapter
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
        @BindingAdapter("loadVideo")
        fun setRecyclerViewProperties(recyclerView: RecyclerView, videos: List<Video>?) {
            videos?.let {
                if(recyclerView.adapter is VideoAdapter) {
                    (recyclerView.adapter as VideoAdapter).setData(it)
                }
            }

        }

        @JvmStatic
        @BindingAdapter("loadMovie")
        fun setRecyclerViewData(recyclerView: RecyclerView, movies : List<Movie>?) {
            movies?.let {movies ->
                when(recyclerView.adapter) {
                    is PopularMoviesAdapter -> {
                        (recyclerView.adapter as PopularMoviesAdapter).addData(movies)
                    }
                    is FavoriteMoviesAdapter -> {
                        (recyclerView.adapter as FavoriteMoviesAdapter).addData(movies)
                    }
                    is SearchMoviesAdapter -> {
                        (recyclerView.adapter as SearchMoviesAdapter).addData(movies)
                    }
                }
            }
        }
    }
}
