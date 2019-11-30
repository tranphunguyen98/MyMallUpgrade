package com.example.mymallupgrade.domain.interactor.auth

import com.example.mymallupgrade.domain.Result
import com.example.mymallupgrade.domain.UtilCheckValid
import com.example.mymallupgrade.domain.repository.auth.AuthRepository
import io.reactivex.Completable

class LoginWithEmailUseCase constructor(private val authRepository: AuthRepository) {
    operator fun invoke(email: String, password: String): Completable {
        when(val result = UtilCheckValid.checkEmail(email)) {
            is Result.Failure -> return Completable.error(IllegalArgumentException(result.throwable.message))
        }
        when(val result = UtilCheckValid.checkPassword(password)) {
            is Result.Failure -> return Completable.error(IllegalArgumentException(result.throwable.message))
        }
        return authRepository.login(email, password)
    }
}