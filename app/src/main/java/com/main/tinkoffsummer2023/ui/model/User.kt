package com.main.tinkoffsummer2023.ui.model

data class User(
    val login: String,
    val password : String,
    val score: Int = 1000,
    val isAdmin : Boolean = false,
    val name: String = "Иван Иванов",

    val token : String,
)
