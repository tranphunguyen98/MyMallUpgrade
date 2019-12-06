package com.example.mymallupgrade.di

import com.example.mymallupgrade.di.modules.DataModule
import com.example.mymallupgrade.di.modules.NetworkModule
import com.example.mymallupgrade.di.modules.popular.PopularMoviesModule
import com.example.mymallupgrade.di.modules.popular.PopularSubComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class,
        DataModule::class
    ]
)

interface MainComponent {
    fun plus(popularMoviesModule: PopularMoviesModule): PopularSubComponent
}