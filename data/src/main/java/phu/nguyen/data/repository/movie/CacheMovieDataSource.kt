package phu.nguyen.data.repository.movie

import io.reactivex.Completable
import io.reactivex.Observable
import phu.nguyen.data.model.MovieData

/**
 * Created by Tran Phu Nguyen on 12/18/2019.
 */
interface CacheMovieDataSource {
    fun clearMovies(): Completable
    fun save(movieData: MovieData): Completable
    fun remove(movieData: MovieData): Completable
    fun saveMovies(moviesData: List<MovieData>): Completable
    fun setFavoriteStatus(isFavorite: Boolean, movieId: Int): Completable
    fun getFavoriteStatus(movieId: Int): Observable<Boolean>
    fun getMovies(): Observable<List<MovieData>>
    fun get(movieId: Int): Observable<MovieData>
    fun search(query: String): Observable<List<MovieData>>
    fun isEmpty(): Observable<Boolean>
    fun areMoviesCached(): Observable<Boolean>
}