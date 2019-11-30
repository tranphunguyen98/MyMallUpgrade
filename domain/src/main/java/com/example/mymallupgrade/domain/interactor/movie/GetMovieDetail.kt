package com.example.mymallupgrade.domain.interactor.movie

import com.example.mymallupgrade.domain.repository.movie.MovieRepository

class GetMovieDetail(
    private val movieRepository: MovieRepository
) {
    operator fun invoke(movieId: Int) = movieRepository.getMovieById(movieId)

}