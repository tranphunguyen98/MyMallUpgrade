package com.example.mymallupgrade.data.mapper

import com.example.mymallupgrade.data.dto.MovieDetailRemote
import com.example.mymallupgrade.domain.common.Mapper
import com.example.mymallupgrade.domain.entity.movie.MovieEntity

/**
 * Created by Tran Phu Nguyen on 12/16/2019.
 */

class MovieDetailRemoteToEntityMapper : Mapper<MovieDetailRemote,MovieEntity>() {
    override fun mapFrom(from: MovieDetailRemote): MovieEntity {
        return MovieEntity(
            id = from.id,
            voteCount = from.voteCount,
            video = from.video,
            voteAverage = from.voteAverage,
            popularity = from.popularity,
            adult = from.adult,
            title = from.title,
            posterPath = from.posterPath,
            originalTitle = from.originalTitle,
            backdropPath = from.backdropPath,
            originalLanguage = from.originalLanguage,
            releaseDate = from.releaseDate,
            overview = from.overview
        )
    }

}