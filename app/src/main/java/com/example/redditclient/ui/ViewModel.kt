package com.example.redditclient.ui

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.redditclient.OnEntryReadyCallback
import com.example.redditclient.RedditRepository

class ViewModel : ViewModel() {
    var redditRepo: RedditRepository = RedditRepository()
    val text = ObservableField("old data")

    val isLoading = ObservableField(false)
    var entries = MutableLiveData<ArrayList<Entry>>()

    fun loadRepositories() {
        isLoading.set(true)
        redditRepo.getEntries(object : OnEntryReadyCallback {
            override fun onDataReady(data: ArrayList<Entry>) {
                isLoading.set(false)
                entries.value = data
            }
        })
    }
}