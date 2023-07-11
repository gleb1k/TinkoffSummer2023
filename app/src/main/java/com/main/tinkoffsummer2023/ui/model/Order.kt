package com.main.tinkoffsummer2023.ui.model

import androidx.compose.ui.graphics.Color

data class Order(
    val id : Int = -1,
    val orderTime : String = "",
    val finishTime : String = "",
    val address: String = "",

    val status : String = "",
    val products: List<CartProduct> = listOf(),
)

//
//data class OrderStatus(
//    val name : String,
//    val color: Color
//)
