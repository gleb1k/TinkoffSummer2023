package com.main.tinkoffsummer2023.domain

import com.main.tinkoffsummer2023.ui.model.CartProduct
import com.main.tinkoffsummer2023.ui.model.Product

interface MainRepository {

    suspend fun getProducts(): List<Product>

    suspend fun getProductById(
        productId: Int,
    ): Product

    suspend fun searchProductS(
        q: String,
    ): List<Product>?

    // region Cart

    suspend fun addProductToCart(productId: Int)

    suspend fun getCartProducts(): List<CartProduct>

    suspend fun plusCartProductById(
        productId: Int
    ): CartProduct

    suspend fun minusCartProductById(
        productId: Int
    ): CartProduct?


    suspend fun clearCart(): List<CartProduct>

    // endregion
}
