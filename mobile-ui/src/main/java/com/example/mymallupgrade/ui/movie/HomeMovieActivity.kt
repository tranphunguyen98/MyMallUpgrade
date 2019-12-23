package com.example.mymallupgrade.ui.movie

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.mymallupgrade.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import timber.log.Timber

class HomeMovieActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_movie)

        val toolbar: Toolbar = findViewById(R.id.toolbar_movie)
        setSupportActionBar(toolbar)

        val navView: BottomNavigationView = findViewById(R.id.nav_view_movie)

        navController = findNavController(R.id.nav_host_fragment_movie)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home_movie, R.id.navigation_favorite_movie, R.id.navigation_account
            )
        )
        //setupActionBarWithNavController(navController, appBarConfiguration)
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
           Timber.d("${destination.id} ${destination.label}")
        }
        navView.setOnNavigationItemSelectedListener {menu ->
            Timber.d("${menu.itemId}")
            true
        }
        navView.setupWithNavController(navController)
        Timber.d("onCreated")
    }

    override fun onStart() {
        super.onStart()
        Timber.d("onStart")
    }

    override fun onRestart() {
        super.onRestart()
        Timber.d("onRestart")
    }

    override fun onResume() {
        super.onResume()
        Timber.d("onResume")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        when (navController.currentDestination?.id) {
            R.id.navigation_favorite_movie -> {
                Timber.d("${navController.currentDestination?.id}")
                menuInflater.inflate(R.menu.movie_search_menu, menu)
            }
            R.id.navigation_home_movie -> {
                Timber.d("${navController.currentDestination?.id}")
                menuInflater.inflate(R.menu.movie_home_menu, menu)
            }
        }
        return true
    }

}
