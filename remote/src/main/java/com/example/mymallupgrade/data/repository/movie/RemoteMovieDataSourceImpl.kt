package com.example.mymallupgrade.data.repository.movie

import com.example.mymallupgrade.data.api.MovieApi
import com.example.mymallupgrade.data.mapper.MovieDetailRemoteToEntityMapper
import com.example.mymallupgrade.data.mapper.MovieRemoteToEntityMapper
import com.example.mymallupgrade.domain.entity.movie.MovieEntity
import com.example.mymallupgrade.domain.entity.movie.Optional
import com.example.mymallupgrade.domain.repository.movie.RemoteMovieDataSource
import io.reactivex.Observable

class RemoteMovieDataSourceImpl (private val api: MovieApi): RemoteMovieDataSource {
    private val movieDataMapper = MovieRemoteToEntityMapper()
    private val movieDetailMapper = MovieDetailRemoteToEntityMapper();

    override fun getMovieById(movieId: Int): Observable<Optional<MovieEntity>> {
        return api.getMovieDetail(movieId).flatMap {detailData ->
            Observable.just(Optional.of(movieDetailMapper.mapFrom(detailData)))
        }
    }

    override fun getMovies(): Observable<List<MovieEntity>> {
        return api.getPopularMovies().map{results ->
            results.movies.map {
                movieDataMapper.mapFrom(it)
            }
        }
    }

    override fun search(query: String): Observable<List<MovieEntity>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}