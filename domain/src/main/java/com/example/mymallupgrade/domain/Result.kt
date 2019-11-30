package com.example.mymallupgrade.domain

sealed class Result<T>{
    data class Success<T>(val value: T) : Result<T>()
    data class Failure<T>(val throwable: Throwable) : Result<T>()
}