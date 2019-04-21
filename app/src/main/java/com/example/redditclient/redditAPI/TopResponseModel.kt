package com.example.redditclient.redditAPI


object TopResponseModel {

    data class Result(val data: MainData)
    data class MainData(val children: List<Children>)
    data class Children(val data: Data)
    data class Data(
        val subreddit: String, val author: String, val created_utc: Long, val title: String,
        val num_comments: Int, val score: Int, val url: String, val thumbnail:String
    )
}