package com.example.mymallupgrade.domain.repository.movie

import com.example.mymallupgrade.domain.entity.movie.MovieEntity
import com.example.mymallupgrade.domain.entity.movie.Optional
import io.reactivex.Observable

interface MovieRepository {
    fun getMovieById(movieId: Int): Observable<Optional<MovieEntity>>
    fun getMovies(): Observable<List<MovieEntity>>
    fun search(query: String): Observable<List<MovieEntity>>
}