package com.example.mymallupgrade.data.api

import com.example.mymallupgrade.data.dto.MovieData
import io.reactivex.Observable
import retrofit2.http.GET

interface Api {
    @GET("movie/popular")
    fun getPopularMovies(): Observable<List<MovieData>>
}