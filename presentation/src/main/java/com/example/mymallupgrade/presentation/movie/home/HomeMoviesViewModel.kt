package com.example.mymallupgrade.presentation.movie.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mymallupgrade.domain.common.Mapper
import com.example.mymallupgrade.domain.entity.movie.MovieEntity
import com.example.mymallupgrade.domain.interactor.movie.GetNowPlayingMovies
import com.example.mymallupgrade.domain.interactor.movie.GetPopularMovies
import com.example.mymallupgrade.domain.interactor.movie.GetTopRatedMovies
import com.example.mymallupgrade.domain.interactor.movie.GetUpcomingMovies
import com.example.mymallupgrade.presentation.BaseViewModel
import com.example.mymallupgrade.presentation.entities.Movie
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

/**
 * Created by Tran Phu Nguyen on 12/12/2019.
 */

class HomeMoviesViewModel(
    private val _getPopularMovies: GetPopularMovies,
    private val _getNowPlayingMovies: GetNowPlayingMovies,
    private val _getUpcomingMovies: GetUpcomingMovies,
    private val _getTopRatedMovies: GetTopRatedMovies,
    private val moviesEntityToMovieMapper: Mapper<MovieEntity, Movie>
) : BaseViewModel() {

    private val _popularMovies = MutableLiveData<List<Movie>>()
    val popularMovies: LiveData<List<Movie>>
        get() = _popularMovies

    private val _nowPlayingMovies = MutableLiveData<List<Movie>>()
    val nowPlayingMovies: LiveData<List<Movie>>
        get() = _nowPlayingMovies

    private val _upcomingMovies = MutableLiveData<List<Movie>>()
    val upcomingMovies: LiveData<List<Movie>>
        get() = _upcomingMovies

    private val _topRatedMovies = MutableLiveData<List<Movie>>()
    val topRatedMovies: LiveData<List<Movie>>
        get() = _topRatedMovies

    private val _loadingState = MutableLiveData<Boolean>()
    val loadingState: LiveData<Boolean>
        get() = _loadingState

    private val _errorState = MutableLiveData<Throwable>()
    val errorState: LiveData<Throwable>
        get() = _errorState

    fun getPopularMovies() {
        _loadingState.value = true
        addDispoable(_getPopularMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap { moviesEntityToMovieMapper.observable(it) }
            .subscribe({ movies ->
                Timber.d("mv ${movies.size}")
                _popularMovies.value = movies
                _errorState.value = null
                _loadingState.value = false
            }, {
                Timber.d(it)
                _errorState.value = it
                _loadingState.value = false
            })
        )
    }

    fun getNowPlayingMovies() {
        addDispoable(_getNowPlayingMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap { moviesEntityToMovieMapper.observable(it) }
            .subscribe({movies ->
                _nowPlayingMovies.value = movies
                Timber.d("mv now ${movies[0].title}")
            },{
                _errorState.value = it
            })

        )
    }

    fun getUpcomingMovies() {
        addDispoable(_getUpcomingMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap { moviesEntityToMovieMapper.observable(it) }
            .subscribe({
                _upcomingMovies.value = it
                Timber.d("mv now ${it[0].title}")
            },{
                _errorState.value = it
                Timber.d("mv now ${it.message}")
            }))
    }

    fun getTopRatedMovies() {
        addDispoable(_getTopRatedMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap { moviesEntityToMovieMapper.observable(it) }
            .subscribe({
                _topRatedMovies.value = it
                Timber.d("mv now ${it[0].title}")
            },{
                _errorState.value = it
                Timber.d("mv now ${it.message}")
            }))
    }

}