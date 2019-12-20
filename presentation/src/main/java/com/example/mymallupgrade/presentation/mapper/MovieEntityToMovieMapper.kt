package com.example.mymallupgrade.presentation.mapper

import com.example.mymallupgrade.domain.common.Mapper
import com.example.mymallupgrade.domain.entity.movie.MovieEntity
import com.example.mymallupgrade.presentation.entities.Movie
import com.example.mymallupgrade.presentation.entities.MovieDetail
import com.example.mymallupgrade.presentation.entities.Video
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
            voteAverage = from.voteAverage.toString(),
            title = from.title,
            popularity = from.popularity,
            originalLanguage = from.originalLanguage,
            originalTitle = from.originalTitle,
            adult = from.adult,
            releaseDate = from.releaseDate.let { "Released: $it" },
            overview = from.overview,
            posterPath = from.posterPath?.let { posterBaseUrl + it },
            backdropPath = from.backdropPath?.let { backdropBaseUrl + it }
        )

        val fromDetails = from.details ?: return movie

        val details = MovieDetail()
        details.belongsToCollection = fromDetails.belongsToCollection
        details.budget = fromDetails.budget
        details.homepage = fromDetails.homepage
        details.imdbId = fromDetails.imdbId
        details.overview = fromDetails.overview
        details.revenue = fromDetails.revenue
        details.runtime = fromDetails.runtime
        details.status = fromDetails.status
        details.tagline = fromDetails.tagline

        fromDetails.videos?.let{
            val videos = it.map {videoEntity ->
                return@map Video(
                    id = videoEntity.id,
                    name = videoEntity.name,
                    url = youTubeBaseUrl + videoEntity.youtubeKey
                )
            }
            details.videos = videos
        }

        movie.details = details

        return movie
    }

    override fun to(from: Movie): MovieEntity {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}