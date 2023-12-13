package com.example.hiltdemo.repository

import com.example.hiltdemo.Analytics
import com.example.hiltdemo.di.FirebaseQualifier
import com.example.hiltdemo.remote.NotificationService
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val notificationService: NotificationService,
    @FirebaseQualifier private val analytics: Analytics
) {
    fun registerUser() {
        notificationService.sendMessage("User registered successfully.")
        analytics.trackEvent("user_registration")
    }
}