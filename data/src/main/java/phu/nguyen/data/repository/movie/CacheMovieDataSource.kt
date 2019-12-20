package phu.nguyen.data.repository.movie

import io.reactivex.Observable
import phu.nguyen.data.model.MovieData

/**
 * Created by Tran Phu Nguyen on 12/18/2019.
 */
interface CacheMovieDataSource {
    fun clear()
    fun save(movieData: MovieData)
    fun remove(movieData: MovieData)
    fun saveAll(moviesData: List<MovieData>)
    fun getAll(): Observable<List<MovieData>>
    fun get(movieId: Int): Observable<MovieData>
    fun search(query: String): Observable<List<MovieData>>
    fun isEmpty(): Observable<Boolean>
}