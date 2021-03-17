package com.abel.mercadoaea.data.model.suggest

data class SuggestedQuery(
    val match_end: Int,
    val match_start: Int,
    val q: String
)