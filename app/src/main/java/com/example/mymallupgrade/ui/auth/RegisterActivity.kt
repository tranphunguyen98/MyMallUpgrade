package com.example.mymallupgrade.ui.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.example.mymallupgrade.R
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity()
    , SignUpFragment.OnSignUpFragmentInteractionListener
    , SignInFragment.OnSignInFragmentInteractionListener
    , ResetPasswordFragment.OnResetPasswordFragmentInteractionListener{


    private lateinit var fragmentManager: FragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        fragmentManager = supportFragmentManager

        addSignInFragment()
    }

    private fun addSignInFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(frame_layout.id,SignUpFragment.newInstance())
        fragmentTransaction.commit()

    }

    override fun onJumpToSignInFragment() {
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction
            .setCustomAnimations(R.anim.slide_from_left, R.anim.slide_out_from_right)
            .replace(R.id.frame_layout,SignInFragment.newInstance())
            .commit()
    }

    override fun onGoBackSignInFragment() {
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction
            .setCustomAnimations(R.anim.slide_from_left, R.anim.slide_out_from_right)
            .replace(R.id.frame_layout,SignInFragment.newInstance())
            .commit()
    }

    override fun onJumpToSignUpFragment() {
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction
            .setCustomAnimations(R.anim.slide_from_right, R.anim.slide_out_from_left)
            .replace(R.id.frame_layout,SignUpFragment.newInstance())
            .commit()
    }

    override fun onJumpToForgotPasswordFragment() {
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction
            .setCustomAnimations(R.anim.slide_from_right, R.anim.slide_out_from_left)
            .replace(R.id.frame_layout,ResetPasswordFragment.newInstance())
            .commit()
    }

}
