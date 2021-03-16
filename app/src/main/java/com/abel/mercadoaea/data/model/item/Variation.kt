package com.abel.mercadoaea.data.model.item

data class Variation(
    val attribute_combinations: List<AttributeCombination>,
    val available_quantity: Int,
    val catalog_product_id: String,
    val id: Long,
    val picture_ids: List<String>,
    val price: Double,
    val sale_terms: List<Any>,
    val sold_quantity: Int
)