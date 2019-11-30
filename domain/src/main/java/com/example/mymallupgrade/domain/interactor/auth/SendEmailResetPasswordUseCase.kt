package com.example.mymallupgrade.domain.interactor.auth

import com.example.mymallupgrade.domain.common.Result
import com.example.mymallupgrade.domain.common.UtilCheckValid
import com.example.mymallupgrade.domain.repository.auth.AuthRepository
import io.reactivex.Completable

class SendEmailResetPasswordUseCase constructor(private val authRepository: AuthRepository) {
    operator fun invoke(email: String): Completable {
        when(val result = UtilCheckValid.checkEmail(email)) {
            is Result.Failure -> return Completable.error(IllegalArgumentException(result.throwable.message))
        }
        return authRepository.sendEmailResetPassword(email)
    }
}