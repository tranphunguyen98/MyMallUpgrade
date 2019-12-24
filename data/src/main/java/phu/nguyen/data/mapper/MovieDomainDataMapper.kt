package phu.nguyen.data.mapper

import com.example.mymallupgrade.domain.common.Mapper
import com.example.mymallupgrade.domain.entity.movie.MovieEntity
import phu.nguyen.data.model.MovieData
import javax.inject.Inject

class MovieDomainDataMapper @Inject constructor() : Mapper<MovieData, MovieEntity>() {
    override fun mapFrom(from: MovieData): MovieEntity {
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
            overview = from.overview,
            isFavorite = from.isFavorite
        )
    }

    override fun to(from: MovieEntity): MovieData {
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
            isFavorite = from.isFavorite
        )
    }

}