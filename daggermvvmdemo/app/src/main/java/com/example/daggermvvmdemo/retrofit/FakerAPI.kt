package com.example.daggermvvmdemo.retrofit

import com.example.daggermvvmdemo.model.Product
import com.example.daggermvvmdemo.utils.Constants.GET_PRODUCTS
import retrofit2.Response
import retrofit2.http.GET

interface FakerAPI {
    @GET(GET_PRODUCTS)
    suspend fun getProducts(): Response<List<Product>>
}