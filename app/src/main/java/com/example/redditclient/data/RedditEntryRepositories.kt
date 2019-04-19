package com.example.redditclient.data

import com.example.redditclient.ui.Entry
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

class RedditEntryRepositories {

    val localDataSource = RedditEntryLocalDataSource()
    private val remoteDataSource = RedditEntryRemoteDataSource()


    fun getEntries(): Observable<ArrayList<Entry>> {
        return remoteDataSource.getEntries().delay(500, TimeUnit.MILLISECONDS)
    }

    fun getEntriesFromFavorites() {
        //TODO
    }

    fun saveEntryToFavorite() {
        //TODO
    }
}
