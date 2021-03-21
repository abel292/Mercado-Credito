package com.abel.mercadoaea.data.repositories

import com.abel.mercadoaea.data.database.AppDatabase
import com.abel.mercadoaea.data.database.SuggestEntity

class DataBaseRespository(var database: AppDatabase) {

    suspend fun insertSuggest(suggestEntity: SuggestEntity) {
        database.suggestDao().insert(suggestEntity)
    }

    suspend fun getSuggest(query: String): List<SuggestEntity>? {
        return if (query.isNotEmpty()) {
            val queryLike = "%$query%"
            database.suggestDao().getByUserQuery(queryLike,3)
        } else {
            database.suggestDao().getLastSuggest(3)
        }
    }
}