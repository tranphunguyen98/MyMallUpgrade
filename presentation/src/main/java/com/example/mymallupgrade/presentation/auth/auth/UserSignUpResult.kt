package com.example.mymallupgrade.presentation.auth.auth

import com.example.mymallupgrade.presentation.auth.MVIBase.MviResult

sealed class UserSignUpResult : MviResult {

    sealed class EmailResult : UserSignUpResult() {
         object Loading : EmailResult()
        data class Success(val email : String) : EmailResult()
        data class Failure(val error : Throwable): EmailResult()
    }
    sealed class PasswordResult : UserSignUpResult() {
         object Loading : PasswordResult()
        data class Success(val password : String) : PasswordResult()
        data class Failure(val error : Throwable): PasswordResult()
    }
    sealed class SignUpResult : UserSignUpResult() {
         object Loading : SignUpResult()
        object Success : SignUpResult()
        data class Failure(val error : Throwable): SignUpResult()
    }
}