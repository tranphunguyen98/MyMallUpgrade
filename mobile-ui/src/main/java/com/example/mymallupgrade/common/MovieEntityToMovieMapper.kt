package com.example.mymallupgrade.common

import com.example.mymallupgrade.domain.common.Mapper
import com.example.mymallupgrade.domain.entity.movie.MovieEntity
import com.example.mymallupgrade.entities.Movie
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieEntityToMovieMapper @Inject constructor(): Mapper<MovieEntity,Movie>() {
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
            overview = from.overview
        )
        return movie
    }

}