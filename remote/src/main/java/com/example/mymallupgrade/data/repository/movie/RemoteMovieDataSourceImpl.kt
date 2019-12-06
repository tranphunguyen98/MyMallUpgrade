package com.example.mymallupgrade.data.repository.movie

import com.example.mymallupgrade.data.api.MovieApi
import com.example.mymallupgrade.data.mapper.MovieDataMapper
import com.example.mymallupgrade.domain.entity.movie.MovieEntity
import com.example.mymallupgrade.domain.entity.movie.Optional
import com.example.mymallupgrade.domain.repository.movie.RemoteMovieDataSource
import io.reactivex.Observable
import timber.log.Timber

class RemoteMovieDataSourceImpl (private val api: MovieApi): RemoteMovieDataSource {
    override fun getMovieById(movieId: Int): Observable<Optional<MovieEntity>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private val movieDataMapper = MovieDataMapper()

    override fun getMovies(): Observable<List<MovieEntity>> {
        return api.getPopularMovies().map{results ->
            Timber.d("getPopularMovies ${results.page}")
            results.movies.map {
                movieDataMapper.mapFrom(it)
            }
        }
    }

    override fun search(query: String): Observable<List<MovieEntity>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}