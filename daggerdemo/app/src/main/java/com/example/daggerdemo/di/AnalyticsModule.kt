package com.example.daggerdemo.di

import com.example.daggerdemo.analytics.AnalyticsService
import com.example.daggerdemo.analytics.MixpanelAnalytics
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AnalyticsModule {
    @Singleton
    @Provides
    fun getAnalyticsService(): AnalyticsService {
        return MixpanelAnalytics()
    }
}