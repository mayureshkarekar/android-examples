package com.example.databindingdemo

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

// Creating custom view attribute (Binding Adapter to set attributes/properties of views).
@BindingAdapter("imageUrl")
fun ImageView.imageUrl(url: String) {
    Glide.with(this).load(url).into(this)
}

// Binding Adapter with multiple arguments. You can also specify the name space for the attribute.
@BindingAdapter("android:imageUrl", "android:errorImage")
fun ImageView.imageUrl(url: String, errorImage: Drawable) {
    Glide.with(this).load(url).error(errorImage).into(this)
}