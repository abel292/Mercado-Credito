package com.abel.mercadoaea.data.model.description

data class ResponseDescription(
    val date_created: String,
    val last_updated: String,
    val plain_text: String,
    val snapshot: Snapshot,
    val text: String
)