package com.example.mymallupgrade.ui.movie.search

import android.app.ActivityOptions
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SearchMovieFragment : Fragment() {

    @Inject
    lateinit var factory: SearchMoviesViewModelFactory

    private lateinit var viewmodel: SearchMoviesViewModel
    private lateinit var searchMoviesAdapter: SearchMoviesAdapter
    private lateinit var binding: FragmentSearchMoviesBinding
    private lateinit var searchSubject: PublishSubject<String>
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity!!.application as App).createSearchComponent().inject(this)

        viewmodel = ViewModelProvider(this,factory).get(SearchMoviesViewModel::class.java)
        searchSubject = PublishSubject.create()

        val disposable = searchSubject
            .debounce(500, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                if(it.isNotEmpty()) {
                    Timber.d("query= $it")
                    viewmodel.search(it)
                } else {
                    viewmodel.search("~")
                }
            }
        compositeDisposable.add(disposable)
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
        binding.searchMoviesEditText.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                //Timber.d("s = ${s.toString()}")
                s?.let {
                    searchSubject.onNext(it.toString())
                }
            }

        })
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

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
        (activity?.application as App).releaseSearchComponent()
    }
}