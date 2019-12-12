package com.example.mymallupgrade.ui.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mymallupgrade.R
import com.example.mymallupgrade.presentation.entities.Movie
import com.squareup.picasso.Picasso
import timber.log.Timber

/**
 * Created by Tran Phu Nguyen on 12/12/2019.
 */

class PopularMoviesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var data: MutableList<Movie> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(parent.context!!)
            .inflate(R.layout.popular_movies_adapter_cell, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MovieViewHolder -> holder.bind(data[position])
        }
    }

    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imgMovie = view.findViewById<ImageView>(R.id.img_movie)
        private val tvTitle = view.findViewById<TextView>(R.id.tv_title_movie)

        fun bind(movie: Movie) {
            tvTitle.text = movie.title
            Picasso.get().load(movie.posterPath).into(imgMovie)
        }
    }

    fun addData(movies: List<Movie>) {
        this.data.addAll(movies)
        notifyDataSetChanged()
    }
}