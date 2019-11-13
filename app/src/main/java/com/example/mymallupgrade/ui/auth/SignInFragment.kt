package com.example.mymallupgrade.ui.auth

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mymallupgrade.R
import com.example.mymallupgrade.databinding.FragmentSignInBinding
import com.example.mymallupgrade.utils.startHomeActivity
import com.google.android.material.snackbar.Snackbar
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance
import timber.log.Timber

class SignInFragment private constructor(): Fragment(), AuthListener, KodeinAware {
    override val kodein by kodein()

    private lateinit var viewModel: AuthViewModel

    private var listener: OnSignInFragmentInteractionListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
//        if (context is SignUpFragment.OnSignUpFragmentInteractionListener) {
//         //   listener = context
//        } else {
//            throw RuntimeException("$context must implement OnSignUpFragmentInteractionListener")
//        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val factory: AuthViewModelFactory by instance()
        // Inflate the layout for this fragment
        val binding: FragmentSignInBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_sign_in, container, false)

        viewModel = ViewModelProvider(this, factory).get(AuthViewModel::class.java)
        viewModel.authListener = this

        binding.viewmodel = viewModel

        return binding.root
    }

    override fun onDetach() {
        super.onDetach()
        //listener = null
    }

    override fun onStarted() {
        Timber.d("OnStarted")
    }

    override fun onSuccess() {
        Timber.d("onSuccess")
        context!!.startHomeActivity()
    }

    override fun onFailure(message: String) {
        Snackbar.make(
            activity!!.findViewById(android.R.id.content),
            "onFailure $message",
            Snackbar.LENGTH_LONG
        ).show()
        Timber.d("onFailure $message")
    }

    interface OnSignInFragmentInteractionListener {
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            SignInFragment()
    }

}
