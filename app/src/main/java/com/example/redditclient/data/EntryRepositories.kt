package com.example.redditclient.data

import com.example.redditclient.redditAPI.TopResponseModel
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

class EntryRepositories {

    val localDS = LocalDataSource()
    private val remoteDS = RemoteDataSource()

    fun getTopEntries(): Observable<TopResponseModel.Result> {
        return remoteDS.getTopEntries()
    }

    fun nextPage(name: String):Observable<TopResponseModel.Result>{
        return remoteDS.nextPage(name)
    }

    fun prevPage(name: String):Observable<TopResponseModel.Result>{
        return remoteDS.prevPage(name)
    }

    fun getFavorites(){
        //todo
    }
}


