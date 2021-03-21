package com.abel.mercadoaea.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SuggestDao {

    @Query("SELECT * FROM SuggestEntity")
    fun allLive(): LiveData<List<SuggestEntity>>

    @get:Query("SELECT count(*) FROM SuggestEntity")
    val count: Int

    @Query("SELECT * FROM SuggestEntity WHERE suggest LIKE :q LIMIT :limit")
    suspend fun getByUserQuery(q: String, limit: Int): List<SuggestEntity>?

    @Query("SELECT * FROM SuggestEntity LIMIT :limit")
    suspend fun getLastSuggest(limit: Int): List<SuggestEntity>?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(vararg suggest: SuggestEntity)
}