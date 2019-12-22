package com.example.mymallupgrade.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieCache (
    @PrimaryKey
    val id: Int = 0,
    val voteCount: Int = 0,
    val video: Boolean = false,
    val voteAverage: Double = 0.0,
    val popularity: Double = 0.0,
    val adult: Boolean = false,
    val title: String,
    val posterPath: String? = "",
    val originalLanguage: String,
    val originalTitle: String,
    val backdropPath: String? = "",
    val releaseDate: String,
    val overview: String? = "",
    val isFavorite: Boolean = false
)