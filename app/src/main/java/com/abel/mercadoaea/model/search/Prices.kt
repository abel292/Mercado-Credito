package com.abel.mercadoaea.model.search

data class Prices(
    val id: String,
    val payment_method_prices: List<Any>,
    val presentation: Presentation,
    val prices: List<Price>
)