package com.example.mymallupgrade.ui.auth

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mymallupgrade.R
import com.example.mymallupgrade.databinding.FragmentSignUpBinding
import com.example.mymallupgrade.di.AuthViewModelFactory
import com.example.mymallupgrade.utils.startHomeActivity
import com.google.android.material.snackbar.Snackbar
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance
import timber.log.Timber

class SignUpFragment private constructor(): Fragment(),
    com.example.mymallupgrade.presentation.auth.AuthListener, KodeinAware {
    override val kodein by kodein()

    override fun onStarted() {
        Timber.d("OnStarted")

    }

    override fun onSuccess() {
        Timber.d("onSuccess")
        context!!.startHomeActivity()
    }

    override fun onFailure(message: String) {
        Snackbar.make(activity!!.findViewById(android.R.id.content),"onFailure $message",Snackbar.LENGTH_LONG).show()
        Timber.d("onFailure $message")
    }

    private lateinit var viewModel: com.example.mymallupgrade.presentation.auth.AuthViewModel

    private var listener: OnSignUpFragmentInteractionListener? = null


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnSignUpFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnSignInFragmentInteractionListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val factory : AuthViewModelFactory by instance()
        // Inflate the layout for this fragment
        val binding: FragmentSignUpBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_sign_up,container,false)
        viewModel = ViewModelProvider(this,factory).get(com.example.mymallupgrade.presentation.auth.AuthViewModel::class.java)
        viewModel.authListener = this
        binding.viewmodel = viewModel
        viewModel.eventJumpToSignIn.observe(viewLifecycleOwner, Observer {isJump ->
            if(isJump) {
                listener?.onJumpToSignInFragment()
            }
        })
        return binding.root
    }

//    fun onButtonPressed(uri: Uri) {
//        listener?.onFragmentInteraction(uri)
//    }



    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnSignUpFragmentInteractionListener {
       fun onJumpToSignInFragment()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment SignUpFragment.
         */
        @JvmStatic
        fun newInstance() =
            SignUpFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
            }
    }
}
