package com.example.mymallupgrade.presentation.entities

data class Movie (
    var id: Int = 0,
    var voteCount: Int = 0,
    var video: Boolean = false,
    var voteAverage: String = "",
    var title: String,
    var popularity: Double = 0.0,
    var posterPath: String? = null,
    var originalLanguage: String,
    var originalTitle: String,
    var backdropPath: String? = null,
    var adult: Boolean = false,
    var releaseDate: String,
    var isFavorite: Boolean = false,
    var details: MovieDetail? = null,
    var overview: String? = null) {

    fun containsVideos(): Boolean {
        return details?.videos != null && details?.videos?.isNotEmpty() ?: false
    }

//    fun containsReviews(): Boolean {
//        return details?.reviews != null && details?.reviews?.isNotEmpty() ?: false
//    }
//
//    fun containsGenres(): Boolean {
//        return details?.genres != null && details?.genres?.isNotEmpty() ?: false
//    }
}