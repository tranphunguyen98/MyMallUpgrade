package com.example.mymallupgrade.ui.home.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mymallupgrade.data.api.ProviderApi
import com.example.mymallupgrade.data.repository.movie.MovieRepositoryImpl
import com.example.mymallupgrade.data.repository.movie.RemoteMovieDataSourceImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber


class MovieFragment : Fragment() {

    private lateinit var galleryViewModel: MovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        galleryViewModel =
            ViewModelProviders.of(this).get(MovieViewModel::class.java)
        val root = inflater.inflate(com.example.mymallupgrade.R.layout.fragment_movie, container, false)
        val textView: TextView = root.findViewById(com.example.mymallupgrade.R.id.text_gallery)
        galleryViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val providerApi = ProviderApi()

        val remoteMovieDataSourceImpl = RemoteMovieDataSourceImpl(providerApi.create())
        val movieRepository = MovieRepositoryImpl(remoteMovieDataSourceImpl)

        providerApi.create().getPopularMovies()

        val disposable = movieRepository.getMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {list->
                Timber.d("title ${list[0].title}")
            }
    }
}