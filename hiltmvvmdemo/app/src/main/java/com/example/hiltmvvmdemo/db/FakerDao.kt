package com.example.hiltmvvmdemo.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hiltmvvmdemo.model.PRODUCT_TABLE_NAME
import com.example.hiltmvvmdemo.model.Product

@Dao
interface FakerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProducts(products: List<Product>)

    @Query("SELECT * FROM $PRODUCT_TABLE_NAME")
    suspend fun getProducts(): List<Product>
}