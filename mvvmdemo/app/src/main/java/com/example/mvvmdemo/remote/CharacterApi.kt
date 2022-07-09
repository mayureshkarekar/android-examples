package com.example.mvvmdemo.remote

import com.example.mvvmdemo.model.CharacterResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterApi {
    @GET("character")
    suspend fun getCharacters(@Query("page") page: Int): Response<CharacterResponse>
}