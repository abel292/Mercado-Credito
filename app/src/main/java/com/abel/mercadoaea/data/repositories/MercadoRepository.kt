package com.abel.mercadoaea.data.repositories

import android.util.Log
import com.abel.mercadoaea.data.api.ContsApi.Companion.LIMIT_SEARCH
import com.abel.mercadoaea.data.api.ContsApi.Companion.V_API
import com.abel.mercadoaea.data.api.ContsApi.Companion.LIMIT_SUGGEST
import com.abel.mercadoaea.data.api.MercadoApi
import com.abel.mercadoaea.data.model.description.ResponseDescription
import com.abel.mercadoaea.data.model.item.ResponseItem
import com.abel.mercadoaea.data.model.review.ResponseReview
import com.abel.mercadoaea.data.model.search.ResponseSearch
import com.abel.mercadoaea.util.Data
import com.abel.mercadoaea.util.ResultResource
import com.abel.mercadoaea.util.Status
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class MercadoRepository(private val mercadoApi: MercadoApi) {

    suspend fun getSuggestApi(q: String) = flow {
        val result = mercadoApi.getSuggest(false, LIMIT_SUGGEST, V_API, q)
        when (result.code()) {
            200 -> emit(ResultResource.Success(result.body()!!))
            else -> emit(ResultResource.Failure())
        }
    }.catch {
        emit(ResultResource.Failure())
    }

    suspend fun getListSearchedItems(q: String, offset: Int) = flow {
        val result = mercadoApi.getListSearchedItems(LIMIT_SEARCH, offset, q, "results")
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
            200 -> emit(ResultResource.Success(result.body()!!))
            else -> emit(ResultResource.Failure())
        }
    }.catch {
        emit(ResultResource.Failure())
    }
}