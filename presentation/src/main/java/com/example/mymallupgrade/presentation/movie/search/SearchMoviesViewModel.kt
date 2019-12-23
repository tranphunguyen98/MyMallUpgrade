package com.example.mymallupgrade.presentation.movie.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mymallupgrade.domain.common.Mapper
import com.example.mymallupgrade.domain.entity.movie.MovieEntity
import com.example.mymallupgrade.domain.interactor.movie.SearchMovies
import com.example.mymallupgrade.presentation.BaseViewModel
import com.example.mymallupgrade.presentation.entities.Movie
import timber.log.Timber

/**
 * Created by Tran Phu Nguyen on 12/12/2019.
 */

class SearchMoviesViewModel(
    private val _searchMovie: SearchMovies,
    private val mapper: Mapper<MovieEntity, Movie>
) : BaseViewModel() {

    private val _searchMoviesState = MutableLiveData<SearchMovieState>()
    val searchMoviesState: LiveData<SearchMovieState>
        get() = _searchMoviesState

    init {
        _searchMoviesState.value = SearchMovieState.idle()
    }

    fun search(query: String) {
        _searchMoviesState.value =
            SearchMovieState(
                true
            )
        addDispoable(_searchMovie(query)
            .flatMap { mapper.observable(it) }
            .subscribe({ movies ->
                Timber.d("mv ${movies.size}")
                _searchMoviesState.value = SearchMovieState(
                    false,
                    null,
                    movies
                )
            }, {
                Timber.d(it)
                _searchMoviesState.value = SearchMovieState(
                    false,
                    it
                )
            })
        )
    }

}