package com.example.mymallupgrade.domain.repository.auth

import io.reactivex.Completable

interface AuthDataSource {
        fun login(email: String, password: String) : Completable

        fun register(email: String, password: String) : Completable

        fun sendEmailResetPassword(email: String) : Completable
}