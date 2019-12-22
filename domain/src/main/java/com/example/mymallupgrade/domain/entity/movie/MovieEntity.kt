package com.example.mymallupgrade.domain.entity.movie

data class MovieEntity (
    val id: Int = 0,
    val voteCount: Int = 0,
    val video: Boolean = false,
    val voteAverage: Double = 0.0,
    val popularity: Double = 0.0,
    val adult: Boolean = false,
    var details: MovieDetailEntity? = null,
    val title: String,
    val posterPath: String? = "",
    val originalLanguage: String,
    val originalTitle: String,
    val backdropPath: String?,
    val releaseDate: String,
    val overview: String? = null,
    val isFavorite: Boolean = false
)