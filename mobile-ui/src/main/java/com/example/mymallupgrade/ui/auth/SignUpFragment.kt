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
import com.example.mymallupgrade.databinding.FragmentSignUpBinding
import com.example.mymallupgrade.di.auth.AuthViewModelFactory
import com.example.mymallupgrade.presentation.auth.AuthViewModel
import com.example.mymallupgrade.utils.startHomeActivity
import com.google.android.material.snackbar.Snackbar
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class SignUpFragment : Fragment(), KodeinAware {
    override val kodein by kodein()

    private val viewModel: AuthViewModel by lazy {
        val factory: AuthViewModelFactory by instance()
        ViewModelProvider(this, factory).get(AuthViewModel::class.java)
    }
    lateinit var binding: FragmentSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false)
        binding.viewmodel = viewModel

        handleDirection()
        handleObserve()

        return binding.root
    }

    private fun handleDirection() {
        binding.tvAlreadyHaveAnAccount.setOnClickListener {
            Navigation.findNavController(it).popBackStack()
        }
    }

    private fun handleObserve() {
        viewModel.loadingState.observe(viewLifecycleOwner, Observer { isLoading ->
            binding.prgSignUp.visibility = isLoading
        })

        viewModel.errorState.observe(viewLifecycleOwner, Observer { message ->
            if (message.isNotEmpty()) {
                Snackbar.make(
                    activity!!.findViewById(android.R.id.content),
                    "onFailure $message",
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


}
