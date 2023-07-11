package com.main.tinkoffsummer2023.data.remote

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.main.tinkoffsummer2023.data.local.entity.CartProductEntity
import com.main.tinkoffsummer2023.ui.model.Product
import com.main.tinkoffsummer2023.ui.model.User
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface MainApi {

//    @GET("")
//    suspend fun searchProducts(
//        q: String
//    ) : List<Product>
//
//    @GET("")
//    suspend fun getProductById(
//        id : Int
//    ) : Product
//
//    @GET("")
//    suspend fun signInUser(
//        login : String,
//        password: String,
//    ) : User

//    @POST("/users")
//    suspend fun singUpUser(
//        @Body login : String,
//        password: String,
//        is_admin : Boolean
//    )

    @POST("/users")
    suspend fun singUpUser(
        @Body requestBody: RequestBody
    )
}
