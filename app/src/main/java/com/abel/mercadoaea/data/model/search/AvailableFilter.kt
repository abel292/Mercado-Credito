package com.abel.mercadoaea.data.model.search

data class AvailableFilter(
    val id: String,
    val name: String,
    val type: String,
    val values: List<Value>
)