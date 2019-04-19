package com.example.redditclient

import android.content.Context
import android.net.ConnectivityManager

class NetworkManager(private var applicationContext: Context) {
    private var status: Boolean? = false

    val isConnectedToInternet: Boolean?
        get() {
            val connectivityManager =
                applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected
        }
}
