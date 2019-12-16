package com.example.mymallupgrade.ui.movie.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.mymallupgrade.R
import com.example.mymallupgrade.common.App
import com.example.mymallupgrade.domain.interactor.movie.GetMovieDetail
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class DetailMoviesActivity : AppCompatActivity() {

    @Inject
    lateinit var getMovieDetail: GetMovieDetail

    companion object {
        private const val MOVIE_ID = "extra_movie_id"
        private const val MOVIE_POSTER_URL = "extra_movie_poster_url"

        fun newIntent(context: Context, movieId: Int, posterUrl: String?): Intent {
            val i = Intent(context, DetailMoviesActivity::class.java)
            i.putExtra(MOVIE_ID, movieId)
            i.putExtra(MOVIE_POSTER_URL, posterUrl)
            return i
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movies)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE and View.SYSTEM_UI_FLAG_FULLSCREEN

        (application as App).createDetailComponent().inject(this)


        val disposable = getMovieDetail(512200)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe (
                {
                    Timber.d("details ${it.value?.overview}")
                },
                {
                    Timber.d("error: ${it.message}" )
                }
            )
//        val module = NetworkModule()
//        val component = DaggerMainComponent.builder().networkModule(module).build()
//        val api = component.provideApi()
//        val disposable = api.getMovieDetail(512200)
//            .flatMap { detail -> Observable.just(MovieDetailRemoteToEntityMapper().mapFrom(detail)) }
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribeOn(Schedulers.io())
//            .subscribe (
//                {
//                    Timber.d("details ${it.overview}")
//                },
//                {
//                    Timber.d("error: ${it.message}" )
//                }
//            )
    }
}
