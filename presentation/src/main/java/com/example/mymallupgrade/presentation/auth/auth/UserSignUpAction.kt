package com.example.mymallupgrade.presentation.auth.auth

import com.example.mymallupgrade.presentation.auth.MVIBase.MviAction

sealed class UserSignUpAction : MviAction {
    data class EmailAction(val email: String) : UserSignUpAction()
    data class PasswordAction(val password: String) : UserSignUpAction()
    data class SignUpAction(
        val email: String,
        val password: String
    ) : UserSignUpAction()
}