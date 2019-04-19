package com.example.redditclient.data

import com.example.redditclient.ui.Entry
import io.reactivex.Observable

class RedditEntryRemoteDataSource {

    fun getEntries(): Observable<ArrayList<Entry>> {
        var arrayList = ArrayList<Entry>()
        arrayList.add(Entry(1, "First from remote", "Author 1", null, 12, 1))
        arrayList.add(Entry(2, "Second from remote", "Author 2", "community of Physics", 112, 1678))
        arrayList.add(Entry(3, "Third from remote", "Author 3", "bazinga mans", 0, -100))

        return Observable.just(arrayList)
    }
}