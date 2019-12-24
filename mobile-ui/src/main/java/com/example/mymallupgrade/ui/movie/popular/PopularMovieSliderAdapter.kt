package com.example.mymallupgrade.ui.movie.popular

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mymallupgrade.R
import com.example.mymallupgrade.databinding.ItemVpPopularBinding
import com.example.mymallupgrade.presentation.entities.Movie

/**
 * Created by Tran Phu Nguyen on 12/24/2019.
 */
class PopularMovieSliderAdapter(private val onMovieSelected: (Movie, View) -> Unit) : RecyclerView.Adapter<PopularMovieSliderAdapter.MovieSliderViewHolder>() {
    private val data: MutableList<Movie> = mutableListOf()
    private var layoutInflater: LayoutInflater? = null
    fun addData(data: List<Movie>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieSliderViewHolder {
        if(layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.context)
        }
        val binding = DataBindingUtil.inflate<ItemVpPopularBinding>(layoutInflater!!, R.layout.item_vp_popular,parent,false)

        return MovieSliderViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size


    override fun onBindViewHolder(holder: MovieSliderViewHolder, position: Int) {
        holder.bind(data[position],onMovieSelected)
    }

    class MovieSliderViewHolder(private val binding: ItemVpPopularBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie,onMovieSelected: (Movie, View) -> Unit) {
            binding.movie = movie
            itemView.setOnClickListener { onMovieSelected(movie,itemView) }
        }
    }

}