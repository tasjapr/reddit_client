package com.example.redditclient.ui

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.redditclient.data.RedditEntryRepositories
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class ViewModel(application: Application) : AndroidViewModel(application) {

    var redditEntryRepo: RedditEntryRepositories = RedditEntryRepositories()

    var entries = MutableLiveData<ArrayList<Entry>>()

    val isLoading = ObservableField(false)
    private val compositeDisposable = CompositeDisposable()

    fun loadEntries() {
        isLoading.set(true)

        compositeDisposable.add(
            redditEntryRepo
                .getEntries()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<ArrayList<Entry>>() {

                    override fun onError(e: Throwable) {
                        //todo
                    }

                    override fun onNext(data: ArrayList<Entry>) {
                        entries.value = data
                    }

                    override fun onComplete() {
                        isLoading.set(false)
                    }
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }
}