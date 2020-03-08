package com.example.mymallupgrade.di.movie.popular

import com.example.mymallupgrade.domain.interactor.movie.GetNowPlayingMovies
import com.example.mymallupgrade.domain.interactor.movie.GetPopularMovies
import com.example.mymallupgrade.domain.repository.movie.MovieRepository
import com.example.mymallupgrade.presentation.mapper.MovieEntityToMovieMapper
import com.example.mymallupgrade.presentation.movie.home.HomeMoviesViewModelFactory
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
    fun provideGetNowPlayingMoviesUseCase(moviesRepository: MovieRepository): GetNowPlayingMovies {
        return GetNowPlayingMovies(moviesRepository)
    }

    @Provides
    fun providePopularMoviesViewModelFactory(
        getPopularMovies: GetPopularMovies,
        getNowPlayingMovies: GetNowPlayingMovies,
        mapper: MovieEntityToMovieMapper
    ): HomeMoviesViewModelFactory {
        Timber.d("providePopularMoviesViewModelFactory")
        return HomeMoviesViewModelFactory(
            getPopularMovies,
            getNowPlayingMovies,
            mapper
        )
    }
}