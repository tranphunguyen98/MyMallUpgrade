package com.example.mymallupgrade.domain.interactor

import com.example.mymallupgrade.domain.Result
import com.example.mymallupgrade.domain.UtilCheckValid
import com.example.mymallupgrade.domain.repository.AuthRepository
import io.reactivex.Completable

class SignUpWithEmailUseCase constructor(private val authRepository: AuthRepository) {
    operator fun invoke(email: String, password: String): Completable {
        when(val result = UtilCheckValid.checkEmail(email)) {
            is Result.Failure -> return Completable.error(IllegalArgumentException(result.message))
        }
        when(val result = UtilCheckValid.checkPassword(password)) {
            is Result.Failure -> return Completable.error(IllegalArgumentException(result.message))
        }
        return authRepository.register(email, password)
    }

}