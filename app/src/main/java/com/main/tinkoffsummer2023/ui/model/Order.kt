package com.main.tinkoffsummer2023.ui.model

import androidx.compose.ui.graphics.Color

data class Order(
    val id : Int,
    val orderTime : String,
    val finishTime : String,
    val status : String,
    val product: Product,
)

//
//data class OrderStatus(
//    val name : String,
//    val color: Color
//)
