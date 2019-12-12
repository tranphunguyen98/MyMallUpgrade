package com.example.mymallupgrade.presentation.mapper

import com.example.mymallupgrade.domain.common.Mapper
import com.example.mymallupgrade.domain.entity.movie.MovieEntity
import com.example.mymallupgrade.presentation.entities.Movie
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieEntityToMovieMapper @Inject constructor(): Mapper<MovieEntity,Movie>() {
    companion object {
        const val posterBaseUrl = "https://image.tmdb.org/t/p/w342"
        const val backdropBaseUrl = "https://image.tmdb.org/t/p/w780"
        const val youTubeBaseUrl = "https://www.youtube.com/watch?v="
    }

    override fun mapFrom(from: MovieEntity): Movie {
        val movie = Movie(
            id = from.id,
            voteCount = from.voteCount,
            video = from.video,
            voteAverage = from.voteAverage,
            title = from.title,
            popularity = from.popularity,
            originalLanguage = from.originalLanguage,
            originalTitle = from.originalTitle,
            adult = from.adult,
            releaseDate = from.releaseDate,
            overview = from.overview,
            posterPath = from.posterPath?.let { posterBaseUrl + it }
        )
        return movie
    }

}