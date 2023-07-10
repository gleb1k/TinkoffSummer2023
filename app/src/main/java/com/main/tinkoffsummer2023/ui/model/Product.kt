package com.main.tinkoffsummer2023.ui.model

data class Product(
    val id: Int,
    val name: String,
    val price: Int,
    val rating: Double,
    val description: String,

    val images: List<String>,
)
