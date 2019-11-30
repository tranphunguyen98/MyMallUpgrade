package com.example.mymallupgrade.domain.entity.movie

data class MovieDetailEntity(
    val belongsToCollection: Any? = null,
    val budget: Int? = null,
    val homepage: String? = null,
    val imdbId: String? = null,
    val overview: String? = null,
    val revenue: Int? = null,
    val runtime: Int? = null,
    val status: String? = null,
    val tagline: String? = null,
    val reviews: List<ReviewEntity>? = null,
    val videos: List<VideoEntity>? = null,
    val genres: List<GenreEntity>? = null
)