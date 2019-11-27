package com.example.mymallupgrade.domain

object UtilCheckValid {
    private val REGEX_EMAIL =
        "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$".toRegex()
    private val REGEX_PASSWORD =
        "/.*?(?:[a-z].*?[0-9]|[0-9].*?[a-z]).*?/".toRegex()

    fun checkEmail(email: String): Boolean {
        if (REGEX_EMAIL.matches(email)) {
            return true
        }
        throw IllegalArgumentException("Email is not valid.")
    }

    fun checkPassword(password: String): Boolean {
        if (password.length < 6) {
            throw IllegalArgumentException("Length password must be greater than 6.")
        }
        if (REGEX_PASSWORD.matches(password)) {
            return true
        }
        throw IllegalArgumentException("Password must contain both number and letter.")
    }

    fun checkConfirmPassword(password: String, confirmPassword: String): Boolean {
        if (password == confirmPassword) {
            return true
        }
        throw IllegalArgumentException("Password and confirmPassword not equal.")
    }
}
