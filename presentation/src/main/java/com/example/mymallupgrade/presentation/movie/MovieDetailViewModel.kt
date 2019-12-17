package com.example.mymallupgrade.presentation.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mymallupgrade.domain.common.Mapper
import com.example.mymallupgrade.domain.entity.movie.MovieEntity
import com.example.mymallupgrade.domain.interactor.movie.GetMovieDetail
import com.example.mymallupgrade.presentation.BaseViewModel
import com.example.mymallupgrade.presentation.entities.Movie
import timber.log.Timber

/**
 * Created by Tran Phu Nguyen on 12/17/2019.
 */

class MovieDetailViewModel(
    private val _getMovieDetail: GetMovieDetail,
    private val mapper: Mapper<MovieEntity, Movie>
) :BaseViewModel() {
    private val _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie>
        get() = _movie

    private val _loadingState = MutableLiveData<Boolean>()
    val loadingState: LiveData<Boolean>
        get() = _loadingState

    private val _errorState = MutableLiveData<Throwable>()
    val errorState: LiveData<Throwable>
        get() = _errorState

    fun getMovieDetail(movieId : Int) {
        _loadingState.value = true
        Timber.d("getMovieDetail")
        addDispoable(
            _getMovieDetail(movieId)
                .map {
                    it.value?.let {movieEntity ->
                        mapper.mapFrom(movieEntity)
                    } ?: run {
                        throw Throwable("Something went wrong")
                    }
                }
                .subscribe(
                    {
                        _movie.value = it
                        _loadingState.value = false
                        Timber.d("it= ${it.backdropPath}")
                    },
                    {
                        _errorState.value = it
                        _loadingState.value = false
                        Timber.d("err ${it.message}")
                    }
                )
        )

    }
}