package com.example.redditclient.ui

import androidx.databinding.BaseObservable
import org.ocpsoft.prettytime.PrettyTime
import java.util.*

class Entry(
    var number: Int, var title: String, var author: String, var subreddit: String?,
    var comments: Int?, var rating: Int?, var utcTime: Long, var url: String, var thumbnail: String
) : BaseObservable() {

    private var timeAgo = PrettyTime(Locale.ENGLISH).format(Date(utcTime * 1000))

    var info: String = "post by $author in $subreddit $timeAgo"
}