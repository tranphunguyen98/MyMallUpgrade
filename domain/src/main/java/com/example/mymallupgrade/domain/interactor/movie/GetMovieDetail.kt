package com.example.mymallupgrade.domain.interactor.movie

import com.example.mymallupgrade.domain.common.Transformer
import com.example.mymallupgrade.domain.entity.movie.MovieEntity
import com.example.mymallupgrade.domain.interactor.UseCase
import com.example.mymallupgrade.domain.repository.movie.MovieRepository
import io.reactivex.Observable

class GetMovieDetail(
    transformer: Transformer<MovieEntity>,
    private val movieRepository: MovieRepository
) : UseCase<MovieEntity>(transformer) {

    companion object {
        private const val PARAM_MOVIE_ENTITY = "param:movieEntity"
    }

    operator fun invoke(movieId: Int): Observable<MovieEntity> {
        val data = HashMap<String,Int>()
        data[PARAM_MOVIE_ENTITY] = movieId
        return observable(data)
    }

    override fun createObservable(data: Map<String, Any>?): Observable<MovieEntity> {
        val movieId = data?.get(PARAM_MOVIE_ENTITY)
        movieId?.let {
            return movieRepository.getMovieById(it as Int)
        } ?: return Observable.error(IllegalArgumentException("MovieId must be provide."))
    }

}