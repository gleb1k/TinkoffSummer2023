package com.main.tinkoffsummer2023.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.main.tinkoffsummer2023.data.local.dao.ProductDao
import com.main.tinkoffsummer2023.data.local.entity.ProductEntity

@Database(
    version = 1,
    entities = [ProductEntity::class]
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getProductDao(): ProductDao
}
