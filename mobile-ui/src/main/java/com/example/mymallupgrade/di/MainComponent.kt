package com.example.mymallupgrade.di

import com.example.mymallupgrade.di.modules.AppModule
import com.example.mymallupgrade.di.modules.CacheModule
import com.example.mymallupgrade.di.modules.DataModule
import com.example.mymallupgrade.di.modules.NetworkModule
import com.example.mymallupgrade.di.movie.detail.DetailSubComponent
import com.example.mymallupgrade.di.movie.detail.MovieDetailModule
import com.example.mymallupgrade.di.movie.popular.PopularMoviesModule
import com.example.mymallupgrade.di.movie.popular.PopularSubComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NetworkModule::class,
        DataModule::class,
        CacheModule::class
    ]
)

interface MainComponent {
    fun plus(popularMoviesModule: PopularMoviesModule): PopularSubComponent
    fun plus(movieDetailModule: MovieDetailModule): DetailSubComponent
}