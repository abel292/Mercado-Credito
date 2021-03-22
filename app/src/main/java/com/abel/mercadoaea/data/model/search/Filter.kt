package com.abel.mercadoaea.data.model.search

data class Filter(
    val id: String,
    val name: String,
    val type: String,
    val values: List<ValueX?>
)