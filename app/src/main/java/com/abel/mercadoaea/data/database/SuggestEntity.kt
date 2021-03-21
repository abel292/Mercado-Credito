package com.abel.mercadoaea.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SuggestEntity(
    @PrimaryKey
    var suggest: String
)