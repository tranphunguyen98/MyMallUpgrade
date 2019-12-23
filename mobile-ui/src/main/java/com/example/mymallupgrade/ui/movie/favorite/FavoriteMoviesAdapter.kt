package com.example.mymallupgrade.ui.movie.favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mymallupgrade.R
import com.example.mymallupgrade.databinding.FavoriteMoviesAdapterCellBinding
import com.example.mymallupgrade.presentation.entities.Movie

/**
 * Created by Tran Phu Nguyen on 12/12/2019.
 */

class FavoriteMoviesAdapter(private val onMovieSelected : (Movie, View) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var data: MutableList<Movie> = mutableListOf()
    private var layoutInflater: LayoutInflater? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.context!!)
        }
        val binding = DataBindingUtil.inflate<FavoriteMoviesAdapterCellBinding>(layoutInflater!!,R.layout.favorite_movies_adapter_cell, parent, false)
        return MovieViewHolder(
            binding
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MovieViewHolder -> holder.bind(data[position],onMovieSelected)
        }
    }

    class MovieViewHolder(val binding: FavoriteMoviesAdapterCellBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie, listener : (Movie, View) -> Unit) = with(itemView) {
            binding.movie = movie
            setOnClickListener { listener(movie, itemView) }
        }
    }

    fun addData(movies: List<Movie>) {
        this.data.addAll(movies)
        notifyDataSetChanged()
    }
}