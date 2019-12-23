package com.example.mymallupgrade.di.movie.search

import com.example.mymallupgrade.common.ASyncTransformer
import com.example.mymallupgrade.domain.interactor.movie.SearchMovies
import com.example.mymallupgrade.domain.repository.movie.MovieRepository
import com.example.mymallupgrade.presentation.mapper.MovieEntityToMovieMapper
import com.example.mymallupgrade.presentation.movie.search.SearchMoviesViewModelFactory
import dagger.Module
import dagger.Provides

/**
 * Created by Tran Phu Nguyen on 12/23/2019.
 */
@Module
class SearchMoviesModule {

    @Provides
    fun provideSearchMovies(movieRepository: MovieRepository): SearchMovies =
        SearchMovies(ASyncTransformer(), movieRepository)

    @Provides
    fun provideSearchMoviesVMFactory(
        searchMovies: SearchMovies,
        mapper: MovieEntityToMovieMapper
    ): SearchMoviesViewModelFactory =
        SearchMoviesViewModelFactory(
            searchMovies,
            mapper
        )
}