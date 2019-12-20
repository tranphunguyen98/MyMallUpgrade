package com.example.mymallupgrade.data.dataSource.movie

import com.example.mymallupgrade.data.api.MovieApi
import com.example.mymallupgrade.data.mapper.MovieDetailRemoteToEntityMapper
import com.example.mymallupgrade.data.mapper.MovieRemoteToEntityMapper
import io.reactivex.Observable
import phu.nguyen.data.model.MovieData
import phu.nguyen.data.repository.movie.RemoteMovieDataSource

class RemoteMovieDataSourceImpl (private val api: MovieApi):
    RemoteMovieDataSource {
    private val movieDataMapper = MovieRemoteToEntityMapper()
    private val movieDetailMapper = MovieDetailRemoteToEntityMapper();

    override fun getMovieById(movieId: Int): Observable<MovieData> {
        return api.getMovieDetail(movieId).flatMap {detailData ->
          Observable.just(movieDetailMapper.mapFrom(detailData))
        }
    }

    override fun getMovies(): Observable<List<MovieData>> {
        return api.getPopularMovies().map{results ->
            results.movies.map {
                movieDataMapper.mapFrom(it)
            }
        }
    }

    override fun search(query: String): Observable<List<MovieData>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}