package com.example.mymallupgrade.presentation.auth.auth

import com.example.mymallupgrade.presentation.auth.MVIBase.MviViewState

data class UserSignUpViewState(
    val isLoading: Boolean,
    val isSuccess: Boolean,
    val isError : Throwable?
) : MviViewState {
    companion object {
        fun default() : UserSignUpViewState = UserSignUpViewState(
            isLoading = false,
            isSuccess = false,
            isError = null
        )
    }
}