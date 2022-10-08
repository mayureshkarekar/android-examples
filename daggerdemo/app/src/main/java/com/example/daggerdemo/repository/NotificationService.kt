package com.example.daggerdemo.repository

import android.util.Log
import com.example.daggerdemo.di.ActivityScope
import javax.inject.Inject

interface NotificationService {
    fun send(to: String, message: String)
}

@ActivityScope
class EmailService @Inject constructor() : NotificationService {
    override fun send(to: String, message: String) {
        Log.d("EmailService", "Email sent --> To: $to, Message: $message")
    }
}

class MessageService(private val retryCount: Int) : NotificationService {
    override fun send(to: String, message: String) {
        Log.d(
            "MessageService",
            "Message sent --> To: $to, Message: $message, Retry count: $retryCount"
        )
    }
}