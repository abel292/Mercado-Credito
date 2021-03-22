package com.abel.mercadoaea.data.model.search

data class ResponseSearch(
    val results: List<Result>,
    val filters: List<Filter>?,
    var title: String?
)