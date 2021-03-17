package com.abel.mercadoaea.data.model.search

data class Value(
    val id: String,
    val name: String,
    val source: Long,
    val struct: Struct?
)