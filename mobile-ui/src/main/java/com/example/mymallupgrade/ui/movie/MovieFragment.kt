package com.example.mymallupgrade.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mymallupgrade.R
import com.example.mymallupgrade.common.App
import javax.inject.Inject


class MovieFragment : Fragment() {

    @Inject
    lateinit var factory: PopularMoviesViewModelFactory

    private lateinit var viewmodel: PopularMoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.application as App).createPopularComponent().inject(this)
        viewmodel = ViewModelProvider(this,factory).get(PopularMoviesViewModel::class.java)
        viewmodel.getPopularMovies()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root =
            inflater.inflate(R.layout.fragment_movie, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val providerApi = ProviderApi()
//
//        val remoteMovieDataSourceImpl = RemoteMovieDataSourceImpl(providerApi.create())
//        val movieRepository = MovieRepositoryImpl(remoteMovieDataSourceImpl)
//
//        providerApi.create().getPopularMovies()

//        val disposable = movieRepository.getMovies()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe {list->
//                Timber.d("title ${list[0].title}")
//            }
    }
}