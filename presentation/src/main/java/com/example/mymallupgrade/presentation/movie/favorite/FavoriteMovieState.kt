package com.example.mymallupgrade.presentation.movie.favorite

import com.example.mymallupgrade.presentation.entities.Movie

/**
 * Created by Tran Phu Nguyen on 12/23/2019.
 */

data class FavoriteMovieState(
    val isLoading: Boolean = false,
    val error: Throwable? = null,
    val movies: List<Movie> = emptyList()
) {
    companion object {
        fun idle(): FavoriteMovieState =
            FavoriteMovieState(
                isLoading = false,
                movies = emptyList(),
                error = null
            )
    }
}