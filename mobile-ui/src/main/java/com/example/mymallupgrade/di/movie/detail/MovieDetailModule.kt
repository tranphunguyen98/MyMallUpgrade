package com.example.mymallupgrade.di.movie.detail

import com.example.mymallupgrade.common.ASyncTransformer
import com.example.mymallupgrade.common.ASyncTransformerCompletable
import com.example.mymallupgrade.domain.interactor.movie.GetMovieDetail
import com.example.mymallupgrade.domain.interactor.movie.SaveFavoriteMovie
import com.example.mymallupgrade.domain.repository.movie.MovieRepository
import com.example.mymallupgrade.presentation.mapper.MovieEntityToMovieMapper
import com.example.mymallupgrade.ui.movie.detail.DetailMovieViewModelFactory
import dagger.Module
import dagger.Provides

/**
 * Created by Tran Phu Nguyen on 12/13/2019.
 */
@Module
class MovieDetailModule {

    @Provides
    fun provideSaveFavoriteMovieUseCase( movieRepository: MovieRepository): SaveFavoriteMovie {
        return SaveFavoriteMovie(ASyncTransformerCompletable(), movieRepository)
    }

    @Provides
    fun provideGetMovieDetailUseCase(movieRepository: MovieRepository): GetMovieDetail {
        return GetMovieDetail(ASyncTransformer(), movieRepository)
    }

    @Provides
    fun provideDetailMovieViewModelFactory(
        saveFavoriteMovie: SaveFavoriteMovie,
        getMovieDetail: GetMovieDetail,
        mapper: MovieEntityToMovieMapper
    ): DetailMovieViewModelFactory {
        return DetailMovieViewModelFactory(saveFavoriteMovie,getMovieDetail,mapper)
    }
}