package com.example.mymallupgrade.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mymallupgrade.domain.interactor.SignInWithEmailUseCase
import com.example.mymallupgrade.domain.interactor.SendEmailResetPasswordUseCase
import com.example.mymallupgrade.domain.interactor.SignUpWithEmailUseCase
import com.example.mymallupgrade.presentation.auth.AuthViewModel

class AuthViewModelFactory(
    private val signInWithEmailUseCase: SignInWithEmailUseCase,
    private val sendEmailResetPasswordUseCase: SendEmailResetPasswordUseCase,
    private val signUpWithEmailUseCase: SignUpWithEmailUseCase
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            return AuthViewModel(
                signInWithEmailUseCase,
                sendEmailResetPasswordUseCase,
                signUpWithEmailUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}