package com.example.mymallupgrade.presentation.movie.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mymallupgrade.domain.common.Mapper
import com.example.mymallupgrade.domain.entity.movie.MovieEntity
import com.example.mymallupgrade.domain.interactor.movie.GetMovieDetail
import com.example.mymallupgrade.domain.interactor.movie.SaveFavoriteMovie
import com.example.mymallupgrade.domain.interactor.movie.SetMovieAsFavorite
import com.example.mymallupgrade.domain.interactor.movie.SetMovieAsNotFavorite
import com.example.mymallupgrade.presentation.BaseViewModel
import com.example.mymallupgrade.presentation.entities.Movie
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import timber.log.Timber

/**
 * Created by Tran Phu Nguyen on 12/17/2019.
 */

class MovieDetailViewModel(
    private val _setMovieAsFavorite: SetMovieAsFavorite,
    private val _setMovieAsNotFavorite: SetMovieAsNotFavorite,
    private val _saveFavoriteMovie: SaveFavoriteMovie,
    private val _getMovieDetail: GetMovieDetail,
    private val mapper: Mapper<MovieEntity, Movie>,
    private val movieId: Int
) : BaseViewModel() {
    private lateinit var movieEntity: MovieEntity
    private val _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie>
        get() = _movie

    private val _loadingState = MutableLiveData<Boolean>()
    val loadingState: LiveData<Boolean>
        get() = _loadingState

    private val _errorState = MutableLiveData<Throwable>()
    val errorState: LiveData<Throwable>
        get() = _errorState

    var favoriteState: MutableLiveData<Boolean> = MutableLiveData()

    fun getMovieDetail() {
        _loadingState.value = true
        Timber.d("getMovieDetail")
        addDispoable(
            _getMovieDetail(movieId)
                .map {
                    movieEntity = it
                    mapper.mapFrom(it)
                }
                .subscribe(
                    {
                        _movie.value = it
                        _loadingState.value = false
                        favoriteState.value = it.isFavorite
                        Timber.d("video = ${it.details?.videos?.size}")
                    },
                    {
                        _errorState.value = it
                        _loadingState.value = false
                        Timber.d("err ${it.message}")
                    }
                )
        )

    }

    fun saveMovieFavorite() {
        val disposable: Disposable
        if (favoriteState.value == true) {
            disposable = _setMovieAsNotFavorite(movieEntity.id)
                .subscribe({
                    Timber.d("Successful")
                    favoriteState.value = false
                }, {
                    Timber.d("err: ${it.message}")
                })
        } else {
            disposable = _setMovieAsFavorite(movieEntity.id)
                .flatMap {
                    if (it > 0) {
                        Single.just(1L)
                    } else {
                        return@flatMap _saveFavoriteMovie(movieEntity.copy(isFavorite = true))
                    }
                }
                .subscribe({
                    Timber.d("Successful ne $it  a")
                    favoriteState.value = true
                }, {
                    Timber.d("err: ${it.message}")
                })
        }


        addDispoable(disposable)
    }

}