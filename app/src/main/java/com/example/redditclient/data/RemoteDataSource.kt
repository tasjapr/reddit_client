package com.example.redditclient.data

import com.example.redditclient.redditAPI.ApiService
import com.example.redditclient.redditAPI.TopEntriesResponse
import io.reactivex.Observable

class RemoteDataSource {

    private val apiService by lazy { ApiService.create() }

    fun getTopEntries(): Observable<TopEntriesResponse.Result> {
        return apiService.getTopEntries()
    }

    fun nextPage(name: String): Observable<TopEntriesResponse.Result> {
        return apiService.nextPage(name)
    }

    fun prevPage(name: String): Observable<TopEntriesResponse.Result> {
        return apiService.prevPage(name)
    }

}