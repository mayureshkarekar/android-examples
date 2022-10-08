package com.example.daggermvvmdemo.di

import android.content.Context
import androidx.room.Room
import com.example.daggermvvmdemo.db.DATABASE_NAME
import com.example.daggermvvmdemo.db.FakerDB
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideFakerDB(context: Context): FakerDB {
        return Room.databaseBuilder(context, FakerDB::class.java, DATABASE_NAME).build()
    }
}