package com.example.mymallupgrade.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mymallupgrade.domain.interactor.LoginWithEmailUseCase
import com.example.mymallupgrade.domain.interactor.SendEmailResetPasswordUseCase
import com.example.mymallupgrade.domain.interactor.SignUpWithEmailUseCase

class AuthViewModelFactory(
    private val loginWithEmailUseCase: LoginWithEmailUseCase,
    private val sendEmailResetPasswordUseCase: SendEmailResetPasswordUseCase,
    private val signUpWithEmailUseCase: SignUpWithEmailUseCase
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            return AuthViewModel(loginWithEmailUseCase,sendEmailResetPasswordUseCase,signUpWithEmailUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}