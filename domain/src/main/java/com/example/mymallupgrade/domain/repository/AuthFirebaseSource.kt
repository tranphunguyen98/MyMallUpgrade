package com.example.mymallupgrade.domain.repository

import io.reactivex.Completable
import io.reactivex.Observable

interface AuthFirebaseSource {
        fun login(email: String, password: String) : Completable

        fun register(email: String, password: String) : Observable<Boolean>

        fun sendEmailResetPassword(email: String) : Completable
}