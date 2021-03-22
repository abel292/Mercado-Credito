package com.abel.mercadoaea.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "_ITEM_ENTITY", indices = [Index(value = ["ID_ITEM"], unique = true)]
)
data class ItemEntity(
    @ColumnInfo(name = "ID_ITEM")
    val id: String,
    val title: String,
    val urlImage: String,
    var price: String
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID_CREATED")
    var idCreated: Int? = null
}