package com.example.mymallupgrade.ui.movie.popular

import android.app.ActivityOptions
import android.os.Bundle
import android.util.Pair
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymallupgrade.R
import com.example.mymallupgrade.common.App
import com.example.mymallupgrade.databinding.FragmentMovieBinding
import com.example.mymallupgrade.presentation.entities.Movie
import com.example.mymallupgrade.presentation.movie.PopularMoviesViewModel
import com.example.mymallupgrade.ui.movie.detail.DetailMoviesActivity
import com.google.android.material.snackbar.Snackbar
import timber.log.Timber
import javax.inject.Inject


class MovieFragment : Fragment() {


    @Inject
    lateinit var factory: PopularMoviesViewModelFactory

    private lateinit var viewmodel: PopularMoviesViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var popularMoviesAdapter: PopularMoviesAdapter
    private lateinit var binding: FragmentMovieBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.application as App).createPopularComponent().inject(this)
        viewmodel = ViewModelProvider(this, factory).get(PopularMoviesViewModel::class.java)

        if (savedInstanceState != null) {
            Timber.d("bundle: not null")
        } else {
            viewmodel.getPopularMovies()
            Timber.d("bundle null")
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_movie, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        popularMoviesAdapter = PopularMoviesAdapter { movie, view ->
            navigateToMovieDetail(movie,view)
        }
        binding.rcPopularMovie.layoutManager = GridLayoutManager(context, 2)
        binding.rcPopularMovie.adapter = popularMoviesAdapter
        handleObserve()
    }

    private fun handleObserve() {
        viewmodel.movies.observe(viewLifecycleOwner, Observer { movies ->
            Timber.d(movies[0].title)
            popularMoviesAdapter.addData(movies)
            binding.prgPopularMovie.visibility = View.GONE

        })

        viewmodel.loadingState.observe(viewLifecycleOwner, Observer { isLoading ->
            if (isLoading) {
                binding.prgPopularMovie.visibility = View.VISIBLE
            }
        })

        viewmodel.errorState.observe(viewLifecycleOwner, Observer { err ->
            if (err != null) {
                Snackbar.make(
                    activity!!.findViewById(android.R.id.content),
                    err.message.toString(),
                    Snackbar.LENGTH_SHORT
                )
            }
            binding.prgPopularMovie.visibility = View.GONE
        })
    }

    private fun navigateToMovieDetail(movie: Movie, view: View) {
        var activityOptions: ActivityOptions? = null
        val imageForTransition: View? = view.findViewById(R.id.img_movie)
        imageForTransition?.let {
            val posterSharedElement: Pair<View, String> = Pair.create(it, getString(R.string.transition_poster))
            activityOptions = ActivityOptions.makeSceneTransitionAnimation(activity,posterSharedElement)
        }

        startActivity(DetailMoviesActivity.newIntent(
            context!!,
            movie.id,
            movie.posterPath),
            activityOptions?.toBundle()
        )

        activity?.overridePendingTransition(0,0)
    }

    override fun onDestroy() {
        super.onDestroy()
        (activity?.application as App).releasePopularComponent()
    }
}