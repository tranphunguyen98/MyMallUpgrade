package com.example.mymallupgrade.ui.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.mymallupgrade.R
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity()
    , SignUpFragment.OnSignUpFragmentInteractionListener {

    private lateinit var fragmentManager: FragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        fragmentManager = supportFragmentManager

        addFragment(SignUpFragment.newInstance())
    }

    private fun addFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(frame_layout.id,fragment)
        fragmentTransaction.commit()

    }

    override fun onJumpToSignInFragment() {
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,SignInFragment.newInstance()).commit()
    }

}
