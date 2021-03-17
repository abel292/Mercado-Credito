package com.abel.mercadoaea.data.api

import com.abel.mercadoaea.data.model.description.ResponseDescription
import com.abel.mercadoaea.data.model.item.ResponseItem
import com.abel.mercadoaea.data.model.review.ResponseReview
import com.abel.mercadoaea.data.model.search.ResponseSearch
import com.abel.mercadoaea.data.model.suggest.ResponseSuggest
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MercadoApi {

    @GET("/sites/MLA/autosuggest")
    suspend fun getSuggest(
        @Query("showFilters") showFilters: Boolean,
        @Query("limit") limit: String,
        @Query("api_version") api_version: String,
        @Query("q") q: String
    ): Response<ResponseSuggest>

    @GET("/sites/MLA/search")
    suspend fun getListSearchedItems(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Query("q") q: String,
        @Query("attributes") attributes: String
    ): Response<ResponseSearch>

    @GET("/items/{idItem}/description?api_version=2")
    suspend fun getDescription(@Path(value = "idItem") idItem: String): Response<ResponseDescription>

    @GET("/reviews/item/{idItem}")
    suspend fun getReviews(@Path(value = "idItem") idItem: String): Response<ResponseReview>

    @GET("/items/{idItem}")
    suspend fun getItemComplete(@Path(value = "idItem") idItem: String): Response<ResponseItem>
}