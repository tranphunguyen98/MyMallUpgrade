package phu.nguyen.data.repository.movie

import io.reactivex.Observable
import phu.nguyen.data.model.MovieData

interface RemoteMovieDataSource {
    fun getMovieById(movieId: Int): Observable<MovieData>
    fun getPopularMovies(): Observable<List<MovieData>>
    fun getNowPlayingMovies(): Observable<List<MovieData>>
    fun getTopRatedMovies(): Observable<List<MovieData>>
    fun getUpcomingMovies(): Observable<List<MovieData>>
    fun searchMovies(query: String): Observable<List<MovieData>>
}