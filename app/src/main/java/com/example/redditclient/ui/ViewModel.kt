package com.example.redditclient.ui

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.redditclient.data.OnEntryReadyCallback
import com.example.redditclient.data.RedditEntryRepositories

class ViewModel : ViewModel() {
    var redditEntryRepo: RedditEntryRepositories =
        RedditEntryRepositories()

    val isLoading = ObservableField(false)
    var entries = MutableLiveData<ArrayList<Entry>>()

    fun loadRepositories() {
        isLoading.set(true)
        redditEntryRepo.getEntries(object : OnEntryReadyCallback {
            override fun onDataReady(data: ArrayList<Entry>) {
                isLoading.set(false)
                entries.value = data
            }
        })
    }
}