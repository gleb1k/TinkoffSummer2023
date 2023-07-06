package com.main.tinkoffsummer2023.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.main.tinkoffsummer2023.data.local.entity.ProductEntity

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: ProductEntity)

    @Query("DELETE FROM products WHERE id=:id")
    suspend fun deleteById(id: String)

    @Query("SELECT * FROM products WHERE id=:id")
    suspend fun get(id: Int): ProductEntity?

    @Query("SELECT * FROM products")
    suspend fun getAll(): List<ProductEntity>
}
