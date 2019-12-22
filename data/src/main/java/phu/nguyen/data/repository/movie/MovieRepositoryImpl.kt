package phu.nguyen.data.repository.movie

import com.example.mymallupgrade.domain.entity.movie.MovieEntity
import com.example.mymallupgrade.domain.repository.movie.MovieRepository
import io.reactivex.Completable
import io.reactivex.Observable
import phu.nguyen.data.mapper.MovieDetailDomainDataMapper
import phu.nguyen.data.mapper.MovieDomainDataMapper
import timber.log.Timber

class MovieRepositoryImpl(
    private val cacheMovieDataSource: CacheMovieDataSource,
    private val remoteMovieDataSource: RemoteMovieDataSource,
    private val movieDetailDomainDataMapper: MovieDetailDomainDataMapper,
    private val movieDomainDataMapper: MovieDomainDataMapper
) : MovieRepository {

    override fun getMovieById(movieId: Int): Observable<MovieEntity> =
        remoteMovieDataSource.getMovieById(movieId).map {
            movieDetailDomainDataMapper.mapFrom(it)
        }

    override fun getMovies(): Observable<List<MovieEntity>> =
        remoteMovieDataSource.getMovies()
            .flatMap { movies ->
                val andThen = cacheMovieDataSource
                    .saveAll(movies)
                    .doOnError {
                        Timber.d("Error: ${it.message}")
                    }
                    .doOnComplete {
                        Timber.d("Luu movies thanh cong!")
                    }
                    .andThen(Observable.just(movies))
                andThen
            }
            .map { movies ->
                movies.map {
                    movieDomainDataMapper.mapFrom(it)
                }
            }

    override fun save(movieEntity: MovieEntity): Completable {
        return cacheMovieDataSource.save(movieDomainDataMapper.to(movieEntity))
    }


    override fun search(query: String): Observable<List<MovieEntity>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}