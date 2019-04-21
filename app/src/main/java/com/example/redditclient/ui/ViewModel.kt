package com.example.redditclient.ui

import android.app.Application
import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.redditclient.data.EntryRepositories
import com.example.redditclient.redditAPI.TopResponseModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class ViewModel(application: Application) : AndroidViewModel(application) {

    var entryRepo: EntryRepositories = EntryRepositories()
    var entries = MutableLiveData<ArrayList<Entry>>()
    val isLoading = ObservableField(false)
    private val compositeDisposable = CompositeDisposable()

    fun loadTopEntries() {
        isLoading.set(true)

        compositeDisposable.add(
            entryRepo
                .getTopEntries()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<TopResponseModel.Result>() {
                    override fun onError(e: Throwable) {
                        //todo
//                        Log.d("Bazinga", "error: $e")
                        isLoading.set(false)
                    }

                    override fun onNext(t: TopResponseModel.Result) {
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

        customTabsIntent.launchUrl(context, Uri.parse(entries.value?.get(index)?.url))
    }

    private fun getSingleEntry(data: List<TopResponseModel.Children>): ArrayList<Entry> {
        val entries = ArrayList<Entry>(data.size)
        for (i in 0..49) {
            entries.add(
                Entry(
                    number = i + 1,
                    title = data[i].data.title,
                    author = data[i].data.author,
                    rating = data[i].data.score,
                    subreddit = data[i].data.subreddit,
                    comments = data[i].data.num_comments,
                    utcTime = data[i].data.created_utc,
                    url = data[i].data.url,
                    thumbnail = data[i].data.thumbnail
                )
            )
        }

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
