package com.example.mymallupgrade.data.repositories

import com.example.mymallupgrade.data.FirebaseSource
import timber.log.Timber

class UserRepository(
    private val firebase: FirebaseSource
) {
    init {
        Timber.d("CCC UserRepository")
    }
    fun register(email: String, password: String) = firebase.register(email,password)
    fun login(email: String,password: String) = firebase.login(email,password)

}