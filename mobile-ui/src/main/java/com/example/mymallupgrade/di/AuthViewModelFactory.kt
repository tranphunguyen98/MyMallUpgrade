package com.example.mymallupgrade.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mymallupgrade.domain.interactor.auth.LoginWithEmailUseCase
import com.example.mymallupgrade.domain.interactor.auth.SendEmailResetPasswordUseCase
import com.example.mymallupgrade.domain.interactor.auth.SignUpWithEmailUseCase
import com.example.mymallupgrade.presentation.auth.AuthViewModel

class AuthViewModelFactory(
    private val loginWithEmailUseCase: LoginWithEmailUseCase,
    private val sendEmailResetPasswordUseCase: SendEmailResetPasswordUseCase,
    private val signUpWithEmailUseCase: SignUpWithEmailUseCase
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            return AuthViewModel(
                loginWithEmailUseCase,
                sendEmailResetPasswordUseCase,
                signUpWithEmailUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}