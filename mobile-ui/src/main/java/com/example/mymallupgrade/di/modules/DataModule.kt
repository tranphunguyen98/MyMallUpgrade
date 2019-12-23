package com.example.mymallupgrade.di.modules

import com.example.mymallupgrade.cache.db.MoviesDatabase
import com.example.mymallupgrade.cache.mapper.MovieCacheDataMapper
import com.example.mymallupgrade.cache.source.CacheMovieDataSourceImpl
import com.example.mymallupgrade.data.api.MovieApi
import com.example.mymallupgrade.data.source.movie.RemoteMovieDataSourceImpl
import com.example.mymallupgrade.domain.repository.movie.MovieRepository
import dagger.Module
import dagger.Provides
import phu.nguyen.data.mapper.MovieDetailDomainDataMapper
import phu.nguyen.data.mapper.MovieDomainDataMapper
import phu.nguyen.data.repository.movie.CacheMovieDataSource
import phu.nguyen.data.repository.movie.MovieRepositoryImpl
import phu.nguyen.data.repository.movie.RemoteMovieDataSource
import phu.nguyen.data.store.MoviesDataStoreFactory
import javax.inject.Singleton

@Module
class DataModule {
    @Provides
    @Singleton
    fun provideCacheMovieDataSource(database: MoviesDatabase,mapper: MovieCacheDataMapper): CacheMovieDataSource {
//        Timber.d("provideCacheMovieDataSource")
        return CacheMovieDataSourceImpl(database,mapper)
    }

    @Provides
    @Singleton
    fun provideRemoteMovieDataSource(movieApi: MovieApi): RemoteMovieDataSource {
        return RemoteMovieDataSourceImpl(movieApi)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(
        cacheMovieDataSource: CacheMovieDataSource,
        movieDetailDomainDataMapper: MovieDetailDomainDataMapper,
        movieDomainDataMapper: MovieDomainDataMapper,
        factory: MoviesDataStoreFactory
    ): MovieRepository {
        return MovieRepositoryImpl(
            cacheMovieDataSource,
            movieDetailDomainDataMapper,
            movieDomainDataMapper,
            factory
        )
    }
}