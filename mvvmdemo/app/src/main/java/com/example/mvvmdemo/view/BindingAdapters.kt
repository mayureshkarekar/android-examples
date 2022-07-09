package com.example.mvvmdemo.view

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("android:imageUrl", "android:centerCrop")
fun ImageView.imageUrl(url: String, centerCrop: Boolean) {
    Glide.with(this).load(url).run {
        if (centerCrop) circleCrop().into(this@imageUrl) else into(this@imageUrl)
    }
}