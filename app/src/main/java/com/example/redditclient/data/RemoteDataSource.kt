package com.example.redditclient.data

import com.example.redditclient.redditAPI.ApiService
import com.example.redditclient.redditAPI.EntriesResponse
import io.reactivex.Observable

class RemoteDataSource {

    private val apiService by lazy { ApiService.create() }

    fun getTopEntries(): Observable<EntriesResponse.Result> {
        return apiService.getTopEntries()
    }

    fun nextPage(name: String): Observable<EntriesResponse.Result> {
        return apiService.nextPage(name)
    }

    fun prevPage(name: String): Observable<EntriesResponse.Result> {
        return apiService.prevPage(name)
    }

}