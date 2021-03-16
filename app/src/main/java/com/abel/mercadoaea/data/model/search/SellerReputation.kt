package com.abel.mercadoaea.data.model.search

data class SellerReputation(
    val level_id: String,
    val metrics: Metrics,
    val power_seller_status: String,
    val transactions: Transactions
)