package com.example.mymallupgrade.domain.interactor.movie

import com.example.mymallupgrade.domain.common.TransformerObservable
import com.example.mymallupgrade.domain.entity.movie.MovieEntity
import com.example.mymallupgrade.domain.repository.movie.MovieRepository
import io.reactivex.Observable

/**
 * Created by Tran Phu Nguyen on 12/23/2019.
 */
class GetFavoriteMovies(
    private val transformer: TransformerObservable<List<MovieEntity>>,
    private val movieRepository: MovieRepository
) {
    operator fun invoke(): Observable<List<MovieEntity>> =
        movieRepository.getFavoriteMovies().compose(transformer)

}
