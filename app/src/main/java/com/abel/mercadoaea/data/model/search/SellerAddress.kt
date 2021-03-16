package com.abel.mercadoaea.data.model.search

data class SellerAddress(
    val address_line: String,
    val city: City,
    val comment: String,
    val country: Country,
    val id: String,
    val latitude: String,
    val longitude: String,
    val state: State,
    val zip_code: String
)