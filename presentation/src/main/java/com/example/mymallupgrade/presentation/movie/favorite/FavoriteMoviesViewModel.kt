package com.example.mymallupgrade.presentation.movie.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mymallupgrade.domain.common.Mapper
import com.example.mymallupgrade.domain.entity.movie.MovieEntity
import com.example.mymallupgrade.domain.interactor.movie.GetFavoriteMovies
import com.example.mymallupgrade.presentation.BaseViewModel
import com.example.mymallupgrade.presentation.entities.Movie
import timber.log.Timber

/**
 * Created by Tran Phu Nguyen on 12/12/2019.
 */

class FavoriteMoviesViewModel(
    private val _getFavoriteMovies: GetFavoriteMovies,
    private val mapper: Mapper<MovieEntity, Movie>
) : BaseViewModel() {

    private val _favoriteMoviesState = MutableLiveData<FavoriteMovieState>()
    val favoriteMoviesState: LiveData<FavoriteMovieState>
        get() = _favoriteMoviesState

    init {
        _favoriteMoviesState.value = FavoriteMovieState.idle()
    }

    fun getPopularMovies() {
        _favoriteMoviesState.value =
            FavoriteMovieState(
                true
            )
        addDispoable(_getFavoriteMovies()
            .flatMap { mapper.observable(it) }
            .subscribe({ movies ->
                Timber.d("mv ${movies.size}")
                _favoriteMoviesState.value = FavoriteMovieState(
                    false,
                    null,
                    movies
                )
            }, {
                Timber.d(it)
                _favoriteMoviesState.value = FavoriteMovieState(
                    false,
                    it
                )
            })
        )
    }

}