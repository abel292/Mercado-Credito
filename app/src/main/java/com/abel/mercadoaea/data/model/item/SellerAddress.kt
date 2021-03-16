package com.abel.mercadoaea.data.model.item

data class SellerAddress(
    val city: City,
    val country: Country,
    val id: Int,
    val search_location: SearchLocation,
    val state: State
)