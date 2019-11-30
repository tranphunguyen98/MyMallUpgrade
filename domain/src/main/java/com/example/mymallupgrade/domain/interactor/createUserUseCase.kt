package com.example.mymallupgrade.domain.interactor

import com.example.mymallupgrade.domain.entity.auth.Gender
import com.example.mymallupgrade.domain.entity.auth.User
import com.example.mymallupgrade.domain.repository.auth.UserRepository

class CreateUserUseCase constructor(private val repository: UserRepository) {
    /**
     * Khi tạo mới user mặc định gender = MALE
     * Nội dung của user không được rỗng
     */
    fun excecute(name : String) {
        if(name.isNullOrEmpty()) {
            throw IllegalArgumentException("name user must not be none")
        } else {

            val user = User(
                id = "",
                name = "",
                age = 0,
                email = "",
                gender = Gender.MALE
            )

            repository.createUser(user)
        }
    }
}