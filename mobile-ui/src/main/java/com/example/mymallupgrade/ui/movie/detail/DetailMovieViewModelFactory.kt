package com.example.mymallupgrade.ui.movie.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mymallupgrade.domain.common.Mapper
import com.example.mymallupgrade.domain.entity.movie.MovieEntity
import com.example.mymallupgrade.domain.interactor.movie.GetMovieDetail
import com.example.mymallupgrade.domain.interactor.movie.SaveFavoriteMovie
import com.example.mymallupgrade.domain.interactor.movie.SetMovieAsFavorite
import com.example.mymallupgrade.domain.interactor.movie.SetMovieAsNotFavorite
import com.example.mymallupgrade.presentation.entities.Movie
import com.example.mymallupgrade.presentation.movie.MovieDetailViewModel

/**
 * Created by Tran Phu Nguyen on 12/17/2019.
 */
class DetailMovieViewModelFactory(
    private val setMovieAsFavorite: SetMovieAsFavorite,
    private val setMovieAsNotFavorite: SetMovieAsNotFavorite,
    private val saveFavoriteMovie: SaveFavoriteMovie,
    private val getMovieDetail: GetMovieDetail,
    private val mapper: Mapper<MovieEntity, Movie>
) : ViewModelProvider.Factory {
    var movieId: Int = -1
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieDetailViewModel(
            setMovieAsFavorite,
            setMovieAsNotFavorite,
            saveFavoriteMovie,
            getMovieDetail,
            mapper,
            movieId
        ) as T
    }
}