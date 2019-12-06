package com.example.mymallupgrade.ui.home.ui.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mymallupgrade.domain.common.Mapper
import com.example.mymallupgrade.domain.entity.movie.MovieEntity
import com.example.mymallupgrade.domain.interactor.movie.GetPopularMovies
import com.example.mymallupgrade.entities.Movie

class PopularMoviesViewModelFactory(
    private val useCase: GetPopularMovies,
    private val mapper: Mapper<MovieEntity, Movie>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PopularMoviesViewModel(useCase, mapper) as T
    }
}