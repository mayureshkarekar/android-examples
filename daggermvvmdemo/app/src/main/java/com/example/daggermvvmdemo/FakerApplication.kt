package com.example.daggermvvmdemo

import android.app.Application
import com.example.daggermvvmdemo.di.AppComponent
import com.example.daggermvvmdemo.di.DaggerAppComponent

class FakerApplication : Application() {
    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)
    }
}