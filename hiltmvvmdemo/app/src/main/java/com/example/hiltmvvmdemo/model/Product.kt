package com.example.hiltmvvmdemo.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = PRODUCT_TABLE_NAME)
data class Product(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val category: String,
    val description: String,
    val image: String,
    val price: Double,
    val title: String
)

const val PRODUCT_TABLE_NAME = "product"