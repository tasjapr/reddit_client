package com.example.redditclient.ui

import android.app.Application
import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.redditclient.NetworkManager
import com.example.redditclient.data.EntryRepositories
import com.example.redditclient.redditAPI.EntriesResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class ViewModel(application: Application) : AndroidViewModel(application) {

    var entryRepo: EntryRepositories = EntryRepositories()
    var entries = MutableLiveData<ArrayList<EntriesResponse.Data>>()
    val isLoading = ObservableField(false)
    private val context = application.applicationContext
    private val compositeDisposable = CompositeDisposable()
    private val networkManager = NetworkManager(context)

    private var firstEntryName: String = ""
    private var lastEntryName: String = ""


    fun loadTopEntries() {
        isLoading.set(true)

        if (networkManager.isConnectedToInternet) {
            compositeDisposable.add(
                entryRepo
                    .getTopEntries()
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(object : DisposableObserver<EntriesResponse.Result>() {
                        override fun onError(e: Throwable) {
                            Toast.makeText(context, "Check your internet connection and try again", Toast.LENGTH_LONG)
                                .show()
                            isLoading.set(false)
                        }

                        override fun onNext(t: EntriesResponse.Result) {
                            entries.value = getSingleEntry(t.data.children)
                        }

                        override fun onComplete() {
                            isLoading.set(false)
                        }
                    })
            )
        }
    }

    fun loadNextPage() {
        isLoading.set(true)

        compositeDisposable.add(
            entryRepo
                .nextPage(lastEntryName)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<EntriesResponse.Result>() {
                    override fun onError(e: Throwable) {
                        Toast.makeText(context, "Check your internet connection and try again", Toast.LENGTH_LONG)
                            .show()
                        isLoading.set(false)
                    }

                    override fun onNext(t: EntriesResponse.Result) {
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
                .subscribeWith(object : DisposableObserver<EntriesResponse.Result>() {
                    override fun onError(e: Throwable) {
                        Toast.makeText(context, "Check your internet connection and try again", Toast.LENGTH_LONG)
                            .show()
                        isLoading.set(false)
                    }

                    override fun onNext(t: EntriesResponse.Result) {
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

    private fun getSingleEntry(data: List<EntriesResponse.Children>): ArrayList<EntriesResponse.Data> {
        val entries = ArrayList<EntriesResponse.Data>(data.size)
        for (i in 0..49) {
            entries.add(
                EntriesResponse.Data(
                    numOfEntry = i + 1,
                    title = data[i].data.title,
                    author = data[i].data.author,
                    score = data[i].data.score,
                    subreddit = data[i].data.subreddit,
                    num_comments = data[i].data.num_comments,
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
