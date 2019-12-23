package com.example.mymallupgrade.domain.interactor.movie

import com.example.mymallupgrade.domain.common.TransformerObservable
import com.example.mymallupgrade.domain.entity.movie.MovieEntity
import com.example.mymallupgrade.domain.repository.movie.MovieRepository
import io.reactivex.Observable

/**
 * Created by Tran Phu Nguyen on 12/23/2019.
 */
class SearchMovie(
    private val transformer: TransformerObservable<List<MovieEntity>>,
    private val movieRepository: MovieRepository
) {
    operator fun invoke(query: String): Observable<List<MovieEntity>> {
        return movieRepository.search(query).compose(transformer)
    }
}