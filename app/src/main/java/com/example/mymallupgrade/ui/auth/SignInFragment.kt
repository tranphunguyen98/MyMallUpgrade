package com.example.mymallupgrade.ui.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mymallupgrade.R
import com.example.mymallupgrade.databinding.FragmentSignInTBinding
import com.example.mymallupgrade.ui.home.HomeActivity
import com.google.android.material.snackbar.Snackbar
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance
import timber.log.Timber

class SignInFragment : Fragment(), AuthListener, KodeinAware {
    override val kodein by kodein()

    override fun onStarted() {
        Timber.d("OnStarted")

    }

    override fun onSuccess() {
        Timber.d("onSuccess")
        activity!!.startActivity(Intent(context,HomeActivity::class.java))
    }

    override fun onFailure(message: String) {
        Snackbar.make(activity!!.findViewById(android.R.id.content),"onFailure $message",Snackbar.LENGTH_LONG).show()
        Timber.d("onFailure $message")
    }

    private lateinit var viewModel: AuthViewModel

    private var listener: OnSignInFragmentInteractionListener? = null
    private lateinit var binding : FragmentSignInTBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnSignInFragmentInteractionListener) {
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
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_sign_in_t,container,false)

        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(activity!!,factory).get(AuthViewModel::class.java)
        viewModel.authListener = this

        binding.viewmodel = viewModel

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
    interface OnSignInFragmentInteractionListener {
       // TODO: Update argument type and name
       // fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment SignInFragment.
         */
        @JvmStatic
        fun newInstance() =
            SignInFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
            }
    }
}
