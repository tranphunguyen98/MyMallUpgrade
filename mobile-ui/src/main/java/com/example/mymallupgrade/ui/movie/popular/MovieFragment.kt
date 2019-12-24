package com.example.mymallupgrade.ui.movie.popular

import android.app.ActivityOptions
import android.os.Bundle
import android.util.Pair
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymallupgrade.R
import com.example.mymallupgrade.common.App
import com.example.mymallupgrade.databinding.FragmentMovieBinding
import com.example.mymallupgrade.presentation.entities.Movie
import com.example.mymallupgrade.presentation.movie.popular.PopularMoviesViewModel
import com.example.mymallupgrade.presentation.movie.popular.PopularMoviesViewModelFactory
import com.example.mymallupgrade.ui.movie.detail.DetailMoviesActivity
import com.google.android.material.snackbar.Snackbar
import timber.log.Timber
import javax.inject.Inject

class MovieFragment : Fragment() {

    @Inject
    lateinit var factory: PopularMoviesViewModelFactory

    private lateinit var viewmodel: PopularMoviesViewModel
    private lateinit var popularMoviesAdapter: PopularMoviesAdapter
    private lateinit var popularSliderMoviesAdapter: PopularMovieSliderAdapter
    private lateinit var binding: FragmentMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.application as App).createPopularComponent().inject(this)
        viewmodel = ViewModelProvider(this, factory).get(PopularMoviesViewModel::class.java)
        Timber.d("onCreate")
        if (savedInstanceState == null) {
            viewmodel.getPopularMovies()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        Timber.d("onCreateView")

        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_movie, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewmodel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        Timber.d("onViewCreated")

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        Timber.d("onActivityCreated")

        popularMoviesAdapter = PopularMoviesAdapter { movie, view ->
            navigateToMovieDetail(movie,view)
        }
        popularSliderMoviesAdapter = PopularMovieSliderAdapter()

        binding.vpFeather.adapter = popularSliderMoviesAdapter

        binding.rcPopularMovie.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        binding.rcPopularMovie.adapter = popularMoviesAdapter

        handleObserve()
    }

    private fun handleObserve() {
        viewmodel.movies.observe(viewLifecycleOwner, Observer { movies ->
            popularMoviesAdapter.addData(movies)
            popularSliderMoviesAdapter.addData(movies)
        })

        viewmodel.loadingState.observe(viewLifecycleOwner, Observer { isLoading ->
            if (isLoading) {
                binding.prgPopularMovie.visibility = View.VISIBLE
            } else {
                binding.prgPopularMovie.visibility = View.GONE
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


    override fun onStart() {
        super.onStart()
//        Timber.d("onStart")
    }

    override fun onResume() {
        super.onResume()
//        Timber.d("onResume")
    }

    override fun onDestroy() {
        super.onDestroy()
        (activity?.application as App).releasePopularComponent()
//        Timber.d("onDestroy")

    }

    override fun onDetach() {
        super.onDetach()
        //Timber.d("onDetach")

    }
}