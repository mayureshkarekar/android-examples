package com.example.hiltmvvmdemo.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hiltmvvmdemo.db.FakerDB
import com.example.hiltmvvmdemo.model.Product
import com.example.hiltmvvmdemo.retrofit.FakerAPI
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val fakerAPI: FakerAPI, private val fakerDB: FakerDB
) {
    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> = _products

    suspend fun getProducts() {
        val response = fakerAPI.getProducts()
        if (response.isSuccessful && response.body() != null) {
            fakerDB.getFakerDao().addProducts(response.body()!!)
            _products.postValue(response.body())
        }
    }
}