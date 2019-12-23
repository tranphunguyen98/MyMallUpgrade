package phu.nguyen.data.store

import io.reactivex.Completable
import io.reactivex.Observable
import phu.nguyen.data.model.MovieData
import phu.nguyen.data.repository.movie.CacheMovieDataSource
import phu.nguyen.data.repository.movie.MoviesDataStore
import javax.inject.Inject

/**
 * Created by Tran Phu Nguyen on 12/23/2019.
 */

class MoviesCacheDataStore @Inject constructor(private val cacheMovieDataSource: CacheMovieDataSource) :
    MoviesDataStore {
    override fun getMovies(): Observable<List<MovieData>> =
        cacheMovieDataSource.getMovies()

    override fun getMovieById(movieId: Int): Observable<MovieData> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun saveMovies(movies: List<MovieData>): Completable =
        cacheMovieDataSource.saveMovies(movies)


    override fun clearMovies(): Completable =
        cacheMovieDataSource.clearMovies()

    override fun getFavoriteMovies(): Observable<List<MovieData>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setMovieAsFavorite(movieId: Int): Completable =
        cacheMovieDataSource.setFavoriteStatus(true, movieId)

    override fun setMovieAsNotFavorite(movieId: Int): Completable =
        cacheMovieDataSource.setFavoriteStatus(false, movieId)

    override fun getFavoriteStatus(movieId: Int): Observable<Boolean> =
        cacheMovieDataSource.getFavoriteStatus(movieId)

}