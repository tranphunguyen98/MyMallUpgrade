package phu.nguyen.data.mapper

import com.example.mymallupgrade.domain.common.Mapper
import com.example.mymallupgrade.domain.entity.movie.MovieDetailEntity
import com.example.mymallupgrade.domain.entity.movie.MovieEntity
import com.example.mymallupgrade.domain.entity.movie.VideoEntity
import phu.nguyen.data.model.MovieData
import javax.inject.Inject

/**
 * Created by Tran Phu Nguyen on 12/16/2019.
 */

class MovieDetailDomainDataMapper @Inject constructor() : Mapper<MovieData, MovieEntity>() {
    override fun mapFrom(from: MovieData): MovieEntity {

        val videos: List<VideoEntity>? =
            from.details!!.videos!!.let {
                return@let it.map { videoData ->
                    return@map VideoEntity(
                        id = videoData.id,
                        youtubeKey = videoData.youtubeKey,
                        name = videoData.name
                    )
                }
            }


        val details: MovieDetailEntity? =
            from.details?.let {
                return@let MovieDetailEntity(
                    belongsToCollection = it.belongsToCollection,
                    budget = it.budget,
                    genres = null,
                    homepage = it.homepage,
                    imdbId = it.imdbId,
                    overview = it.overview,
                    revenue = it.revenue,
                    reviews = null,
                    runtime = it.runtime,
                    status = it.status,
                    tagline = it.tagline,
                    videos = videos
                )
            }

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
            overview = from.overview,
            details = details
        )


    }

    override fun to(from: MovieEntity): MovieData {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}