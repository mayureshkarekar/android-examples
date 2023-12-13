package com.example.hiltmvvmdemo.retrofit

import com.example.hiltmvvmdemo.model.Product
import com.example.hiltmvvmdemo.utils.Constants.GET_PRODUCTS
import retrofit2.Response
import retrofit2.http.GET

interface FakerAPI {
    @GET(GET_PRODUCTS)
    suspend fun getProducts(): Response<List<Product>>
}