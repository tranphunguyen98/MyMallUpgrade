package com.example.mymallupgrade.ui.movie

import androidx.lifecycle.ViewModel
import com.example.mymallupgrade.domain.common.Mapper
import com.example.mymallupgrade.domain.entity.movie.MovieEntity
import com.example.mymallupgrade.domain.interactor.movie.GetPopularMovies
import com.example.mymallupgrade.entities.Movie
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class PopularMoviesViewModel (private val getPopularMovies_: GetPopularMovies,
                              private val movieEntityMoviesMapper: Mapper<MovieEntity,Movie>)
    : ViewModel() {
        fun getPopularMovies() {
            Timber.d("getPopularMovies")
            val disposable = getPopularMovies_()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({movies ->
                    Timber.d("size = ${movies.size}")
                }, {
                    Timber.d("error ${it.message}")
                })
        }
}