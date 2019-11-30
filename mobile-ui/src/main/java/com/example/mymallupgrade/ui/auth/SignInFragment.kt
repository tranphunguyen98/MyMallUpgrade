package com.example.mymallupgrade.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.mymallupgrade.R
import com.example.mymallupgrade.databinding.FragmentSignInBinding
import com.example.mymallupgrade.di.AuthViewModelFactory
import com.example.mymallupgrade.domain.common.Result
import com.example.mymallupgrade.domain.common.UtilCheckValid
import com.example.mymallupgrade.presentation.auth.AuthViewModel
import com.example.mymallupgrade.utils.startHomeActivity
import com.google.android.material.snackbar.Snackbar
import io.reactivex.disposables.CompositeDisposable
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance
import timber.log.Timber


class SignInFragment : Fragment(), KodeinAware {
    override val kodein by kodein()
    private val viewModel: AuthViewModel by lazy {
        val factory: AuthViewModelFactory by instance()
        ViewModelProvider(
            this,
            factory
        ).get(AuthViewModel::class.java)
    }
    private val compositeDisposable = CompositeDisposable()
    lateinit var binding: FragmentSignInBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_in, container, false)
        binding.viewmodel = viewModel

        handleDirection()

        checkValidEdittext()

        return binding.root
    }

    private fun handleDirection() {

        binding.tvDontHaveAccount.setOnClickListener {
            val action = SignInFragmentDirections.actionSignInFragmentToSignUpFragment()
            Navigation.findNavController(it).navigate(action)
        }

        binding.tvForgotPassword.setOnClickListener {
            val action = SignInFragmentDirections.actionSignInFragmentToResetPasswordFragment()
            Navigation.findNavController(it).navigate(action)
        }

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
    }

    private fun checkValidEdittext() {
        compositeDisposable.add(
            ValidEditedObservable.execute(binding.edtEmail, binding.edtEmailLayout)
                .subscribe { string ->
                    when (val result = UtilCheckValid.checkEmail(string)) {
                        is Result.Failure -> {
                            binding.edtEmailLayout.error = result.throwable.message
                            disableButtonSignIn()
                        }
                        is Result.Success -> {
                            binding.edtEmailLayout.error = null
                            activeButtonSignIn()
                        }
                    }
                }
        )

        compositeDisposable.add(
            ValidEditedObservable.execute(binding.edtPassword, binding.edtPasswordLayout)
                .subscribe { string ->
                    when (val result = UtilCheckValid.checkPassword(string)) {
                        is Result.Failure -> {
                            binding.edtPasswordLayout.error = result.throwable.message
                            disableButtonSignIn()
                        }
                        is Result.Success -> {
                            binding.edtPasswordLayout.error = null
                            activeButtonSignIn()
                        }
                    }
                }
        )
    }

    private fun disableButtonSignIn() {
        Timber.d("disableButtonSignIn")
        binding.btnSignIn.isEnabled = false
        binding.btnSignIn.setTextColor(context!!.resources.getColor(com.example.mymallupgrade.R.color.btnTextColorDisable))
        binding.btnSignIn.setBackgroundColor(context!!.resources.getColor(com.example.mymallupgrade.R.color.btnDisable))
    }

    private fun activeButtonSignIn() {
        if (binding.edtEmail.text.toString().isNotEmpty() && binding.edtEmailLayout.error.isNullOrEmpty() && binding.edtPasswordLayout.error.isNullOrEmpty()) {
            Timber.d("activeButtonSignIn " + binding.edtEmail.text)
            binding.btnSignIn.isEnabled = true
            binding.btnSignIn.setTextColor(context!!.resources.getColor(R.color.colorPrimary))
            ViewCompat.setBackground(binding.btnSignIn, context!!.getDrawable(R.drawable.btn_whtie))
        }
    }

    override fun onDetach() {
        super.onDetach()
        Timber.d("onDetach")
        compositeDisposable.clear()
    }

}
