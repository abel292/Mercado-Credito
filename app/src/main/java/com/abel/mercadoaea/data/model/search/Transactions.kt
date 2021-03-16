package com.abel.mercadoaea.data.model.search

data class Transactions(
    val canceled: Int,
    val completed: Int,
    val period: String,
    val ratings: Ratings,
    val total: Int
)