package com.example.redditclient.ui

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.example.redditclient.BR

class Entry(
    var entryNumber: Int, entryTitle: String, var entryAuthor: String, var entrySubreddit: String?,
    var numberOfComments: Int?, var entryRating: Int?
) : BaseObservable() {

    @get:Bindable
    var entryTitle: String = "Title"
        set(value) {
            field = value
            notifyPropertyChanged(BR.entryTitle)
        }

    var entryInfo: String = "post by $entryAuthor in $entrySubreddit"


}