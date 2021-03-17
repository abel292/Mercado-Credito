package com.abel.mercadoaea.data.model.search

data class Prices(
    val id: String,
    val payment_method_prices: List<String>?,
    val presentation: Presentation,
    val prices: List<Price>
)