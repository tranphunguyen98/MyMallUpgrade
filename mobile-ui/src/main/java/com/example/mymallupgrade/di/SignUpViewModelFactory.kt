package com.example.mymallupgrade.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mymallupgrade.domain.interactor.LoginWithEmailUseCase
import com.example.mymallupgrade.domain.interactor.SendEmailResetPasswordUseCase
import com.example.mymallupgrade.domain.interactor.SignUpWithEmailUseCase
import com.example.mymallupgrade.presentation.auth.AuthViewModel
import com.example.mymallupgrade.presentation.auth.auth.UserSignUpProcessorHolder
import com.example.mymallupgrade.presentation.auth.auth.UserSignUpViewModes

class SignUpViewModelFactory(
    private val userSignUpProcessorHolder: UserSignUpProcessorHolder
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(UserSignUpViewModes::class.java)) {
            return UserSignUpViewModes(
                userSignUpProcessorHolder
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}