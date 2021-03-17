package com.abel.mercadoaea.data.model.search

data class Claims(
    val excluded: Excluded,
    val period: String,
    val rate: Int,
    val value: Int
)