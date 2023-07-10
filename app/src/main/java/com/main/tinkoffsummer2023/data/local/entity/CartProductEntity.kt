package com.main.tinkoffsummer2023.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.main.tinkoffsummer2023.ui.model.CartProduct
import com.main.tinkoffsummer2023.ui.model.Product

@Entity(tableName = "cart_products")
data class CartProductEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val price: Int,
    val rating: Double,
    val description: String,
    //todo converter
    val images: List<String>,

    val count: Int,
) {
    fun toCartProduct(): CartProduct =
        CartProduct(
            Product(id, name, price, rating, description, images),
            count
        )
}
