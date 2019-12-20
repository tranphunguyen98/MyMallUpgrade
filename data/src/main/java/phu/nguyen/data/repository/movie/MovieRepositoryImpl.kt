package phu.nguyen.data.repository.movie

import com.example.mymallupgrade.domain.entity.movie.MovieEntity
import com.example.mymallupgrade.domain.repository.movie.MovieRepository
import io.reactivex.Observable
import phu.nguyen.data.mapper.MovieDetailDomainDataMapper
import phu.nguyen.data.mapper.MovieDomainDataMapper

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
        remoteMovieDataSource.getMovies().map {
            it.map { movieData ->
                movieDomainDataMapper.mapFrom(movieData)
            }
        }

    override fun save(movieEntity: MovieEntity) {
        cacheMovieDataSource.save(movieDomainDataMapper.to(movieEntity))
    }


    override fun search(query: String): Observable<List<MovieEntity>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}