package com.example.mymallupgrade.domain.repository

import io.reactivex.Completable

interface AuthRepository {
    fun register(email: String, password: String) : Completable
    fun login(email: String,password: String) : Completable
    fun sendEmailResetPassword(email: String) : Completable
}