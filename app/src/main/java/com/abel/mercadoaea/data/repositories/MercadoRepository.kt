package com.abel.mercadoaea.data.repositories

import com.abel.mercadoaea.data.api.ContsApi.Companion.LIMIT_SEARCH
import com.abel.mercadoaea.data.api.ContsApi.Companion.V_API
import com.abel.mercadoaea.data.api.ContsApi.Companion.LIMIT_SUGGEST
import com.abel.mercadoaea.data.api.MercadoApi
import com.abel.mercadoaea.data.database.ItemEntity
import com.abel.mercadoaea.data.database.SuggestEntity
import com.abel.mercadoaea.data.model.item.ResponseItem
import com.abel.mercadoaea.util.ResultResource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class MercadoRepository(
    private val mercadoApi: MercadoApi,
    private var dataBaseRespository: DataBaseRespository
) {

    suspend fun getSuggestApi(q: String) = flow {
        val result = mercadoApi.getSuggest(false, LIMIT_SUGGEST, V_API, q)
        when (result.code()) {
            200 -> emit(ResultResource.Success(result.body()!!))
            else -> emit(ResultResource.Failure())
        }
    }.catch {
        emit(ResultResource.Failure())
    }

    suspend fun searchedItem(q: String, offset: Int) = flow {
        dataBaseRespository.insertSuggest(SuggestEntity(q))
        val result = mercadoApi.getListSearchedItems(LIMIT_SEARCH, offset, q, "results")
        kotlinx.coroutines.delay(2000)
        when (result.code()) {
            200 -> emit(ResultResource.Success(result.body()!!))
            else -> emit(ResultResource.Failure())
        }
    }.catch {
        emit(ResultResource.Failure())
    }

    suspend fun searchCategory(categoryId: String, offset: Int) = flow {
        val result = mercadoApi.getLisSearchCategory(LIMIT_SEARCH, offset, categoryId, "results")
        kotlinx.coroutines.delay(2000)
        when (result.code()) {
            200 -> emit(ResultResource.Success(result.body()!!))
            else -> emit(ResultResource.Failure())
        }
    }.catch {
        emit(ResultResource.Failure())
    }

    suspend fun getDescriptionApi(idItem: String) = flow {
        val result = mercadoApi.getDescription(idItem)
        when (result.code()) {
            200 -> emit(ResultResource.Success(result.body()!!))
            else -> emit(ResultResource.Failure())
        }
    }.catch {
        emit(ResultResource.Failure())
    }

    suspend fun getCategoryApi() = flow {
        val result = mercadoApi.getCategory()
        when (result.code()) {
            200 -> emit(ResultResource.Success(result.body()!!))
            else -> emit(ResultResource.Failure())
        }
    }.catch {
        emit(ResultResource.Failure())
    }

    suspend fun getReviews(idItem: String) = flow {
        val result = mercadoApi.getReviews(idItem)
        when (result.code()) {
            200 -> emit(ResultResource.Success(result.body()!!))
            else -> emit(ResultResource.Failure())
        }
    }.catch {
        emit(ResultResource.Failure())
    }

    suspend fun getItemComplete(idItem: String) = flow {
        val result = mercadoApi.getItemComplete(idItem)
        when (result.code()) {
            200 -> {
                saveItemSeen(result.body()!!)
                emit(ResultResource.Success(result.body()!!))

            }
            else -> emit(ResultResource.Failure())
        }
    }.catch {
        emit(ResultResource.Failure())
    }

    //room
    suspend fun getHistoryViewed(): List<ItemEntity>? {
        return dataBaseRespository.getItems()
    }

    suspend fun getHistorySearched(q: String): List<SuggestEntity>? {
        return dataBaseRespository.getSuggest(q)
    }

    private suspend fun saveItemSeen(responseItem: ResponseItem) {
        return dataBaseRespository.insertItem(responseItem)
    }
}