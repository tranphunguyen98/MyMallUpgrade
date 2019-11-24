package com.example.mymallupgrade.domain.interactor

import com.example.mymallupgrade.domain.repository.AuthRepository

class LoginWithEmailUseCase constructor(private val authRepository: AuthRepository) {
    operator fun invoke(email: String, password: String) = authRepository.login(email, password)

}