package com.example.mymallupgrade.domain.repository

import io.reactivex.Completable

interface AuthFirebaseSource {
        fun login(email: String, password: String) : Completable

        fun register(email: String, password: String) : Completable

        fun sendEmailResetPassword(email: String) : Completable
}