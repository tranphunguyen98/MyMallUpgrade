package com.example.mymallupgrade.presentation.auth.auth

import com.example.mymallupgrade.presentation.auth.MVIBase.MviIntent

sealed class UserSignUpIntent : MviIntent {
    data class EmailIntent(val email: String) : UserSignUpIntent()
    data class PasswordIntent(val password: String) : UserSignUpIntent()
    data class SignUpIntent(
        val email: String,
        val password: String
    ) : UserSignUpIntent()
}