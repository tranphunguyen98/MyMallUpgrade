package com.example.mymallupgrade

import com.example.mymallupgrade.data.UserRepositoryImp
import com.example.mymallupgrade.domain.interactor.CreateUserUseCase
import com.example.mymallupgrade.domain.interactor.GetUserUseCase
import com.example.mymallupgrade.domain.repository.UserRepository

object InjectionUtil {
    private val repositoryImp : UserRepository by lazy {
        UserRepositoryImp()
    }

    private val getUser : GetUserUseCase by lazy {
        GetUserUseCase(repositoryImp)
    }

    private val createUser : CreateUserUseCase by lazy {
        CreateUserUseCase(repositoryImp)
    }

    fun inject(mainActivity: MainActivity) {
        mainActivity.getUser = getUser
        mainActivity.createUser = createUser
    }
}