package com.example.mymallupgrade.domain.interactor.movie

import com.example.mymallupgrade.domain.repository.movie.MovieRepository
import io.reactivex.Single
import io.reactivex.SingleTransformer

/**
 * Created by Tran Phu Nguyen on 12/18/2019.
 */

class SetMovieAsNotFavorite(
    private val transformer: SingleTransformer<Int,Int>,
    private val movieRepository: MovieRepository
) {
    operator fun invoke(movieId: Int): Single<Int> {
        return movieRepository.setMovieAsNotFavorite(movieId).compose(transformer)
    }


}