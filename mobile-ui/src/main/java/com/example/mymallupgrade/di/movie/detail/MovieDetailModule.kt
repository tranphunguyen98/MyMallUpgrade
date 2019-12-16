package com.example.mymallupgrade.di.movie.detail

import com.example.mymallupgrade.domain.interactor.movie.GetMovieDetail
import com.example.mymallupgrade.domain.repository.movie.MovieRepository
import dagger.Module
import dagger.Provides

/**
 * Created by Tran Phu Nguyen on 12/13/2019.
 */
@Module
class MovieDetailModule {

    @Provides
    fun provideGetMovieDetailUseCase(movieRepository: MovieRepository): GetMovieDetail {

    }
}