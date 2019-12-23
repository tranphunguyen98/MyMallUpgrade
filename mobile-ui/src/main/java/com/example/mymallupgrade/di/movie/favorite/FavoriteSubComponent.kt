package com.example.mymallupgrade.di.movie.favorite

import com.example.mymallupgrade.ui.movie.favorite.FavoriteMovieFragment
import dagger.Subcomponent

/**
 * Created by Tran Phu Nguyen on 12/23/2019.
 */
@Subcomponent(modules = [FavoriteMoviesModule::class])
interface FavoriteSubComponent {
    fun inject(favoriteMovieFragment: FavoriteMovieFragment)
}