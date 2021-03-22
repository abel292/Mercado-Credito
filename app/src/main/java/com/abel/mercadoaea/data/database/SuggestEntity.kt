package com.abel.mercadoaea.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "_SUGGEST_ENTITY", indices = [Index(value = ["SUGGEST"], unique = true)])
data class SuggestEntity(

    @ColumnInfo(name = "SUGGEST")
    var suggest: String
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    var id: Int? = null
}