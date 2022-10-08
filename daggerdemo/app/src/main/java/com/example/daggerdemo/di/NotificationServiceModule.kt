package com.example.daggerdemo.di

import com.example.daggerdemo.repository.EmailService
import com.example.daggerdemo.repository.MessageService
import com.example.daggerdemo.repository.NotificationService
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class NotificationServiceModule {
    @ActivityScope
    @MessageServiceQualifier
    @Provides
    fun getMessageService(retryCount: Int): NotificationService {
        return MessageService(retryCount)
    }

    @Named("email")
    @Provides
    fun getEmailService(emailService: EmailService): NotificationService {
        return emailService
    }
}