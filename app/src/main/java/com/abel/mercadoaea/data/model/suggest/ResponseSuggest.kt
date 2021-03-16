package com.abel.mercadoaea.data.model.suggest

data class ResponseSuggest(
    val q: String,
    val suggested_queries: List<SuggestedQuery>
)