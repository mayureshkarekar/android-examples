package com.example.networkingdemo.retrofit

import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface StudentRetrofitApi {
    // Making GET request.
    @GET("school/students")
    fun getAllStudents(): Call<StudentResponse>

    // Making GET request with query parameter.
    @GET("school/students")
    fun getAllStudentsByGender(@Query("gender") gender: String): Call<StudentResponse>

    // Making POST request with form data.
    @Multipart
    @POST("school/students/delhi")
    fun getAllDelhiStudents(@Part("city") city: RequestBody): Call<StudentResponse>

    // Making POST request with JSON data.
    @POST("school/students/{city}")
    fun getAllStudentsByCity(
        @Path("city") cityName: String,
        @Body city: City
    ): Call<StudentResponse>
}