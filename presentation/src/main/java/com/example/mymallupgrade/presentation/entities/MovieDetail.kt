package com.example.mymallupgrade.presentation.entities

/**
 * Created by Tran Phu Nguyen on 12/17/2019.
 */
data class MovieDetail(
    var belongsToCollection: Any? = null,
    var budget: Int? = null,
    var homepage: String? = null,
    var imdbId: String? = null,
    var overview: String? = null,
    var revenue: Int? = null,
    var runtime: Int? = null,
    var status: String? = null,
    var tagline: String? = null,
    var videos: List<Video>? = null
)