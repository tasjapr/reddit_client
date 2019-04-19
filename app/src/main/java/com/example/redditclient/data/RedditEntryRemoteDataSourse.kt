package com.example.redditclient.data

import android.os.Handler
import com.example.redditclient.ui.Entry

class RedditEntryRemoteDataSourse {

    fun getEntries(onEntryReadyCallback: OnEntryRemoteReadyCallback) {
        var arrayList = ArrayList<Entry>()
        arrayList.add(Entry(1, "First from remote", "Author 1", null, 12, 1))
        arrayList.add(Entry(2, "Second from remote", "Author 2", "community of Physics", 112, 1678))
        arrayList.add(Entry(3, "Third from remote", "Author 3", "bazinga mans", 0, -100))

        Handler().postDelayed({ onEntryReadyCallback.onRemoteDataReady(arrayList) }, 0)
    }
}

interface OnEntryRemoteReadyCallback {
    fun onRemoteDataReady(data: ArrayList<Entry>)
}