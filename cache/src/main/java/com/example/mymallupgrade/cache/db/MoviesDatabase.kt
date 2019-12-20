package com.example.mymallupgrade.cache.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mymallupgrade.cache.dao.MoviesDao
import com.example.mymallupgrade.cache.model.MovieCache
import timber.log.Timber

/**
 * Created by Tran Phu Nguyen on 12/20/2019.
 */
@Database(entities = [MovieCache::class],version = 1)
abstract class MoviesDatabase: RoomDatabase() {

    abstract fun getMoviesDao() : MoviesDao

    companion object {

        @Volatile
        private var INSTANCE: MoviesDatabase? = null
        private val lock = Any()

        fun getInstance(context: Context): MoviesDatabase {
            if(INSTANCE == null) {
                synchronized(lock) {
                    if(INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            MoviesDatabase::class.java,
                            "movies.db"
                        ).build()
                        Timber.d("MoviesDatabase Successful")
                    }
                }
            }
            return INSTANCE!!
        }
    }

}