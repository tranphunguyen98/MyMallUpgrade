package com.example.mymallupgrade.di.movie.favorite

import com.example.mymallupgrade.common.ASyncTransformer
import com.example.mymallupgrade.domain.interactor.movie.GetFavoriteMovies
import com.example.mymallupgrade.domain.repository.movie.MovieRepository
import com.example.mymallupgrade.presentation.mapper.MovieEntityToMovieMapper
import com.example.mymallupgrade.ui.movie.favorite.FavoriteMoviesViewModelFactory
import dagger.Module
import dagger.Provides

/**
 * Created by Tran Phu Nguyen on 12/23/2019.
 */
@Module
class FavoriteMoviesModule {

    @Provides
    fun provideGetFavoriteMovies(movieRepository: MovieRepository): GetFavoriteMovies =
        GetFavoriteMovies(ASyncTransformer(),movieRepository)

    @Provides
    fun provideFavoriteMoviesVMFactory(
        getFavoriteMovies: GetFavoriteMovies,
        mapper: MovieEntityToMovieMapper
    ): FavoriteMoviesViewModelFactory =
        FavoriteMoviesViewModelFactory(
            getFavoriteMovies,
            mapper
        )
}