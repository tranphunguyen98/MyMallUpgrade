package com.example.mymallupgrade.di.movie.popular

import com.example.mymallupgrade.domain.interactor.movie.GetNowPlayingMovies
import com.example.mymallupgrade.domain.interactor.movie.GetPopularMovies
import com.example.mymallupgrade.domain.interactor.movie.GetTopRatedMovies
import com.example.mymallupgrade.domain.interactor.movie.GetUpcomingMovies
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
    fun provideGetUpcomingMoviesUseCase(moviesRepository: MovieRepository): GetUpcomingMovies {
        return GetUpcomingMovies(moviesRepository)
    }

    @Provides
    fun provideGetTopRatedMoviesUseCase(moviesRepository: MovieRepository): GetTopRatedMovies {
        return GetTopRatedMovies(moviesRepository)
    }

    @Provides
    fun providePopularMoviesViewModelFactory(
        getPopularMovies: GetPopularMovies,
        getNowPlayingMovies: GetNowPlayingMovies,
        getUpcomingMovies: GetUpcomingMovies,
        getTopRatedMovies: GetTopRatedMovies,
        mapper: MovieEntityToMovieMapper
    ): HomeMoviesViewModelFactory {
        Timber.d("providePopularMoviesViewModelFactory")
        return HomeMoviesViewModelFactory(
            getPopularMovies,
            getNowPlayingMovies,
            getUpcomingMovies,
            getTopRatedMovies,
            mapper
        )
    }
}