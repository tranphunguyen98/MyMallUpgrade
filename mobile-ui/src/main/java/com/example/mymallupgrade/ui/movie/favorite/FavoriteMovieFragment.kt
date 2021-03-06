package com.example.mymallupgrade.ui.movie.favorite

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
import com.example.mymallupgrade.databinding.FragmentFavoriteMovieBinding
import com.example.mymallupgrade.presentation.entities.Movie
import com.example.mymallupgrade.presentation.movie.favorite.FavoriteMoviesViewModel
import com.example.mymallupgrade.presentation.movie.favorite.FavoriteMoviesViewModelFactory
import com.example.mymallupgrade.ui.movie.detail.DetailMoviesActivity
import timber.log.Timber
import javax.inject.Inject

class FavoriteMovieFragment : Fragment() {

    @Inject
    lateinit var factory: FavoriteMoviesViewModelFactory

    private lateinit var viewmodel: FavoriteMoviesViewModel
    private lateinit var favoriteMoviesAdapter: FavoriteMoviesAdapter
    private lateinit var binding: FragmentFavoriteMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity!!.application as App).createFavoriteComponent().inject(this)

        viewmodel = ViewModelProvider(this,factory).get(FavoriteMoviesViewModel::class.java)

        if(savedInstanceState == null) {
            viewmodel.getPopularMovies()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_favorite_movie, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewmodel

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpRecyclerView()
        handleObservable()
    }

    private fun setUpRecyclerView() {
        favoriteMoviesAdapter = FavoriteMoviesAdapter{ movie, view ->
            navigateToMovieDetail(movie,view)
        }
        binding.rcFavoriteMovie.also {
            it.adapter = favoriteMoviesAdapter
            it.layoutManager = LinearLayoutManager(context)
        }
    }

    private fun handleObservable() {
        viewmodel.favoriteMoviesState.observe(viewLifecycleOwner, Observer {
            if(it.isLoading) {
                binding.prgFavoriteMovie.visibility = View.VISIBLE
            } else {
                binding.prgFavoriteMovie.visibility = View.GONE
            }

            if(it.error != null) {
                Timber.d("Error Favorite ${it.error!!.message}")
            }
        })
    }

    private fun navigateToMovieDetail(movie: Movie, view: View) {
        var activityOptions: ActivityOptions? = null
        val imageForTransition: View? = view.findViewById(R.id.img_movie_favorite)
        imageForTransition?.let {
            val posterSharedElement: Pair<View, String> = Pair.create(it, getString(R.string.transition_poster))
            activityOptions = ActivityOptions.makeSceneTransitionAnimation(activity,posterSharedElement)
        }

        startActivity(
            DetailMoviesActivity.newIntent(
                context!!,
                movie.id,
                movie.posterPath),
            activityOptions?.toBundle()
        )

        activity?.overridePendingTransition(0,0)
    }
}