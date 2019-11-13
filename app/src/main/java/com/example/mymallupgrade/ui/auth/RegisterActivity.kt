package com.example.mymallupgrade.ui.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.mymallupgrade.R
import com.example.mymallupgrade.utils.Constant
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity()
    , SignInFragment.OnSignInFragmentInteractionListener
    {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        addFragment(SignInFragment.newInstance())
    }

    private fun addFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(frame_layout.id,fragment, Constant.TAG_SIGN_IN_FRAG)
        fragmentTransaction.commit()
    }


}
