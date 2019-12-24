package com.example.mymallupgrade.domain.interactor.movie

import com.example.mymallupgrade.domain.entity.movie.MovieEntity
import com.example.mymallupgrade.domain.repository.movie.MovieRepository
import io.reactivex.Single
import io.reactivex.SingleTransformer

/**
 * Created by Tran Phu Nguyen on 12/18/2019.
 */

class SaveFavoriteMovie(
    private val transformer: SingleTransformer<Long,Long>,
    private val movieRepository: MovieRepository
) {

    operator fun invoke(movieEntity: MovieEntity): Single<Long> =
        movieRepository.save(movieEntity).compose(transformer)

}