package com.main.tinkoffsummer2023.data

import com.main.tinkoffsummer2023.data.local.AppDatabase
import com.main.tinkoffsummer2023.data.local.entity.CartProductEntity
import com.main.tinkoffsummer2023.data.remote.MainApi
import com.main.tinkoffsummer2023.domain.MainRepository
import com.main.tinkoffsummer2023.ui.model.CartProduct
import com.main.tinkoffsummer2023.ui.model.MockTempConstants
import com.main.tinkoffsummer2023.ui.model.Product
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
//    private val api: MainApi,
    db: AppDatabase,
) : MainRepository {

    private val cartProductDao = db.getCartProductDao()

    override suspend fun getProducts(): List<Product> {
        return MockTempConstants.products
    }

    override suspend fun getProductById(productId: Int): Product {
//        return api.getProductById(productId)

        return MockTempConstants.products.first { it.id == productId }
    }

    override suspend fun searchProductS(q: String): List<Product>? {
//        return api.searchProducts(q)

        return MockTempConstants.products.filter { it.name.contains(q) }
    }

    override suspend fun addProductToCart(productId: Int) {
        val product = getProductById(productId)

        cartProductDao.insert(
            CartProductEntity(
                product.id,
                product.name,
                product.price,
                product.rating,
                product.description,
                product.images,
                1
            )
        )
    }

    override suspend fun getCartProducts(): List<CartProduct> {
        return cartProductDao.getAll().map { it.toCartProduct() }
    }

    override suspend fun plusCartProductById(productId: Int): CartProduct {
        cartProductDao.plusCartProductById(productId)

        return cartProductDao.get(productId)!!.toCartProduct()
    }

    override suspend fun minusCartProductById(productId: Int): CartProduct {
        cartProductDao.minusCartProductById(productId)

        return cartProductDao.get(productId)!!.toCartProduct()
    }

    override suspend fun clearCart(): List<CartProduct> {
        cartProductDao.deleteALL()
        return cartProductDao.getAll().map { it.toCartProduct() }
    }
}
