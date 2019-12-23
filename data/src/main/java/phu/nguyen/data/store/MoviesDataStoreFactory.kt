package phu.nguyen.data.store

import phu.nguyen.data.repository.movie.MoviesDataStore
import javax.inject.Inject

/**
 * Created by Tran Phu Nguyen on 12/23/2019.
 */
class MoviesDataStoreFactory @Inject constructor(
    private val moviesCacheDataStore: MoviesCacheDataStore,
    private val moviesRemoteDataStore: MoviesRemoteDataStore
) {

    fun getDataStore(moviesCached: Boolean): MoviesDataStore {
        return if(moviesCached) {
            moviesCacheDataStore
        } else {
            moviesRemoteDataStore
        }
    }

    fun getCacheDataStore(): MoviesCacheDataStore {
        return moviesCacheDataStore
    }

    fun getRemoteDataStore(): MoviesRemoteDataStore {
        return moviesRemoteDataStore
    }
}
