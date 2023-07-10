package com.main.tinkoffsummer2023.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.main.tinkoffsummer2023.data.local.dao.CartProductDao
import com.main.tinkoffsummer2023.data.local.entity.CartProductEntity

@Database(
    version = 1,
    entities = [CartProductEntity::class]
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getCartProductDao(): CartProductDao
}
