package com.example.mvvmdemo.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CharacterRemoteService {
    private const val CHARACTER_BASE_URL = "https://rickandmortyapi.com/api/"

    val characterApi: CharacterApi by lazy {
        val logging = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
        val client = OkHttpClient.Builder().addInterceptor(logging).build()

        Retrofit.Builder()
            .baseUrl(CHARACTER_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CharacterApi::class.java)
    }
}