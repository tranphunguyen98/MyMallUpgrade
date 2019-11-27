package com.example.mymallupgrade.domain.interactor

import com.example.mymallupgrade.domain.UtilCheckValid
import com.example.mymallupgrade.domain.repository.AuthRepository
import io.reactivex.Completable
import java.lang.Exception

class SignInWithEmailUseCase constructor(private val authRepository: AuthRepository) {

    operator fun invoke(email: String, password: String): Completable {
        try {
            UtilCheckValid.checkPassword(password)
            UtilCheckValid.checkEmail(email)
        } catch (exception: Exception) {
            throw exception
        }

        return authRepository.login(email, password)
    }

}