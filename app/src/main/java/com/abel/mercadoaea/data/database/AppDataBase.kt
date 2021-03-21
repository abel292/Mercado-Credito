package com.abel.mercadoaea.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [SuggestEntity::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun suggestDao():SuggestDao
}