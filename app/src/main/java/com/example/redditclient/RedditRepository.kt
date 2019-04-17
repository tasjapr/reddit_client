package com.example.redditclient

import android.os.Handler
import com.example.redditclient.ui.Entry

class RedditRepository {

    fun getEntries(onEntryReadyCallback: OnEntryReadyCallback) {
        var arrayList = ArrayList<Entry>()
        arrayList.add(Entry(50, "First", "Author 1", null, 12, 1))
        arrayList.add(Entry(44, "Second", "Author 2", "communityOfPhisics", 112, 1678))
        arrayList.add(Entry(3, "Third", "Author 3", "bazinga_mans", 0, -100))

        Handler().postDelayed({ onEntryReadyCallback.onDataReady(arrayList) }, 0)
    }
}

interface OnEntryReadyCallback {
    fun onDataReady(data: ArrayList<Entry>)
}