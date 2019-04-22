package com.example.redditclient.data

import android.content.Context
import androidx.room.Room
import com.example.redditclient.db.AppDatabase
import com.example.redditclient.ui.Entry

class LocalDataSource(context: Context) {

    fun saveEntries(listOfEntries: ArrayList<Entry>) {
        //TODO save favorite repositories in  DB
    }

    val db = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "fav_db"
    ).build()
}
