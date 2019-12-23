package com.example.mymallupgrade.cache.source

import com.example.mymallupgrade.cache.dao.MoviesDao
import com.example.mymallupgrade.cache.db.MoviesDatabase
import com.example.mymallupgrade.cache.mapper.MovieCacheDataMapper
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import phu.nguyen.data.model.MovieData
import phu.nguyen.data.repository.movie.CacheMovieDataSource
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Tran Phu Nguyen on 12/20/2019.
 */

class CacheMovieDataSourceImpl @Inject constructor(
    database: MoviesDatabase,
    private val mapper: MovieCacheDataMapper

) : CacheMovieDataSource {

    private val dao: MoviesDao = database.getMoviesDao()

    override fun clearMovies(): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun save(movieData: MovieData): Completable {
        Timber.d("save movie ${movieData.title}")
        return dao.saveMovie(mapper.to(movieData))
    }

    override fun remove(movieData: MovieData): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveMovies(moviesData: List<MovieData>): Completable {
        return dao.saveMovies(moviesData.map { mapper.to(it) })
    }

    override fun setFavoriteStatus(isFavorite: Boolean, movieId: Int): Single<Int> {
        return dao.setFavoriteStatus(isFavorite, movieId)
    }

    override fun getFavoriteStatus(movieId: Int): Observable<Boolean> {
        Timber.d("movieId $movieId")
        return dao.getFavoriteStatus(movieId).defaultIfEmpty(false).toObservable()
    }

    override fun getMovies(): Observable<List<MovieData>> {
        return dao.getMovies().map { movies ->
            movies.map {
                mapper.from(it)
            }
        }
    }

    override fun get(movieId: Int): Observable<MovieData> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun search(query: String): Observable<List<MovieData>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isEmpty(): Observable<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun areMoviesCached(): Observable<Boolean> {
        return Observable.fromCallable {
            dao.getOneMovie() != null
        }
    }

    override fun getFavoriteMovies(): Observable<List<MovieData>> =
        dao.getFavoriteMovies().map { movies ->
            movies.map {
                mapper.from(it)
            }
        }

}