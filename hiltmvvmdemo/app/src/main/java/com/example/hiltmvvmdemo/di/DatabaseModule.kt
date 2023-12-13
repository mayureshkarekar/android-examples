package com.example.hiltmvvmdemo.di

import android.content.Context
import androidx.room.Room
import com.example.hiltmvvmdemo.db.DATABASE_NAME
import com.example.hiltmvvmdemo.db.FakerDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideFakerDB(@ApplicationContext context: Context): FakerDB {
        return Room.databaseBuilder(context, FakerDB::class.java, DATABASE_NAME).build()
    }
}