package com.abel.mercadoaea.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ItemEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val urlImage: String,
    var price: String
)