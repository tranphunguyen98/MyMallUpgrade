package com.example.mymallupgrade.domain.repository

import io.reactivex.Completable
import io.reactivex.Observable

interface AuthRepository {
    fun register(email: String, password: String) : Observable<Boolean>
    fun login(email: String,password: String) : Completable
    fun sendEmailResetPassword(email: String) : Completable
}