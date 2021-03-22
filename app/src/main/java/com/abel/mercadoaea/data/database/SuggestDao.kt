package com.abel.mercadoaea.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SuggestDao {

    @Query("SELECT * FROM _SUGGEST_ENTITY")
    fun allLive(): LiveData<List<SuggestEntity>>

    @get:Query("SELECT count(*) FROM _SUGGEST_ENTITY")
    val count: Int

    @Query("SELECT * FROM _SUGGEST_ENTITY WHERE suggest LIKE :q LIMIT :limit")
    suspend fun getByUserQuery(q: String, limit: Int): List<SuggestEntity>?

    @Query("SELECT * FROM _SUGGEST_ENTITY ORDER BY ID DESC LIMIT :limit")
    suspend fun getLastSuggest(limit: Int): List<SuggestEntity>?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(vararg suggest: SuggestEntity)
}