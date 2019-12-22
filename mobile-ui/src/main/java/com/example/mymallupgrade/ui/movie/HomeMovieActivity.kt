package com.example.mymallupgrade.ui.movie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.mymallupgrade.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeMovieActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_movie)

        val toolbar: Toolbar = findViewById(R.id.toolbar_movie)
        setSupportActionBar(toolbar)

        val navView: BottomNavigationView = findViewById(R.id.nav_view_movie)

        val navController = findNavController(R.id.nav_host_fragment_movie)

        val appBarConfiguration =  AppBarConfiguration(setOf(
        R.id.navigation_home_movie,R.id.navigation_favorite_movie,R.id.navigation_account
        ))
        setupActionBarWithNavController(navController,appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}
