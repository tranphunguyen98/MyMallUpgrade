package com.example.mymallupgrade.presentation.movie.search

import com.example.mymallupgrade.presentation.entities.Movie

/**
 * Created by Tran Phu Nguyen on 12/23/2019.
 */

data class SearchMovieState(
    val isLoading: Boolean = false,
    val error: Throwable? = null,
    val movies: List<Movie> = emptyList()
) {
    companion object {
        fun idle(): SearchMovieState =
            SearchMovieState(
                isLoading = false,
                movies = emptyList(),
                error = null
            )
    }
}