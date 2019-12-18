package com.example.mymallupgrade.ui.movie.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.transition.Slide
import android.transition.TransitionManager
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymallupgrade.R
import com.example.mymallupgrade.common.App
import com.example.mymallupgrade.common.SimpleTransitionEndedCallback
import com.example.mymallupgrade.databinding.ActivityDetailMoviesBinding
import com.example.mymallupgrade.presentation.movie.MovieDetailViewModel
import kotlinx.android.synthetic.main.activity_detail_movies.*
import javax.inject.Inject

class DetailMoviesActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: DetailMovieViewModelFactory

    lateinit var viewModel: MovieDetailViewModel
    lateinit var binding: ActivityDetailMoviesBinding
    lateinit var adapter: VideoAdapter

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

        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_movies)

        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE and View.SYSTEM_UI_FLAG_FULLSCREEN

        (application as App).createDetailComponent().inject(this)

        window.sharedElementEnterTransition.addListener(SimpleTransitionEndedCallback {
            handleViewState()
        })
        factory.movieId = intent.getIntExtra(MOVIE_ID, 0)
        viewModel = ViewModelProvider(this, factory).get(MovieDetailViewModel::class.java)

        if (savedInstanceState == null) {
            viewModel.getMovieDetail()
        }

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        setUpVideoRecyclerview()

       // handleObservable()
    }


    private fun handleObservable() {
        viewModel.movie.observe(this, Observer {
            it.details?.videos?.let{videos ->
                adapter.setData(videos)
            }
        })
    }

    private fun setUpVideoRecyclerview() {
        adapter = VideoAdapter()
        binding.detailsVideoSection.detailsVideos.also {
            it.adapter = adapter
            it.layoutManager = LinearLayoutManager(this)
        }
    }

    private fun handleViewState() {
        val transition = Slide()
        transition.excludeTarget(binding.detailsPoster, true)
        transition.duration = 750
        TransitionManager.beginDelayedTransition(details_root_view, transition)

        binding.detailsTitle.visibility = View.VISIBLE
        binding.detailsReleaseDateLayout.visibility = View.VISIBLE
        binding.detailsScoreLayout.visibility = View.VISIBLE
        binding.detailsOverviewSection.root.visibility = View.VISIBLE
        binding.detailsVideoSection.root.visibility = View.VISIBLE

    }
}
