package com.example.mymallupgrade.di.modules

import com.example.mymallupgrade.data.api.MovieApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import javax.inject.Singleton

@Module
class NetworkModule() {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        Timber.d("provideRetrofit")
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideMovieApi(retrofit: Retrofit): MovieApi {
        Timber.d("provideMovieApi")
        return retrofit.create(MovieApi::class.java)
    }
}