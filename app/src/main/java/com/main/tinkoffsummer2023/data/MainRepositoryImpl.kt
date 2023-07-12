package com.main.tinkoffsummer2023.data

import com.main.tinkoffsummer2023.core.PreferenceManager
import com.main.tinkoffsummer2023.data.local.AppDatabase
import com.main.tinkoffsummer2023.data.remote.MainApi
import com.main.tinkoffsummer2023.domain.MainRepository
import com.main.tinkoffsummer2023.ui.model.CartProduct
import com.main.tinkoffsummer2023.ui.model.Category
import com.main.tinkoffsummer2023.ui.model.MockBackend
import com.main.tinkoffsummer2023.ui.model.Order
import com.main.tinkoffsummer2023.ui.model.Product
import com.main.tinkoffsummer2023.ui.model.User
import java.util.Locale
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val api: MainApi, //TODO БЭКЕНДА НЕТ, НО ВЫ ДЕРЖИТЕСЬ . F
    db: AppDatabase,
    private val preferenceManager: PreferenceManager,
) : MainRepository {

    private val cartProductDao = db.getCartProductDao()

    override suspend fun getProducts(): List<Product> {
        return MockBackend.products
    }

    override suspend fun getProductById(productId: Int): Product {
//        return api.getProductById(productId)

        return MockBackend.products.first { it.id == productId }
    }

    override suspend fun searchProducts(q: String): List<Product>? {
//        return api.searchProducts(q)

        // бекенда нет, печаль
        return MockBackend.products.filter {
            it.name.lowercase(Locale.ROOT).contains(q.lowercase(Locale.ROOT))
        }
    }

    override suspend fun getCategories(): List<Category> {
        //        return api.getCategories()

        return MockBackend.categories
    }

    override suspend fun addProductToCart(productId: Int) {
        val product = getProductById(productId)

//        cartProductDao.insert(
//            CartProductEntity(
//                product.id,
//                product.name,
//                product.price,
//                product.rating,
//                product.description,
//                product.images,
//                1
//            )
//        )
        if (MockBackend.usersDataBase.isNotEmpty()) {
            MockBackend.cartProductDataBase.add(
                CartProduct(
                    product,
                    1
                )
            )
        }
    }

    override suspend fun getCartProducts(): List<CartProduct> {
//        return cartProductDao.getAll().map { it.toCartProduct() }
        return MockBackend.cartProductDataBase
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
//        cartProductDao.deleteALL()
//        return cartProductDao.getAll().map { it.toCartProduct() }
        MockBackend.cartProductDataBase.clear()
        return getCartProducts()
    }

    override suspend fun saveOrder(order: Order) {
        //api.saveOrder(order)

        MockBackend.ordersDataBase.add(order)
    }

    override suspend fun getOrders(): List<Order> {
        // return api.getOrders(token : String) ?

        return MockBackend.ordersDataBase
    }

    override suspend fun registerUser(login: String, password: String, isAdmin: Boolean) {
//        // Create JSON using JSONObject
//        val jsonObject = JSONObject()
//        jsonObject.put("login", login)
//        jsonObject.put("password", password)
//        if (isAdmin)
//            jsonObject.put("role", "admin")
//        else
//            jsonObject.put("role", "customer")
//
//        // Convert JSONObject to String
//        val jsonObjectString = jsonObject.toString()
//
//        // Create RequestBody ( We're not using any converter, like GsonConverter, MoshiConverter e.t.c, that's why we use RequestBody )
//        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())
//
//        api.singUpUser(requestBody)

        if (isAdmin)
            MockBackend.usersDataBase.add(
                User(
                    login,
                    password,
                    token = login + password,
                    isAdmin = isAdmin,
                    score = 1000,
                )
            )
        else
            MockBackend.usersDataBase.add(
                User(
                    login,
                    password,
                    token = login + password,
                    isAdmin = isAdmin,
                    score = 5000,
                )
            )
    }

    override suspend fun loginUser(login: String, password: String): Boolean {
        // val token = api.signInUser(login, password)
//
//        if (token!= null)
//        {
//            preferenceManager.saveUserToken(token)
//            return true
//        }
//        else
//            return false
//
        // проверка на совпадение юзеров в базе ... тяжело
        return try {
            val userInBase: User =
                MockBackend.usersDataBase.first { it.login == login && it.password == password }
            preferenceManager.saveUserToken(userInBase.token)
            true
        } catch (ex: NoSuchElementException) {
            false
        }
    }

    override suspend fun getUserInfo(token: String?): User? {
//        return api.getUserInfo(token)
        return try {
            MockBackend.usersDataBase.first { it.token == token }
        } catch (ex: NoSuchElementException) {
            null
        }
    }
}
