package com.example.mymallupgrade.ui.movie.search

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
import com.example.mymallupgrade.databinding.FragmentSearchMoviesBinding
import com.example.mymallupgrade.presentation.entities.Movie
import com.example.mymallupgrade.presentation.movie.search.SearchMoviesViewModel
import com.example.mymallupgrade.presentation.movie.search.SearchMoviesViewModelFactory
import com.example.mymallupgrade.ui.movie.detail.DetailMoviesActivity
import timber.log.Timber
import javax.inject.Inject

class SearchMovieFragment : Fragment() {

    @Inject
    lateinit var factory: SearchMoviesViewModelFactory

    private lateinit var viewmodel: SearchMoviesViewModel
    private lateinit var searchMoviesAdapter: SearchMoviesAdapter
    private lateinit var binding: FragmentSearchMoviesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity!!.application as App).createSearchComponent().inject(this)

        viewmodel = ViewModelProvider(this,factory).get(SearchMoviesViewModel::class.java)

        if(savedInstanceState == null) {
            viewmodel.search("star")
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_search_movies, container, false)
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
        searchMoviesAdapter = SearchMoviesAdapter(){ movie, view ->
            navigateToMovieDetail(movie,view)
        }

        binding.rcSearchMovie.also {
            it.adapter = searchMoviesAdapter
            it.layoutManager = LinearLayoutManager(context)
        }
    }

    private fun handleObservable() {
        viewmodel.searchMoviesState.observe(viewLifecycleOwner, Observer {
            if(it.isLoading) {
                binding.searchMoviesProgress.visibility = View.VISIBLE
            } else {
                binding.searchMoviesProgress.visibility = View.GONE
            }

            if(it.error != null) {
                Timber.d("Error Search ${it.error!!.message}")
            }
        })
    }

    private fun navigateToMovieDetail(movie: Movie, view: View) {
        var activityOptions: ActivityOptions? = null
        val imageForTransition: View? = view.findViewById(R.id.img_movie_search)
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