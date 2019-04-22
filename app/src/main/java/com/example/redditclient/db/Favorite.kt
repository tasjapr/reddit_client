package com.example.redditclient.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Favorite(
    @PrimaryKey val favContent: Int,
    @ColumnInfo(name = "subreddit") val subreddit: String?
)