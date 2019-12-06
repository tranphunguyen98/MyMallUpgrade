package com.example.mymallupgrade.di.modules

import com.example.mymallupgrade.data.api.MovieApi
import com.example.mymallupgrade.data.repository.movie.MovieRepositoryImpl
import com.example.mymallupgrade.data.repository.movie.RemoteMovieDataSourceImpl
import com.example.mymallupgrade.domain.repository.movie.MovieRepository
import dagger.Module
import dagger.Provides
import timber.log.Timber
import javax.inject.Singleton

@Module
class DataModule {
    @Provides
    @Singleton
    fun provideMovieRepository(movieApi: MovieApi): MovieRepository {
        Timber.d("provideMovieRepository")
        val remoteMovieDataSourceImpl = RemoteMovieDataSourceImpl(movieApi)
        return MovieRepositoryImpl(remoteMovieDataSourceImpl)
    }
}