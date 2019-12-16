package com.example.mymallupgrade.data.repository.movie

import com.example.mymallupgrade.domain.entity.movie.MovieEntity
import com.example.mymallupgrade.domain.entity.movie.Optional
import com.example.mymallupgrade.domain.repository.movie.MovieRepository
import io.reactivex.Observable
import timber.log.Timber

class MovieRepositoryImpl (private val remoteMovieDataSourceImpl: RemoteMovieDataSourceImpl) : MovieRepository {
    init {
        Timber.d("MovieRepositoryImpl created")
    }
    override fun getMovieById(movieId: Int): Observable<Optional<MovieEntity>> {
        return remoteMovieDataSourceImpl.getMovieById(movieId)
    }

    override fun getMovies(): Observable<List<MovieEntity>> {
        return remoteMovieDataSourceImpl.getMovies()
    }

    override fun search(query: String): Observable<List<MovieEntity>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}