package com.example.mymallupgrade.data.dto

import com.google.gson.annotations.SerializedName

/**
 * Created by Tran Phu Nguyen on 12/16/2019.
 */

data class MovieDetailRemote(
    @SerializedName("adult")
    var adult: Boolean = false,

    @SerializedName("budget")
    var budget: Int? = null,

//    @SerializedName("genres")
//    var genres: List<GenreData>? = null,

//    @SerializedName("videos")
//    var videos: VideoResult? = null,
//
//    @SerializedName("reviews")
//    var reviews: ReviewsResult? = null,

    @SerializedName("homepage")
    var homepage: String? = null,

    @SerializedName("id")
    var id: Int = -1,

    @SerializedName("imdb_id")
    var imdbId: String? = null,

    @SerializedName("popularity")
    var popularity: Double = 0.0,

    @SerializedName("revenue")
    var revenue: Int? = null,

    @SerializedName("runtime")
    var runtime: Int? = null,

    @SerializedName("tagline")
    var tagline: String? = null,

    @SerializedName("video")
    var video: Boolean = false,

    @SerializedName("vote_average")
    var voteAverage: Double = 0.0,

    @SerializedName("vote_count")
    var voteCount: Int = 0,

    @SerializedName("title")
    var title: String,

    @SerializedName("poster_path")
    var posterPath: String,

    @SerializedName("original_language")
    var originalLanguage: String,

    @SerializedName("original_title")
    var originalTitle: String,

    @SerializedName("backdrop_path")
    var backdropPath: String,

    @SerializedName("overview")
    var overview: String,

    @SerializedName("release_date")
    var releaseDate: String
)