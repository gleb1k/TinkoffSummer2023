package com.main.tinkoffsummer2023.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.main.tinkoffsummer2023.data.local.entity.CartProductEntity

@Dao
interface CartProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: CartProductEntity)

    @Query("SELECT * FROM cart_products")
    suspend fun getAll(): List<CartProductEntity>

    @Query("DELETE FROM cart_products")
    suspend fun deleteALL()

    @Query("UPDATE cart_products SET count=count+1 WHERE id=:id")
    suspend fun plusCartProductById(
        id : Int,
    )

    @Query("UPDATE cart_products SET count=count-1 WHERE id=:id")
    suspend fun minusCartProductById(
        id : Int,
    )

    @Delete
    suspend fun delete(cartProduct: CartProductEntity)

    @Query("DELETE FROM cart_products WHERE id=:id")
    suspend fun deleteById(id: Int)

    @Query("SELECT * FROM cart_products WHERE id=:id")
    suspend fun get(id: Int): CartProductEntity?

}
