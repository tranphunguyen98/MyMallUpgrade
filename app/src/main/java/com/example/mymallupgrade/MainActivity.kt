package com.example.mymallupgrade

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mymallupgrade.domain.interactor.CreateUserUseCase
import com.example.mymallupgrade.domain.interactor.GetUserUseCase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var getUser: GetUserUseCase
    lateinit var createUser: CreateUserUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        InjectionUtil.inject(this)

        tv_name.text = getUser.execute().name

    }
}
