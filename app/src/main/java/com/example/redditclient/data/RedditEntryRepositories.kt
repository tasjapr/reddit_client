package com.example.redditclient.data

import com.example.redditclient.ui.Entry

class RedditEntryRepositories {

    val localDataSource = RedditEntryLocalDataSourse()
    val remoteDataSource = RedditEntryRemoteDataSourse()

    fun getEntries(onEntryReadyCallback: OnEntryReadyCallback) {
        remoteDataSource.getEntries(object : OnEntryRemoteReadyCallback {
            override fun onRemoteDataReady(data: ArrayList<Entry>) {
                localDataSource.saveEntries(data)
                onEntryReadyCallback.onDataReady(data)
            }
        })
    }
}

interface OnEntryReadyCallback {
    fun onDataReady(data: ArrayList<Entry>)
}
