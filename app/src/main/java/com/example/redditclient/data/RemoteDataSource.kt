package com.example.redditclient.data

import com.example.redditclient.redditAPI.ApiService
import com.example.redditclient.redditAPI.TopResponseModel
import io.reactivex.Observable

class RemoteDataSource {

    private val apiService by lazy { ApiService.create() }

    fun getTopEntries(): Observable<TopResponseModel.Result> {
        return apiService.getTopEntries()
    }

    fun nextPage(name: String): Observable<TopResponseModel.Result> {
        return apiService.nextPage(name)
    }

    fun prevPage(name: String): Observable<TopResponseModel.Result> {
        return apiService.prevPage(name)
    }

}