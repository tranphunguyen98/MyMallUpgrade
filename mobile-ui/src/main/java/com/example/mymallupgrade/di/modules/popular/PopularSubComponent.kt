package com.example.mymallupgrade.di.modules.popular

import com.example.mymallupgrade.ui.movie.MovieFragment
import dagger.Subcomponent

@Subcomponent(modules = [PopularMoviesModule::class])
interface PopularSubComponent {
    fun inject(movieFragment: MovieFragment)
}