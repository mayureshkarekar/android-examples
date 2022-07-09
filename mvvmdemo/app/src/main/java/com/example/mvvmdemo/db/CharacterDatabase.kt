package com.example.mvvmdemo.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mvvmdemo.model.Character

private const val DATABASE_VERSION = 1
private const val DATABASE_NAME = "characterDB"

@Database(entities = [Character::class], version = DATABASE_VERSION, exportSchema = false)
abstract class CharacterDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao

    companion object {
        @Volatile
        private var INSTANCE: CharacterDatabase? = null

        fun getInstance(context: Context): CharacterDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext, CharacterDatabase::class.java, DATABASE_NAME
                ).build().also { INSTANCE = it }
            }
        }
    }
}