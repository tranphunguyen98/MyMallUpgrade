package com.example.mymallupgrade.domain.repository.movie

import com.example.mymallupgrade.domain.entity.movie.MovieEntity
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface MovieRepository {
    fun getMovieById(movieId: Int): Observable<MovieEntity>
    fun getMovies(): Observable<List<MovieEntity>>
    fun getFavoriteMovies(): Observable<List<MovieEntity>>
    fun save(movieEntity: MovieEntity): Completable
    fun search(query: String): Observable<List<MovieEntity>>
    fun setMovieAsFavorite(movieId: Int): Single<Int>
    fun setMovieAsNotFavorite(movieId: Int): Single<Int>
}