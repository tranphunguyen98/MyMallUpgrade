package com.example.mymallupgrade.data.mapper

import com.example.mymallupgrade.data.model.MovieDetailRemote
import phu.nguyen.data.model.MovieData
import phu.nguyen.data.model.MovieDetailData
import phu.nguyen.data.model.VideoData

/**
 * Created by Tran Phu Nguyen on 12/16/2019.
 */

class MovieDetailRemoteToEntityMapper : Mapper<MovieDetailRemote, MovieData>() {
    override fun mapFrom(from: MovieDetailRemote): MovieData {
        val videoData = from.videos?.let {
            it.results?.filter { videoData ->
                videoData.site == VideoData.SOURCE_YOUTUBE &&
                        videoData.type == VideoData.TYPE_TRAILER
            }?.map { videoData ->
                return@map VideoData(
                    id = videoData.id,
                    name = videoData.name,
                    youtubeKey = videoData.key
                )
            }
        }

        val details = MovieDetailData(
            overview = from.overview,
            budget = from.budget,
            homepage = from.homepage,
            imdbId = from.imdbId,
            revenue = from.revenue,
            runtime = from.runtime,
            tagline = from.tagline,
            videos = videoData
        )

        return MovieData(
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
            overview = from.overview,
            details = details,
            isFavorite = false
        )
    }

}