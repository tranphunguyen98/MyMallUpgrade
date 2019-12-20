package com.example.mymallupgrade.di.modules

import com.example.mymallupgrade.data.api.MovieApi
import com.example.mymallupgrade.data.source.movie.RemoteMovieDataSourceImpl
import com.example.mymallupgrade.domain.repository.movie.MovieRepository
import dagger.Module
import dagger.Provides
import phu.nguyen.data.mapper.MovieDetailDomainDataMapper
import phu.nguyen.data.mapper.MovieDomainDataMapper
import phu.nguyen.data.repository.movie.MovieRepositoryImpl
import phu.nguyen.data.repository.movie.RemoteMovieDataSource
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideRemoteMovieDataSource(movieApi: MovieApi): RemoteMovieDataSource {
        return RemoteMovieDataSourceImpl(movieApi)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(
        remoteMovieDataSource: RemoteMovieDataSource,
        movieDetailDomainDataMapper: MovieDetailDomainDataMapper,
        movieDomainDataMapper: MovieDomainDataMapper
    ): MovieRepository {
        return MovieRepositoryImpl(
            remoteMovieDataSource,
            movieDetailDomainDataMapper,
            movieDomainDataMapper
        )
    }
}