package com.example.redditclient.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Favorite::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favDao(): FavoriteDao
}