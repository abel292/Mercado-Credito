package com.abel.mercadoaea.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface ItemDao {
    @Query("SELECT * FROM ItemEntity LIMIT :limit")
    suspend fun getLastItem(limit: Int): List<ItemEntity>?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(vararg item: ItemEntity)
}