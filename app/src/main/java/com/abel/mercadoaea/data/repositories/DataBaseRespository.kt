package com.abel.mercadoaea.data.repositories

import com.abel.mercadoaea.data.database.AppDatabase
import com.abel.mercadoaea.data.database.ItemEntity
import com.abel.mercadoaea.data.database.SuggestEntity
import com.abel.mercadoaea.data.model.item.ResponseItem
import com.abel.mercadoaea.util.toItemEntity

class DataBaseRespository(var database: AppDatabase) {

    //suggest
    suspend fun insertSuggest(suggestEntity: SuggestEntity) {
        database.suggestDao().insert(suggestEntity)
    }

    suspend fun getSuggest(query: String): List<SuggestEntity>? {
        return if (query.isNotEmpty()) {
            val queryLike = "%$query%"
            database.suggestDao().getByUserQuery(queryLike, 3)
        } else {
            database.suggestDao().getLastSuggest(3)
        }
    }

    //items
    suspend fun insertItem(response: ResponseItem) {
        database.itemDao().insert(response.toItemEntity())
    }

    suspend fun getItems(): List<ItemEntity>? {
        return database.itemDao().getLastItem(3)
    }

}