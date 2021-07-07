package com.example.recyclerviewdemo.model

import androidx.annotation.StringRes

data class Image(val imageUrl: String, @StringRes val title: Int, @StringRes val content: Int)