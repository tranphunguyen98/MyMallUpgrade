package com.example.mymallupgrade.domain

sealed class Result{
    object Success : Result()
    data class Failure(val message: String) : Result()
}