package com.example.mymallupgrade.cache.model

data class MovieDetailCache(
    val belongsToCollection: Any? = null,
    val budget: Int? = null,
    val homepage: String? = null,
    val imdbId: String? = null,
    val overview: String? = null,
    val revenue: Int? = null,
    val runtime: Int? = null,
    val status: String? = null,
    val tagline: String? = null,
    var videos: List<VideoCache>? = null
)