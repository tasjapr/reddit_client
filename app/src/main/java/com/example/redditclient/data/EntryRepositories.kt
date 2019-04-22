package com.example.redditclient.data

import com.example.redditclient.redditAPI.TopEntriesResponse
import io.reactivex.Observable

class EntryRepositories {

    val localDS = LocalDataSource()
    private val remoteDS = RemoteDataSource()

    fun getTopEntries(): Observable<TopEntriesResponse.Result> {
        return remoteDS.getTopEntries()
    }

    fun nextPage(name: String):Observable<TopEntriesResponse.Result>{
        return remoteDS.nextPage(name)
    }

    fun prevPage(name: String):Observable<TopEntriesResponse.Result>{
        return remoteDS.prevPage(name)
    }

    fun getFavorites(){
        //todo
    }
}


