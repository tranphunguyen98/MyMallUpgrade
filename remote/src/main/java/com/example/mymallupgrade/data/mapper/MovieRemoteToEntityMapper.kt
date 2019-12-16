package com.example.mymallupgrade.data.mapper

import com.example.mymallupgrade.data.dto.MovieRemote
import com.example.mymallupgrade.domain.common.Mapper
import com.example.mymallupgrade.domain.entity.movie.MovieEntity

class MovieRemoteToEntityMapper : Mapper<MovieRemote,MovieEntity>() {
    override fun mapFrom(from: MovieRemote): MovieEntity {
        return MovieEntity(
            id = from.id,
            voteCount = from.voteCount,
            voteAverage = from.voteAverage,
            popularity = from.popularity,
            adult = from.adult,
            title = from.title,
            posterPath = from.posterPath,
            originalLanguage = from.originalLanguage,
            backdropPath = from.backdropPath,
            originalTitle = from.originalTitle,
            releaseDate = from.releaseDate,
            overview = from.overview
        )
    }

}