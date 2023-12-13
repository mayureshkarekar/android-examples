package com.example.hiltdemo.remote

import android.util.Log
import javax.inject.Inject

private const val TAG = "NotificationService"

class NotificationService @Inject constructor() {
    fun sendMessage(message: String) {
        Log.d(TAG, message)
    }
}