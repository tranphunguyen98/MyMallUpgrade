package com.example.mymallupgrade.di.modules

import android.content.Context
import com.example.mymallupgrade.cache.db.MoviesDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Tran Phu Nguyen on 12/20/2019.
 */
@Module
class CacheModule {
//    @Module
//    companion object {
//        @Provides
//        @JvmStatic
//        fun providesDatabase(context: Context): MoviesDatabase {
//            return MoviesDatabase.getInstance(context)
//        }
//    }
//
    @Provides
    @Singleton
    fun providesDatabase(context: Context): MoviesDatabase {
        return MoviesDatabase.getInstance(context)
    }

//    @Binds
//    abstract fun bindMovieCacheDataSource(movieDataSource: CacheMovieDataSourceImpl): CacheMovieDataSource
}