package com.example.redditclient.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM favorite")
    fun getAll(): List<Favorite>

    @Query("SELECT * FROM favorite WHERE favContent IN (:contentType)")
    fun loadAllByIds(contentType: IntArray): List<Favorite>

    @Query("SELECT * FROM favorite WHERE subreddit LIKE :sub")
    fun findBySubreddit(sub: String): Favorite

    @Insert
    fun insertAll(vararg favs: Favorite)

    @Delete
    fun delete(fav: Favorite)
}