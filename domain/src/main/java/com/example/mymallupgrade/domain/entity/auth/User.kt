package com.example.mymallupgrade.domain.entity.auth

data class User(
    val id : String,
    val name: String,
    val email: String,
    val gender: Gender,
    val age: Int
)