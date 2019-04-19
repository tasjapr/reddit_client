package com.example.redditclient.data

import com.example.redditclient.ui.Entry
import io.reactivex.Observable

class RedditEntryLocalDataSource {

    fun getEntries(): Observable<ArrayList<Entry>> {
        var listOfEntries = ArrayList<Entry>()
        listOfEntries.add(Entry(1, "First from local", "Author 1", null, 12, 1))
        listOfEntries.add(Entry(2, "Second from local", "Author 2", "community of Physics", 112, 1678))
        listOfEntries.add(Entry(3, "Third from local", "Author 3", "bazinga mans", 0, -100))

        return Observable.just(listOfEntries)
    }

    fun saveEntries(listOfEntries: ArrayList<Entry>) {
        //TODO save favorite repositories in  DB
    }
}
