package com.example.mymallupgrade.domain.interactor.movie

import com.example.mymallupgrade.domain.common.TransformerCompletable
import com.example.mymallupgrade.domain.interactor.UseCaseCompletable
import com.example.mymallupgrade.domain.repository.movie.MovieRepository
import io.reactivex.Completable

/**
 * Created by Tran Phu Nguyen on 12/18/2019.
 */

class SetMovieAsFavorite(
    transformerCompletable: TransformerCompletable,
    private val movieRepository: MovieRepository
) : UseCaseCompletable(transformerCompletable) {

    companion object {
        private const val PARAM_MOVIE_ID = "param:movieId"
    }

    operator fun invoke(movieId: Int): Completable {
        val data = HashMap<String, Int>()
        data[PARAM_MOVIE_ID] = movieId
        return completable(data)
    }

    override fun createCompletable(data: Map<String, Any>?): Completable {
        val movieEntity = data?.get(PARAM_MOVIE_ID)
        movieEntity?.let {
            val id = it as Int
            return movieRepository.setMovieAsFavorite(id)
        } ?: return Completable.error { IllegalArgumentException("MovieId must be provided") }
    }

}