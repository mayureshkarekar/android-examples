package com.example.networkingdemo.retrofit

import com.example.networkingdemo.MainActivity
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    val studentAPI: StudentRetrofitApi = Retrofit.Builder()
        .baseUrl(MainActivity.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(StudentRetrofitApi::class.java)
}