package com.example.daggermvvmdemo.di

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.daggermvvmdemo.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, DatabaseModule::class, ViewModelModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)

    // fun getViewModelMap(): Map<String, ViewModel>
    fun getViewModelMap(): Map<Class<*>, ViewModel>

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}