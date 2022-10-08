package com.example.daggerdemo.di

import com.example.daggerdemo.view.MainActivity
import dagger.BindsInstance
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [UserRepositoryModule::class, NotificationServiceModule::class])
interface RegistrationComponent {
    fun inject(mainActivity: MainActivity)

    // Factory Pattern (Recommended)
    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance retryCount: Int): RegistrationComponent
    }

    // Builder Pattern
    /*@Subcomponent.Builder
    interface Builder {
        fun build(): RegistrationComponent
        fun retryCount(@BindsInstance retryCount: Int): Builder
    }*/
}