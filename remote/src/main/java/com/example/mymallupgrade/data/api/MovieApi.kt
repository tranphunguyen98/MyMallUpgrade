package com.example.mymallupgrade.data.api

import com.example.mymallupgrade.data.dto.MovieDetailRemote
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApi {
    @GET("movie/popular?api_key=3e8e250e9d20db7a41ad86ed088149cb")
    fun getPopularMovies(): Observable<MovieListResult>

    @GET("movie/{id}?api_key=3e8e250e9d20db7a41ad86ed088149cb&append_to_response=videos")
    fun getMovieDetail(@Path("id") movieId : Int): Observable<MovieDetailRemote>
}