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
import com.example.mymallupgrade.databinding.FragmentSignInBinding
import com.example.mymallupgrade.di.AuthViewModelFactory
import com.example.mymallupgrade.domain.UtilCheckValid
import com.example.mymallupgrade.utils.startHomeActivity
import com.google.android.material.snackbar.Snackbar
import io.reactivex.disposables.CompositeDisposable
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance
import timber.log.Timber

class SignInFragment : Fragment(), KodeinAware {
    override val kodein by kodein()
    private lateinit var viewModel: com.example.mymallupgrade.presentation.auth.AuthViewModel
    private var listener: OnSignInFragmentInteractionListener? = null
    private val compositeDisposable = CompositeDisposable()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnSignInFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnSignInFragmentInteractionListener")
        }

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

        viewModel = ViewModelProvider(
            this,
            factory
        ).get(com.example.mymallupgrade.presentation.auth.AuthViewModel::class.java)

        binding.viewmodel = viewModel

        viewModel.eventJumpToSignUp.observe(viewLifecycleOwner, Observer { isJump ->
            if (isJump) {
                listener?.onJumpToSignUpFragment()
            }
        })

        viewModel.eventJumpToForgotPassword.observe(viewLifecycleOwner, Observer { isJump ->
            if (isJump) {
                listener?.onJumpToForgotPasswordFragment()
            }
        })

        viewModel.errorState.observe(viewLifecycleOwner, Observer { message ->
            if (message.isNotEmpty()) {
                Snackbar.make(
                    activity!!.findViewById(android.R.id.content),
                    message,
                    Snackbar.LENGTH_LONG
                ).show()
            }
        })

        viewModel.successState.observe(viewLifecycleOwner, Observer { isSuccess ->
            if (isSuccess) {
                context!!.startHomeActivity()
            }
        })

        compositeDisposable.add(ValidEditedObservable.execute(binding.edtEmail,UtilCheckValid::checkEmail))
        compositeDisposable.add(ValidEditedObservable.execute(binding.edtPassword,UtilCheckValid::checkPassword))

        binding.btnSignIn.setTextColor(context!!.resources.getColor(R.color.colorAccent))

        return binding.root
    }

    override fun onDetach() {
        super.onDetach()
        Timber.d("onDetach")
        compositeDisposable.clear()
        listener = null
    }

    interface OnSignInFragmentInteractionListener {
        fun onJumpToSignUpFragment()
        fun onJumpToForgotPasswordFragment()
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            SignInFragment()
    }

}
