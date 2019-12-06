package com.example.mymallupgrade.data.api

import com.example.mymallupgrade.data.dto.MovieRemote
import com.google.gson.annotations.SerializedName

class MovieListResult {
    val page: Int = 0
    @SerializedName("results")
    lateinit var movies: List<MovieRemote>
}