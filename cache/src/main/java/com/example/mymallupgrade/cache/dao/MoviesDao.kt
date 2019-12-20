package com.example.mymallupgrade.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.mymallupgrade.cache.model.MovieCache

/**
 * Created by Tran Phu Nguyen on 12/20/2019.
 */
@Dao
interface MoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovie(movie: MovieCache)
}