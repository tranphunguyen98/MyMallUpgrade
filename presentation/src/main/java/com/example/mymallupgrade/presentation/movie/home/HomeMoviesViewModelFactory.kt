package com.example.mymallupgrade.presentation.movie.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mymallupgrade.domain.common.Mapper
import com.example.mymallupgrade.domain.entity.movie.MovieEntity
import com.example.mymallupgrade.domain.interactor.movie.GetNowPlayingMovies
import com.example.mymallupgrade.domain.interactor.movie.GetPopularMovies
import com.example.mymallupgrade.presentation.entities.Movie

class HomeMoviesViewModelFactory(
    private val getPopularMovies: GetPopularMovies,
    private val getNowPlayingMovies: GetNowPlayingMovies,
    private val mapper: Mapper<MovieEntity, Movie>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeMoviesViewModel(
            getPopularMovies,
            getNowPlayingMovies,
            mapper
        ) as T
    }
}