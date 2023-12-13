package com.example.hiltdemo

import android.util.Log
import javax.inject.Inject

interface Analytics {
    val TAG: String

    fun trackEvent(eventName: String)
}

class FirebaseAnalytics : Analytics {
    override val TAG: String = "FirebaseAnalytics"

    override fun trackEvent(eventName: String) {
        Log.d(TAG, "Tracking Event $eventName")
    }
}

class WebEngageAnalytics @Inject constructor() : Analytics {
    override val TAG: String = "WebEngageAnalytics"

    override fun trackEvent(eventName: String) {
        Log.d(TAG, "Tracking Event $eventName")
    }
}