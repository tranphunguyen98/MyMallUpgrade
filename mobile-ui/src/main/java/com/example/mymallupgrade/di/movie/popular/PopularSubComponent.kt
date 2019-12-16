package com.example.mymallupgrade.di.movie.popular

import com.example.mymallupgrade.ui.movie.popular.MovieFragment
import dagger.Subcomponent

@Subcomponent(modules = [PopularMoviesModule::class])
interface PopularSubComponent {
    fun inject(movieFragment: MovieFragment)
}