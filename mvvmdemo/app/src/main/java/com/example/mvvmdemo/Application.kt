package com.example.mvvmdemo

import android.app.Application
import com.example.mvvmdemo.db.CharacterDatabase
import com.example.mvvmdemo.remote.CharacterRemoteService
import com.example.mvvmdemo.repository.CharacterRepository

class Application : Application() {
    val characterRepository by lazy {
        val characterDatabase = CharacterDatabase.getInstance(this)
        val characterApi = CharacterRemoteService.characterApi
        CharacterRepository(this, characterDatabase, characterApi)
    }
}