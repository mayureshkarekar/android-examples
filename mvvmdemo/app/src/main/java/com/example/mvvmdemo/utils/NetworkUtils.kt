package com.example.mvvmdemo.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

object NetworkUtils {
    fun isInternetAvailable(context: Context): Boolean {
        (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).run {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                return getNetworkCapabilities(activeNetwork)?.hasCapability(
                    NetworkCapabilities.NET_CAPABILITY_INTERNET
                ) ?: false
            } else {
                @Suppress("DEPRECATION")
                return activeNetworkInfo?.isConnected ?: false
            }
        }
    }
}