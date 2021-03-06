package com.abel.mercadoaea.data.model.search

data class Seller(
    val car_dealer: Boolean,
    val eshop: Eshop,
    val id: Int,
    val permalink: String,
    val real_estate_agency: Boolean,
    val registration_date: String,
    val tags: List<String>
)