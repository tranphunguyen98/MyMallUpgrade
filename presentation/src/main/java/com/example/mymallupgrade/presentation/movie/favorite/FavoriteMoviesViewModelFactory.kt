package com.example.mymallupgrade.presentation.movie.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mymallupgrade.domain.common.Mapper
import com.example.mymallupgrade.domain.entity.movie.MovieEntity
import com.example.mymallupgrade.domain.interactor.movie.GetFavoriteMovies
import com.example.mymallupgrade.presentation.entities.Movie

/**
 * Created by Tran Phu Nguyen on 12/23/2019.
 */

class FavoriteMoviesViewModelFactory(
    private val useCase: GetFavoriteMovies,
    private val mapper: Mapper<MovieEntity, Movie>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FavoriteMoviesViewModel(
            useCase,
            mapper
        ) as T
    }
}