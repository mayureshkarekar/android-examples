package com.example.daggerdemo.di

import com.example.daggerdemo.repository.SQLRepository
import com.example.daggerdemo.repository.UserRepository
import dagger.Binds
import dagger.Module

@Module
abstract class UserRepositoryModule {
    /*@Provides
    fun getFirebaseRepository(): UserRepository {
        return FirebaseRepository()
    }*/

    @Binds
    @ActivityScope
    abstract fun getSQLRepository(sqlRepository: SQLRepository): UserRepository
}