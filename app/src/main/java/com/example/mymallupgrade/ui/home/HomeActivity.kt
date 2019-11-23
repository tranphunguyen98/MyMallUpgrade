package com.example.mymallupgrade.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mymallupgrade.R
import com.google.firebase.auth.FirebaseAuth

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FirebaseAuth.getInstance().signOut()
    }
}
