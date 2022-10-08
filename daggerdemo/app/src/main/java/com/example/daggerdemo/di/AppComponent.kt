package com.example.daggerdemo.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AnalyticsModule::class])
interface AppComponent {
    // Using Factory Pattern.
    fun getRegistrationComponentFactory(): RegistrationComponent.Factory

    // Using Builder Pattern.
    // fun getRegistrationComponentBuilder(): RegistrationComponent.Builder
}