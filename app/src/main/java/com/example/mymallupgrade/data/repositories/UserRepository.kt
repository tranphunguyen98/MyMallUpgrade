package com.example.mymallupgrade.data.repositories

import com.example.mymallupgrade.data.FirebaseSource

class UserRepository(
    private val firebase: FirebaseSource
) {

    fun register(email: String, password: String) = firebase.register(email,password)
    fun login(email: String,password: String) = firebase.login(email,password)
    fun sendEmailResetPassword(email: String) = firebase.sendEmailResetPassword(email)

}