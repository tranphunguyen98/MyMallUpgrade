package com.example.mymallupgrade.domain.interactor

import com.example.mymallupgrade.domain.repository.AuthRepository

class SendEmailResetPasswordUseCase constructor(private val authRepository: AuthRepository) {
    operator fun invoke(email: String) = authRepository.sendEmailResetPassword(email)
}