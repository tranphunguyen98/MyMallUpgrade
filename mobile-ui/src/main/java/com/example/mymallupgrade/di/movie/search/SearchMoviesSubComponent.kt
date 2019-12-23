package com.example.mymallupgrade.di.movie.search

import com.example.mymallupgrade.ui.movie.search.SearchMovieFragment
import dagger.Subcomponent

/**
 * Created by Tran Phu Nguyen on 12/23/2019.
 */
@Subcomponent(modules = [SearchMoviesModule::class])
interface SearchMoviesSubComponent {
    fun inject(searchMovieFragment: SearchMovieFragment)
}