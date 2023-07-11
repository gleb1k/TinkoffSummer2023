package com.main.tinkoffsummer2023.domain

import com.main.tinkoffsummer2023.ui.model.CartProduct
import com.main.tinkoffsummer2023.ui.model.Category
import com.main.tinkoffsummer2023.ui.model.Order
import com.main.tinkoffsummer2023.ui.model.Product
import com.main.tinkoffsummer2023.ui.model.User

interface MainRepository {

    suspend fun getProducts(): List<Product>

    suspend fun getProductById(
        productId: Int,
    ): Product

    suspend fun searchProducts(
        q: String,
    ): List<Product>?

    suspend fun getCategories() : List<Category>

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

    // region order
    suspend fun saveOrder(
        order: Order
    )

    suspend fun getOrders() : List<Order>

    // endregion

    // region user
    suspend fun registerUser(
        login : String,
        password : String,
        isAdmin : Boolean,
    )

    suspend fun loginUser(
        login : String,
        password : String,
    ) : Boolean

    suspend fun getUserInfo(
        token : String?
    ) : User?

    // endregion
}
