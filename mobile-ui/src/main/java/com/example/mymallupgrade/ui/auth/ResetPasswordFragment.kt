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
import com.example.mymallupgrade.databinding.FragmentResetPasswordBinding
import com.google.android.material.snackbar.Snackbar
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance
import timber.log.Timber

class ResetPasswordFragment : Fragment(), AuthListener, KodeinAware {

    var binding : FragmentResetPasswordBinding? = null

    override fun onStarted() {
        Timber.d("onStarted")
        binding!!.btnResetPassword.text = ""
        binding!!.prgLinearResetPassword.progress = 50
        binding!!.prgResetPassword.visibility = View.VISIBLE
        binding!!.tvSentEmailSuccessful.visibility = View.INVISIBLE
    }

    override fun onSuccess() {
        Timber.d("onSuccess")
        binding!!.btnResetPassword.text = resources.getText(R.string.reset_password)
        binding!!.prgLinearResetPassword.progress = 100
        binding!!.prgResetPassword.visibility = View.GONE
        binding!!.tvSentEmailSuccessful.visibility = View.VISIBLE
    }

    override fun onFailure(message: String) {
        binding!!.btnResetPassword.text = resources.getText(R.string.reset_password)
        binding!!.prgResetPassword.visibility = View.GONE
        Snackbar.make(activity!!.findViewById(android.R.id.content),"onFailure $message", Snackbar.LENGTH_LONG).show()
        Timber.d("onFailure $message")
    }


    override val kodein by kodein()

    private var listener: OnResetPasswordFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val factory: AuthViewModelFactory by instance()

        val viewModel = ViewModelProvider(this,factory).get(AuthViewModel::class.java)

        binding = DataBindingUtil.inflate<FragmentResetPasswordBinding>(inflater,R.layout.fragment_reset_password,container,false)

        viewModel.eventJumpToSignIn.observe(viewLifecycleOwner, Observer {isJump ->
            if(isJump) {
                listener?.onGoBackSignInFragment()
            }
        })

        viewModel.authListener = this

        binding!!.viewmodel = viewModel

        return binding!!.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnResetPasswordFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnResetPasswordFragmentInteractionListener")
        }
    }

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
    interface OnResetPasswordFragmentInteractionListener {
        fun onGoBackSignInFragment()
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ResetPasswordFragment()
    }
}
