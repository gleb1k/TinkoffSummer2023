package com.main.tinkoffsummer2023.data.remote

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.main.tinkoffsummer2023.data.local.entity.CartProductEntity
import com.main.tinkoffsummer2023.ui.model.Product
import com.main.tinkoffsummer2023.ui.model.User
import retrofit2.http.GET
import retrofit2.http.POST

interface MainApi {

    @GET("")
    suspend fun searchProducts(
        q: String
    ) : List<Product>

    @GET("")
    suspend fun getProductById(
        id : Int
    ) : Product

    @GET("")
    suspend fun signInUser(
        login : String,
        password: String,
    ) : User

    @POST("")
    suspend fun singUpUser(
        login : String,
        password: String,
    )
}
