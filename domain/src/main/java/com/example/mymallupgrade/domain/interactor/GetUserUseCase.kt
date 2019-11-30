package com.example.mymallupgrade.domain.interactor

import com.example.mymallupgrade.domain.entity.auth.User
import com.example.mymallupgrade.domain.repository.auth.UserRepository

class GetUserUseCase constructor(private val repository: UserRepository) {
    fun execute() : User {
        return repository.getUser()
    }
}