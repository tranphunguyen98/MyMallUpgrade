package com.example.mymallupgrade.data.model

import com.google.gson.annotations.SerializedName

class MovieResultRemote {
    val page: Int = 0
    @SerializedName("results")
    lateinit var movies: List<MovieRemote>
}