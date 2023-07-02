package com.main.tinkoffsummer2023.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val cost: Int,
    val img: String,
    val description: String,
)
