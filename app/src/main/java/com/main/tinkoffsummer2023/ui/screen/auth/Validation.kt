package com.main.tinkoffsummer2023.ui.screen.auth

import android.util.Patterns

class Validation {

    private fun isEmailValid(email: String): Boolean {
        val pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }

    private fun isPasswordValid(password: String): Boolean {
        // Минимальная длина пароля
        val minLength = 8
        return password.length >= minLength
    }

    private fun doPasswordsMatch(password: String, confirmPassword: String): Boolean {
        return password == confirmPassword
    }

    fun validateRegistrationFields(
        email: String,
        password: String,
        confirmPassword: String
    ): Boolean {
        // Валидация email
        if (!isEmailValid(email)) {
            return false
        }

        // Валидация пароля
        if (!isPasswordValid(password)) {
            return false
        }

        // Проверка совпадения пароля и подтверждения пароля
        if (!doPasswordsMatch(password, confirmPassword)) {
            return false
        }

        // Все поля прошли валидацию
        return true
    }
}
