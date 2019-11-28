package com.example.mymallupgrade.domain

object UtilCheckValid {
    private val REGEX_EMAIL =
        "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$".toRegex()
    private val REGEX_PASSWORD =
        ".*?(?:[a-z].*?[0-9]|[0-9].*?[a-z]).*?".toRegex()

    fun checkEmail(email: String): Result{
        if (REGEX_EMAIL.matches(email)) {
            return Result.Success
        }
        return Result.Failure("Email is not valid.")
    }

    fun checkPassword(password: String): Result {
        if (password.length < 6) {
            return Result.Failure("Length password must be greater than 6.")
        }
        if (REGEX_PASSWORD.matches(password)) {
            return Result.Success
        }
        return Result.Failure("Password must contain both number and letter.")
    }

    fun checkConfirmPassword(password: String, confirmPassword: String): Result {
        if (password == confirmPassword) {
            return Result.Success
        }
        return Result.Failure("Password and confirmPassword not equal.")
    }
}
