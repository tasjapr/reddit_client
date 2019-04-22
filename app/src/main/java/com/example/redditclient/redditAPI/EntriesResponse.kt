package com.example.redditclient.redditAPI

import androidx.databinding.BaseObservable
import org.ocpsoft.prettytime.PrettyTime
import java.util.*


object EntriesResponse {

    data class Result(val data: MainData)

    data class MainData(val children: List<Children>)

    data class Children(val data: Data)

    data class Data(
        val numOfEntry: Int, val subreddit: String, val author: String, val created_utc: Long, val title: String,
        val num_comments: Int, val score: Int, val url: String, val thumbnail: String, var name: String
    ) : BaseObservable() {
        private var timeAgo = PrettyTime(Locale.ENGLISH).format(Date(created_utc * 1000))
        var info: String = "post by $author in $subreddit $timeAgo"
    }
}