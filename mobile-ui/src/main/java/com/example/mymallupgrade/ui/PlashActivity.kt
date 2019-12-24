package com.example.mymallupgrade.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mymallupgrade.R
import com.example.mymallupgrade.ui.movie.HomeMovieActivity

class PlashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plash)

        startActivity(
            Intent(this,HomeMovieActivity::class.java)
        )
        this.finish()
    }
}
