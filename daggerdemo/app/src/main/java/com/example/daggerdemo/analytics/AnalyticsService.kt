package com.example.daggerdemo.analytics

import android.util.Log

interface AnalyticsService {
    fun trackEvent(eventName: String, type: String)
}

class MixpanelAnalytics : AnalyticsService {
    override fun trackEvent(eventName: String, type: String) {
        Log.d("MixpanelAnalytics", "Tracking event $eventName")
    }
}

class FirebaseAnalytics : AnalyticsService {
    override fun trackEvent(eventName: String, type: String) {
        Log.d("FirebaseAnalytics", "Tracking event $eventName")
    }
}