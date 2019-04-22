package com.example.redditclient.data

import com.example.redditclient.redditAPI.EntriesResponse
import io.reactivex.Observable

class EntryRepositories {

    private val remoteDS = RemoteDataSource()

    fun getTopEntries(): Observable<EntriesResponse.Result> {
        return remoteDS.getTopEntries()
    }

    fun nextPage(name: String): Observable<EntriesResponse.Result> {
        return remoteDS.nextPage(name)
    }

    fun prevPage(name: String): Observable<EntriesResponse.Result> {
        return remoteDS.prevPage(name)
    }
}


