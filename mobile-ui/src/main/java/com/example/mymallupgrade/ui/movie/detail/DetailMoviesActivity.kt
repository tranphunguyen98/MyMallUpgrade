package com.example.mymallupgrade.ui.movie.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.mymallupgrade.R

class DetailMoviesActivity : AppCompatActivity() {

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
    }
}
