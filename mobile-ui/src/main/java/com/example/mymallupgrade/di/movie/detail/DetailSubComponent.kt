package com.example.mymallupgrade.di.movie.detail

import com.example.mymallupgrade.ui.movie.detail.DetailMoviesActivity
import dagger.Subcomponent

/**
 * Created by Tran Phu Nguyen on 12/16/2019.
 */

@Subcomponent(modules = [MovieDetailModule::class])
interface DetailSubComponent {
    fun inject(detailMoviesActivity: DetailMoviesActivity)
}