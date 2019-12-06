package com.example.mymallupgrade.data.dto

import com.google.gson.annotations.SerializedName

data class MovieRemote(
    @SerializedName("id")
    val id: Int = -1,

    @SerializedName("vote_count")
    val voteCount: Int = 0,

    @SerializedName("vote_average")
    val voteAverage: Double = 0.0,

    @SerializedName("adult")
    val adult: Boolean = false,

    @SerializedName("popularity")
    val popularity: Double = 0.0,

    @SerializedName("title")
    val title: String,

    @SerializedName("poster_path")
    val posterPath: String? = null,

    @SerializedName("original_language")
    val originalLanguage: String,

    @SerializedName("original_title")
    val originalTitle: String,

    @SerializedName("backdrop_path")
    val backdropPath: String? = null,

    @SerializedName("release_date")
    val releaseDate: String,

    @SerializedName("overview")
    val overview: String? = null
)