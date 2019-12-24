package phu.nguyen.data.repository.movie

import com.example.mymallupgrade.domain.entity.movie.MovieEntity
import com.example.mymallupgrade.domain.repository.movie.MovieRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import phu.nguyen.data.mapper.MovieDetailDomainDataMapper
import phu.nguyen.data.mapper.MovieDomainDataMapper
import phu.nguyen.data.model.MovieData
import phu.nguyen.data.store.MoviesDataStoreFactory
import phu.nguyen.data.store.MoviesRemoteDataStore
import timber.log.Timber

class MovieRepositoryImpl(
    private val cacheMovieDataSource: CacheMovieDataSource,
    private val movieDetailDomainDataMapper: MovieDetailDomainDataMapper,
    private val movieDomainDataMapper: MovieDomainDataMapper,
    private val factory: MoviesDataStoreFactory
) : MovieRepository {

    override fun getMovieById(movieId: Int): Observable<MovieEntity> =
        Observable.zip(
            factory.getCacheDataStore().getFavoriteStatus(movieId),
            factory.getRemoteDataStore().getMovieById(movieId),
            BiFunction<Boolean, MovieData, MovieData> { isFavorite, movieData ->
                Timber.d("isFav $isFavorite moviedata ${movieData.title}")
                return@BiFunction movieData.copy(
                    isFavorite = isFavorite
                )
            }
        ).map {
            movieDetailDomainDataMapper.mapFrom(it)
        }

    override fun getMovies(): Observable<List<MovieEntity>> =
        cacheMovieDataSource.areMoviesCached()
            .flatMap { isCached ->
                val dataStore: MoviesDataStore =
                    factory.getDataStore(isCached)
                Timber.d(dataStore::class.toString())
                if(dataStore is MoviesRemoteDataStore) {
                    Timber.d("Save movies")
                    return@flatMap dataStore.getMovies().flatMap {movies ->
                        factory
                            .getCacheDataStore()
                            .saveMovies(movies)
                            .doOnError {
                                Timber.d("Error save movies: ${it.message}")
                            }
                            .andThen(Observable.just(movies))
                    }
                }
                return@flatMap dataStore.getMovies()
            }
            .map { movies ->
                movies.map {
                    movieDomainDataMapper.mapFrom(it)
                }
            }

    override fun getFavoriteMovies(): Observable<List<MovieEntity>> =
        cacheMovieDataSource.getFavoriteMovies().map {movies ->
            movies.map {
                movieDomainDataMapper.mapFrom(it)
            }
        }


    override fun save(movieEntity: MovieEntity): Completable =
       cacheMovieDataSource.save(movieDomainDataMapper.to(movieEntity))



    override fun search(query: String): Observable<List<MovieEntity>> =
        factory.getRemoteDataStore().searchMovies(query).map {movies ->
            movies.map {
                movieDomainDataMapper.mapFrom(it)
            }
        }


    override fun setMovieAsFavorite(movieId: Int): Single<Int> {
        return factory.getCacheDataStore().setMovieAsFavorite(movieId)
    }

    override fun setMovieAsNotFavorite(movieId: Int): Single<Int> {
        return factory.getCacheDataStore().setMovieAsNotFavorite(movieId)
    }

}