package com.example.redditclient.ui

import android.app.Application
import android.content.Context
import android.graphics.Color
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.redditclient.data.EntryRepositories
import com.example.redditclient.redditAPI.TopEntriesResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class ViewModel(application: Application) : AndroidViewModel(application) {

    var entryRepo: EntryRepositories = EntryRepositories()
    var entries = MutableLiveData<ArrayList<TopEntriesResponse.Data>>()
    val isLoading = ObservableField(false)
    private val compositeDisposable = CompositeDisposable()

    private var firstEntryName: String = ""
    private var lastEntryName: String = ""


    fun loadTopEntries() {
        isLoading.set(true)

        compositeDisposable.add(
            entryRepo
                .getTopEntries()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<TopEntriesResponse.Result>() {
                    override fun onError(e: Throwable) {
                        //todo
//                        Log.d("Bazinga", "error: $e")
                        isLoading.set(false)
                    }

                    override fun onNext(t: TopEntriesResponse.Result) {
                        entries.value = getSingleEntry(t.data.children)
                    }

                    override fun onComplete() {
                        isLoading.set(false)
                    }
                })
        )
    }

    fun loadNextPage() {
        isLoading.set(true)

        compositeDisposable.add(
            entryRepo
                .nextPage(lastEntryName)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<TopEntriesResponse.Result>() {
                    override fun onError(e: Throwable) {
                        //todo
//                        Log.d("Bazinga", "error: $e")
                        isLoading.set(false)
                    }

                    override fun onNext(t: TopEntriesResponse.Result) {
                        entries.value = getSingleEntry(t.data.children)
                    }

                    override fun onComplete() {
                        isLoading.set(false)
                    }
                })
        )
    }

    fun loadPrevPage() {
        isLoading.set(true)

        compositeDisposable.add(
            entryRepo
                .prevPage(firstEntryName)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<TopEntriesResponse.Result>() {
                    override fun onError(e: Throwable) {
                        //todo
//                        Log.d("Bazinga", "error: $e")
                        isLoading.set(false)
                    }

                    override fun onNext(t: TopEntriesResponse.Result) {
                        entries.value = getSingleEntry(t.data.children)
                    }

                    override fun onComplete() {
                        isLoading.set(false)
                    }
                })
        )
    }

    fun openEntryInChromeTab(index: Int, context: Context) {
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        builder.setToolbarColor(Color.parseColor("#004d40"))
        builder.setSecondaryToolbarColor(Color.parseColor("#ff5252"))

        customTabsIntent.launchUrl(context, Uri.parse(entries.value?.get(index)?.url))
    }

    private fun getSingleEntry(data: List<TopEntriesResponse.Children>): ArrayList<TopEntriesResponse.Data> {
        val entries = ArrayList<TopEntriesResponse.Data>(data.size)
        for (i in 0..49) {
            entries.add(
                TopEntriesResponse.Data(
                    numOfEntry = i + 1,
                    title = data[i].data.title,
                    author = data[i].data.author,
                    score = data[i].data.score,
                    subreddit = data[i].data.subreddit,
                    numOfcomments = data[i].data.numOfcomments,
                    created_utc = data[i].data.created_utc,
                    url = data[i].data.url,
                    thumbnail = data[i].data.thumbnail,
                    name = data[i].data.name
                )
            )
        }

        firstEntryName = entries[0].name
        lastEntryName = entries[49].name

        isLoading.set(false)
        return entries
    }


    override fun onCleared() {
        super.onCleared()
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }
}
