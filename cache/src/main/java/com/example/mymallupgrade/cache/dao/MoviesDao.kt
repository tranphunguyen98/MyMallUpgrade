package com.example.mymallupgrade.cache.dao

import androidx.room.*
import com.example.mymallupgrade.cache.model.MovieCache
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable

/**
 * Created by Tran Phu Nguyen on 12/20/2019.
 */
@Dao
interface MoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovie(movie: MovieCache): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovies(movies: List<MovieCache>): Completable

    @Delete
    fun removeMovie(movie: MovieCache): Completable

    @Query("DELETE FROM movies")
    fun clear(): Completable

    @Query("UPDATE movies SET isFavorite = :isFavorite WHERE id = :movieId")
    fun setFavoriteStatus(isFavorite: Boolean, movieId: Int): Completable

    @Query("SELECT isFavorite FROM movies WHERE id = :movieId")
    fun getFavoriteStatus(movieId: Int): Maybe<Boolean>

    @Query("SELECT * FROM movies ORDER BY popularity DESC")
    fun getMovies(): Observable<List<MovieCache>>

    @Query("SELECT * FROM movies WHERE isFavorite = 1")
    fun getFavoriteMovies(): Observable<List<MovieCache>>

    @Query("SELECT* FROM movies LIMIT 1")
    fun getOneMovie(): MovieCache?

    @Query("SELECT * FROM movies")
    fun gettMovies(): List<MovieCache>
}