package com.abel.mercadoaea.model.suggest

data class SuggestedQuery(
    val filters: List<Any>,
    val match_end: Int,
    val match_start: Int,
    val q: String
)