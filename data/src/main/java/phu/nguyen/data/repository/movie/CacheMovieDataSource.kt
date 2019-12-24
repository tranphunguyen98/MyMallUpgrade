package phu.nguyen.data.repository.movie

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import phu.nguyen.data.model.MovieData

/**
 * Created by Tran Phu Nguyen on 12/18/2019.
 */
interface CacheMovieDataSource {
    fun clearMovies(): Completable
    fun save(movieData: MovieData): Single<Long>
    fun remove(movieData: MovieData): Completable
    fun saveMovies(moviesData: List<MovieData>): Completable
    fun setFavoriteStatus(isFavorite: Boolean, movieId: Int): Single<Int>
    fun getFavoriteStatus(movieId: Int): Observable<Boolean>
    fun getMovies(): Observable<List<MovieData>>
    fun get(movieId: Int): Observable<MovieData>
    fun search(query: String): Observable<List<MovieData>>
    fun isEmpty(): Observable<Boolean>
    fun areMoviesCached(): Observable<Boolean>
    fun getFavoriteMovies(): Observable<List<MovieData>>
}