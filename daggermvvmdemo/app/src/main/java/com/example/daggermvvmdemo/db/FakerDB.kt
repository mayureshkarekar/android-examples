package com.example.daggermvvmdemo.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.daggermvvmdemo.model.Product

@Database(entities = [Product::class], version = DATABASE_VERSION)
abstract class FakerDB : RoomDatabase() {
    abstract fun getFakerDao(): FakerDao
}

const val DATABASE_NAME = "FakerDB"
const val DATABASE_VERSION = 1