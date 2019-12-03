package com.example.mymallupgrade.data.api

import io.reactivex.Observable
import retrofit2.http.GET

interface MovieApi {
    @GET("movie/popular?api_key=3e8e250e9d20db7a41ad86ed088149cb")
    fun getPopularMovies(): Observable<MovieListResult>
}