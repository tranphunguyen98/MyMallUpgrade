package com.example.mymallupgrade.data.api

import com.example.mymallupgrade.data.model.MovieDetailRemote
import com.example.mymallupgrade.data.model.MovieResultRemote
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("movie/now_playing?api_key=3e8e250e9d20db7a41ad86ed088149cb")
    fun getNowPlayingMovies(): Observable<MovieResultRemote>

    @GET("movie/popular?api_key=3e8e250e9d20db7a41ad86ed088149cb")
    fun getPopularMovies(): Observable<MovieResultRemote>

    @GET("movie/{id}?api_key=3e8e250e9d20db7a41ad86ed088149cb&append_to_response=videos")
    fun getMovieDetail(@Path("id") movieId : Int): Observable<MovieDetailRemote>

    @GET("search/movie?api_key=3e8e250e9d20db7a41ad86ed088149cb")
    fun searchMovies(@Query("query") query: String): Observable<MovieResultRemote>
}