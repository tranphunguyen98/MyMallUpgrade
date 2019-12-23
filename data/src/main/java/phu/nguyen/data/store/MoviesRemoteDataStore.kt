package phu.nguyen.data.store

import io.reactivex.Completable
import io.reactivex.Observable
import phu.nguyen.data.model.MovieData
import phu.nguyen.data.repository.movie.MoviesDataStore
import phu.nguyen.data.repository.movie.RemoteMovieDataSource
import javax.inject.Inject

/**
 * Created by Tran Phu Nguyen on 12/23/2019.
 */

class MoviesRemoteDataStore @Inject constructor(private val remoteMovieDataSource: RemoteMovieDataSource) :
    MoviesDataStore {
    override fun getMovies(): Observable<List<MovieData>> =
        remoteMovieDataSource.getMovies()

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

    override fun setMovieAsFavorite(movieId: Int): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setMovieAsNotFavorite(movieId: Int): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getFavoriteStatus(movieId: Int): Observable<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}