package com.example.composemvvmdemo.api

import com.example.composemvvmdemo.model.Fruit
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface FruitsyAPI {
    @GET("v3/b/65887a71dc746540188842b4?meta=false")
    suspend fun getFruitBenefits(@Header("X-JSON-Path") fruit: String): Response<List<Fruit>>

    @GET("v3/b/65887a71dc746540188842b4?meta=false")
    @Headers("X-JSON-Path: fruits..fruit")
    suspend fun getFruits(): Response<List<String>>
}