package com.example.mymallupgrade.domain.interactor.movie

import com.example.mymallupgrade.domain.repository.movie.MovieRepository

class GetPopularMovies (private val movieRepository: MovieRepository) {
    operator fun invoke() = movieRepository.getPopularMovies()
}