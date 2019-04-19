package com.example.redditclient.data

import android.os.Handler
import com.example.redditclient.ui.Entry

class RedditEntryLocalDataSourse {

    fun getEntries(onEntryReadyCallback: OnEntryLocalReadyCallback) {
        var arrayList = ArrayList<Entry>()
        arrayList.add(Entry(1, "First from local", "Author 1", null, 12, 1))
        arrayList.add(Entry(2, "Second from local", "Author 2", "community of Physics", 112, 1678))
        arrayList.add(Entry(3, "Third from local", "Author 3", "bazinga mans", 0, -100))

        Handler().postDelayed({ onEntryReadyCallback.onLocalDataReady(arrayList) }, 0)
    }

    fun saveEntries(arrayList: ArrayList<Entry>) {
        //todo save favorite repositories in  DB
    }
}

interface OnEntryLocalReadyCallback {
    fun onLocalDataReady(data: ArrayList<Entry>)
}
