package com.example.daggerdemo

import android.app.Application
import com.example.daggerdemo.di.AppComponent

class DaggerApplication : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().build()
    }
}