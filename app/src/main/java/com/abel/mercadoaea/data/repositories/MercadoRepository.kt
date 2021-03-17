package com.abel.mercadoaea.data.repositories

import com.abel.mercadoaea.data.api.ContsApi.Companion.LIMIT_SEARCH
import com.abel.mercadoaea.data.api.ContsApi.Companion.V_API
import com.abel.mercadoaea.data.api.ContsApi.Companion.LIMIT_SUGGEST
import com.abel.mercadoaea.data.api.MercadoApi
import com.abel.mercadoaea.data.model.description.ResponseDescription
import com.abel.mercadoaea.data.model.item.ResponseItem
import com.abel.mercadoaea.data.model.review.ResponseReview
import com.abel.mercadoaea.data.model.search.ResponseSearch
import com.abel.mercadoaea.data.model.suggest.ResponseSuggest
import com.abel.mercadoaea.viewmodel.ResourceResponse
import com.abel.mercadoaea.viewmodel.ResourceResponse.Companion.ERROR
import com.abel.mercadoaea.viewmodel.ResourceResponse.Companion.SUCCESS
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class MercadoRepository(private val mercadoApi: MercadoApi) {
    private val resourceSuggest = ResourceResponse<ResponseSuggest>(null, null)
    private val resourceSearch = ResourceResponse<ResponseSearch>(null, null)
    private val resourceDescription = ResourceResponse<ResponseDescription>(null, null)
    private val resourceReview = ResourceResponse<ResponseReview>(null, null)
    private val resourceItem = ResourceResponse<ResponseItem>(null, null)

    suspend fun getSuggestApi(q: String) = flow {
        val result = mercadoApi.getSuggest(false, LIMIT_SUGGEST, V_API, q)
        when (result.code()) {
            200 -> {
                resourceSuggest.resourceObject = result.body()
                resourceSuggest.responseAction = SUCCESS
            }
            else -> {
                resourceSuggest.responseAction = ERROR
            }
        }
        resourceSuggest.loading = false
        emit(resourceSuggest)

    }.catch {
        resourceSuggest.responseAction = ERROR
        emit(resourceSuggest)
    }

    suspend fun getListSearchedItems(q: String, offset: Int) = flow {
        val result = mercadoApi.getListSearchedItems(LIMIT_SEARCH, offset, q, "results")
        when (result.code()) {
            200 -> {
                resourceSearch.resourceObject = result.body()
                resourceSearch.responseAction = SUCCESS
            }
            else -> {
                resourceSearch.responseAction = ERROR
            }
        }
        resourceSearch.loading = false
        emit(resourceSearch)

    }

    suspend fun getDescriptionApi(idItem: String) = flow {
        val result = mercadoApi.getDescription(idItem)
        when (result.code()) {
            200 -> {
                resourceDescription.resourceObject = result.body()
                resourceDescription.responseAction = SUCCESS
            }
            else -> {
                resourceDescription.responseAction = ERROR
            }
        }
        resourceDescription.loading = false
        emit(resourceDescription)

    }.catch {
        resourceDescription.responseAction = ERROR
        emit(resourceDescription)
    }

    suspend fun getReviews(idItem: String) = flow {
        val result = mercadoApi.getReviews(idItem)
        when (result.code()) {
            200 -> {
                resourceReview.resourceObject = result.body()
                resourceReview.responseAction = SUCCESS
            }
            else -> {
                resourceReview.responseAction = ERROR
            }
        }
        resourceReview.loading = false
        emit(resourceReview)

    }.catch {
        resourceReview.responseAction = ERROR
        emit(resourceReview)
    }

    suspend fun getItemComplete(idItem: String) = flow {
        val result = mercadoApi.getItemComplete(idItem)
        when (result.code()) {
            200 -> {
                resourceItem.resourceObject = result.body()
                resourceItem.responseAction = SUCCESS
            }
            else -> {
                resourceItem.responseAction = ERROR
            }
        }
        resourceItem.loading = false
        emit(resourceItem)

    }.catch {
        resourceItem.responseAction = ERROR
        emit(resourceItem)
    }
}