package com.abel.mercadoaea.model.item

data class AttributeCombination(
    val id: String,
    val name: String,
    val value_id: String,
    val value_name: String,
    val value_struct: Any,
    val values: List<Value>
)