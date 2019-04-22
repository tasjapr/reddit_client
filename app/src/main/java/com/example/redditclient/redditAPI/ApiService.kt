package com.example.redditclient.redditAPI

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("top.json?limit=50")
    fun getTopEntries(): Observable<TopEntriesResponse.Result>

    @GET("top.json?limit=50&")
    fun nextPage(@Query("after") name: String): Observable<TopEntriesResponse.Result>

    @GET("top.json?limit=50&")
    fun prevPage(@Query("before") name: String): Observable<TopEntriesResponse.Result>

    companion object {
        fun create(): ApiService {

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://www.reddit.com/")
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}