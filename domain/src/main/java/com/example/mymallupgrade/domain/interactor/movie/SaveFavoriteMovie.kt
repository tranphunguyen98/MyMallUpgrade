package com.example.mymallupgrade.domain.interactor.movie

import com.example.mymallupgrade.domain.common.Transformer
import com.example.mymallupgrade.domain.entity.movie.MovieEntity
import com.example.mymallupgrade.domain.interactor.UseCase
import com.example.mymallupgrade.domain.repository.movie.MoviesCache
import io.reactivex.Observable

/**
 * Created by Tran Phu Nguyen on 12/18/2019.
 */

class SaveFavoriteMovie(
    transformer: Transformer<Boolean>,
    private val moviesCache: MoviesCache
) : UseCase<Boolean>(transformer) {

    companion object {
        private const val PARAM_MOVIE_ENTITY = "param:movieEntity"
    }

    override fun createObservable(data: Map<String, Any>?): Observable<Boolean> {
        val movieEntity = data?.get(PARAM_MOVIE_ENTITY)

        movieEntity?.let {
            return Observable.fromCallable {
                val entity = it as MovieEntity
                moviesCache.save(entity)
                return@fromCallable true
            }
        } ?: return Observable.error { IllegalArgumentException("MovieEntity must be provided") }
    }

    operator fun invoke(movieEntity: MovieEntity): Observable<Boolean> {
        val data = HashMap<String, MovieEntity>()
        data[PARAM_MOVIE_ENTITY] = movieEntity
        return observable(data)
    }

}