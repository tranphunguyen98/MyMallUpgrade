package com.example.mymallupgrade.di.modules.popular

import com.example.mymallupgrade.presentation.mapper.MovieEntityToMovieMapper
import com.example.mymallupgrade.domain.interactor.movie.GetPopularMovies
import com.example.mymallupgrade.domain.repository.movie.MovieRepository
import com.example.mymallupgrade.ui.movie.PopularMoviesViewModelFactory
import dagger.Module
import dagger.Provides
import timber.log.Timber

@Module
class PopularMoviesModule {
    @Provides
    fun provideGetPopularMoviesUseCase(moviesRepository: MovieRepository): GetPopularMovies {
        Timber.d("provideGetPopularMoviesUseCase")
        return GetPopularMovies(moviesRepository)
    }

    @Provides
    fun providePopularMoviesViewModelFactory(
        useCase: GetPopularMovies,
        mapper: MovieEntityToMovieMapper
    ): PopularMoviesViewModelFactory {
        Timber.d("providePopularMoviesViewModelFactory")
        return PopularMoviesViewModelFactory(useCase, mapper)
    }
}