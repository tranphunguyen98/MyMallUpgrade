package com.example.mymallupgrade.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.mymallupgrade.R
import com.example.mymallupgrade.databinding.FragmentResetPasswordBinding
import com.example.mymallupgrade.di.auth.AuthViewModelFactory
import com.example.mymallupgrade.presentation.auth.AuthViewModel
import com.google.android.material.snackbar.Snackbar
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance
import timber.log.Timber

class ResetPasswordFragment : Fragment(), KodeinAware {

    override val kodein by kodein()
    private val viewModel: AuthViewModel by lazy {
        val factory: AuthViewModelFactory by instance()
        ViewModelProvider(
            this,
            factory
        ).get(AuthViewModel::class.java)
    }

    lateinit var binding: FragmentResetPasswordBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate<FragmentResetPasswordBinding>(
            inflater,
            R.layout.fragment_reset_password,
            container,
            false
        )
        binding.viewmodel = viewModel

        handleDirection()
        handleObserve()

        return binding.root
    }

    private fun handleDirection() {

        binding.btnGoBack.setOnClickListener {
            Navigation.findNavController(it).popBackStack()
        }
    }


    private fun handleObserve() {
        viewModel.loadingState.observe(viewLifecycleOwner, Observer { isLoading ->
            if (isLoading == View.VISIBLE) {
                binding.btnResetPassword.text = ""
                binding.prgLinearResetPassword.progress = 50
                binding.prgResetPassword.visibility = View.VISIBLE
                binding.tvSentEmailSuccessful.visibility = View.INVISIBLE
            }
        })

        viewModel.errorState.observe(viewLifecycleOwner, Observer { message ->
            if (message.isNotEmpty()) {
                binding.btnResetPassword.text = resources.getText(R.string.reset_password)
                binding.prgResetPassword.visibility = View.GONE
                Snackbar.make(
                    activity!!.findViewById(android.R.id.content),
                    "onFailure $message",
                    Snackbar.LENGTH_LONG
                ).show()
                Timber.d("onFailure $message")
            }
        })

        viewModel.successState.observe(viewLifecycleOwner, Observer { isSuccess ->
            if (isSuccess) {
                Timber.d("onSuccess")
                binding.btnResetPassword.text = resources.getText(R.string.reset_password)
                binding.prgLinearResetPassword.progress = 100
                binding.prgResetPassword.visibility = View.GONE
                binding.tvSentEmailSuccessful.visibility = View.VISIBLE
            }
        })
    }
}
