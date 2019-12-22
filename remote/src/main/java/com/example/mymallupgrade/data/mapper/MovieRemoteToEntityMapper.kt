package com.example.mymallupgrade.data.mapper

import com.example.mymallupgrade.data.model.MovieRemote
import phu.nguyen.data.model.MovieData

class MovieRemoteToEntityMapper : Mapper<MovieRemote, MovieData>() {
    override fun mapFrom(from: MovieRemote): MovieData {
        return MovieData(
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
            overview = from.overview,
            isFavorite = false
        )
    }

}