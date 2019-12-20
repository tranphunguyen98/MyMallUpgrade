package com.example.mymallupgrade.domain.interactor.movie

import com.example.mymallupgrade.domain.common.TransformerCompletable
import com.example.mymallupgrade.domain.entity.movie.MovieEntity
import com.example.mymallupgrade.domain.interactor.UseCaseCompletable
import com.example.mymallupgrade.domain.repository.movie.MovieRepository
import io.reactivex.Completable

/**
 * Created by Tran Phu Nguyen on 12/18/2019.
 */

class SaveFavoriteMovie(
    transformerCompletable: TransformerCompletable,
    private val movieRepository: MovieRepository
) : UseCaseCompletable(transformerCompletable) {

    companion object {
        private const val PARAM_MOVIE_ENTITY = "param:movieEntity"
    }

    operator fun invoke(movieEntity: MovieEntity): Completable {
        val data = HashMap<String, MovieEntity>()
        data[PARAM_MOVIE_ENTITY] = movieEntity
        return completable(data)
    }

    override fun createCompletable(data: Map<String, Any>?): Completable {
        val movieEntity = data?.get(PARAM_MOVIE_ENTITY)

        movieEntity?.let {
            val entity = it as MovieEntity
            return movieRepository.save(entity)
        } ?: return Completable.error { IllegalArgumentException("MovieEntity must be provided") }
    }

}