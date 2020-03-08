package phu.nguyen.data.store

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import phu.nguyen.data.model.MovieData
import phu.nguyen.data.repository.movie.MoviesDataStore
import phu.nguyen.data.repository.movie.RemoteMovieDataSource
import javax.inject.Inject

/**
 * Created by Tran Phu Nguyen on 12/23/2019.
 */

class MoviesRemoteDataStore @Inject constructor(private val remoteMovieDataSource: RemoteMovieDataSource) :
    MoviesDataStore {
    override fun getPopularMovies(): Observable<List<MovieData>> =
        remoteMovieDataSource.getPopularMovies()

    override fun getNowPlayingMovies(): Observable<List<MovieData>> =
        remoteMovieDataSource.getNowPlayingMovies()

    override fun getMovieById(movieId: Int): Observable<MovieData> =
        remoteMovieDataSource.getMovieById(movieId)

    override fun saveMovies(movies: List<MovieData>): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clearMovies(): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getFavoriteMovies(): Observable<List<MovieData>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setMovieAsFavorite(movieId: Int): Single<Int> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setMovieAsNotFavorite(movieId: Int): Single<Int> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getFavoriteStatus(movieId: Int): Observable<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun searchMovies(query: String): Observable<List<MovieData>> =
        remoteMovieDataSource.searchMovies(query)

}