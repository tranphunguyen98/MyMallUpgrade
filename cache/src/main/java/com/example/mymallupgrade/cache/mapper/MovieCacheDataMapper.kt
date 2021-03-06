package com.example.mymallupgrade.cache.mapper

import com.example.mymallupgrade.cache.model.MovieCache
import phu.nguyen.data.model.MovieData
import javax.inject.Inject

class MovieCacheDataMapper @Inject constructor(): Mapper<MovieCache, MovieData>() {
    override fun from(from: MovieCache): MovieData {
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

    override fun to(from: MovieData): MovieCache {
        return MovieCache(
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