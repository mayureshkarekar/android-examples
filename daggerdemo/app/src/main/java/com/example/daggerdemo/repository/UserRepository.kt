package com.example.daggerdemo.repository

import android.util.Log
import com.example.daggerdemo.analytics.AnalyticsService
import javax.inject.Inject

interface UserRepository {
    fun saveUser(email: String, password: String)
}

class SQLRepository @Inject constructor(private val analyticsService: AnalyticsService) :
    UserRepository {
    override fun saveUser(email: String, password: String) {
        Log.d("SQLRepository", "User saved --> Username: $email, Password: $password")
        analyticsService.trackEvent("Save User", "SQL")
    }
}

class FirebaseRepository() : UserRepository {
    override fun saveUser(email: String, password: String) {
        Log.d("FirebaseRepository", "User saved --> Username: $email, Password: $password")
    }
}