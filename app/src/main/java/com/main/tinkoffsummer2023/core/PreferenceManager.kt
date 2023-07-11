package com.main.tinkoffsummer2023.core

import android.content.Context
import android.content.SharedPreferences


class PreferenceManager(context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        "UserPreferences",
        Context.MODE_PRIVATE
    )

    fun saveUserToken(token: String) {
        sharedPreferences.edit()
            .putString(KEY_USER_TOKEN, token)
            .apply()
    }

    fun getUserToken(): String? {
        return sharedPreferences.getString(KEY_USER_TOKEN, null)
    }

    fun saveIsAdmin(isAdmin: Boolean) {
        sharedPreferences.edit()
            .putBoolean(KEY_IS_ADMIN, isAdmin)
            .apply()
    }

    fun getIsAdmin(): Boolean {
        return sharedPreferences.getBoolean(KEY_IS_ADMIN, false)
    }

    companion object {
        private const val KEY_USER_TOKEN = "user_token"
        private const val KEY_IS_ADMIN = "is_admin"
    }
}
