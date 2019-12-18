package com.example.mymallupgrade.data.mapper

import com.example.mymallupgrade.data.dto.MovieDetailRemote
import com.example.mymallupgrade.domain.common.Mapper
import com.example.mymallupgrade.domain.entity.movie.MovieDetailEntity
import com.example.mymallupgrade.domain.entity.movie.MovieEntity
import com.example.mymallupgrade.domain.entity.movie.VideoEntity

/**
 * Created by Tran Phu Nguyen on 12/16/2019.
 */

class MovieDetailRemoteToEntityMapper : Mapper<MovieDetailRemote, MovieEntity>() {
    override fun mapFrom(from: MovieDetailRemote): MovieEntity {
        val movieEntity = MovieEntity(
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

        val details = MovieDetailEntity(
            overview = from.overview,
            budget = from.budget,
            homepage = from.homepage,
            imdbId = from.imdbId,
            revenue = from.revenue,
            runtime = from.runtime,
            tagline = from.tagline
        )

        from.videos?.let {
            val videoEntity = it.results?.filter {videoData ->
                videoData.site == VideoEntity.SOURCE_YOUTUBE &&
                        videoData.type == VideoEntity.TYPE_TRAILER
            }?.map {videoData ->
                return@map VideoEntity(
                    id = videoData.id,
                    name = videoData.name,
                    youtubeKey = videoData.key
                )
            }

            details.videos = videoEntity
        }
        movieEntity.details = details
        return movieEntity
    }

}