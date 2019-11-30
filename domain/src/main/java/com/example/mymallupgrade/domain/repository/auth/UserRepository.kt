package com.example.mymallupgrade.domain.repository.auth

import com.example.mymallupgrade.domain.entity.auth.User

interface UserRepository {
    fun getUser() : User

    /**
     * create new user, return id
     */
    fun createUser(user: User) : String
}