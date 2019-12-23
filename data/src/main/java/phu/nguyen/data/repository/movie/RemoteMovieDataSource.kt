package phu.nguyen.data.repository.movie

import io.reactivex.Observable
import phu.nguyen.data.model.MovieData

interface RemoteMovieDataSource {
    fun getMovieById(movieId: Int): Observable<MovieData>
    fun getMovies(): Observable<List<MovieData>>
    fun searchMovies(query: String): Observable<List<MovieData>>
}