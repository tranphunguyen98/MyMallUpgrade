package phu.nguyen.data.repository.movie

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import phu.nguyen.data.model.MovieData

/**
 * Created by Tran Phu Nguyen on 12/23/2019.
 */

interface MoviesDataStore {

    fun getMovies(): Observable<List<MovieData>>

    fun getMovieById(movieId: Int): Observable<MovieData>

    fun saveMovies(movies: List<MovieData>): Completable

    fun clearMovies(): Completable

    fun getFavoriteMovies(): Observable<List<MovieData>>

    fun setMovieAsFavorite(movieId: Int): Single<Int>

    fun setMovieAsNotFavorite(movieId: Int): Single<Int>

    fun getFavoriteStatus(movieId: Int): Observable<Boolean>

    fun searchMovies(query: String): Observable<List<MovieData>>
}