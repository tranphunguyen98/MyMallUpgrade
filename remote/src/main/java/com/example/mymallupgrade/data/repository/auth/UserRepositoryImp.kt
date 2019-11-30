package com.example.mymallupgrade.data.repository.auth

import com.example.mymallupgrade.domain.entity.auth.Gender
import com.example.mymallupgrade.domain.entity.auth.User
import com.example.mymallupgrade.domain.repository.auth.UserRepository

class UserRepositoryImp : UserRepository {

    override fun getUser(): User {
        return User(
            "123",
            "Tran Phu Nguyen",
            "ntranphu111@gmail.com",
            Gender.MALE,
            21
        )
    }

    override fun createUser(user: User): String {
        val id = "111"
        return id
    }

}