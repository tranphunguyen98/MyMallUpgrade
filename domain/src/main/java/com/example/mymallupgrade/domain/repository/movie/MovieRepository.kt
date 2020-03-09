package com.example.mymallupgrade.domain.repository.movie

import com.example.mymallupgrade.domain.entity.movie.MovieEntity
import io.reactivex.Observable
import io.reactivex.Single

interface MovieRepository {
    fun getMovieById(movieId: Int): Observable<MovieEntity>
    fun getPopularMovies(): Observable<List<MovieEntity>>
    fun getNowPlayingMovies(): Observable<List<MovieEntity>>
    fun getUpcomingMovies(): Observable<List<MovieEntity>>
    fun getTopRatedMovies(): Observable<List<MovieEntity>>
    fun getFavoriteMovies(): Observable<List<MovieEntity>>
    fun save(movieEntity: MovieEntity): Single<Long>
    fun search(query: String): Observable<List<MovieEntity>>
    fun setMovieAsFavorite(movieId: Int): Single<Int>
    fun setMovieAsNotFavorite(movieId: Int): Single<Int>
}