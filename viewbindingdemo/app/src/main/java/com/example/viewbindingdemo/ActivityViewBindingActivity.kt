package com.example.viewbindingdemo

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.viewbindingdemo.databinding.ActivityViewBindingBinding

class ActivityViewBindingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityViewBindingBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflating the layout using View Binding.
        binding = ActivityViewBindingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Updating the layout using View Binding.
        /* View Binding will generate the Binding classes for all the layouts (unless ignored using
        tools:viewBindingIgnore = "true" in layout file). It will automatically convert the ids of
        layout to camel case (i.e removes underscore and convert next character to uppercase) */
        binding.tvQuote.text = "In order to write about life first you must live it."

        /* View Binding converts the view to nullable type if the view is not present in one of the
        screen variation layout. For example the view is present in the portrait layout but not in the
        landscape layout. */
        binding.tvAuthor?.text = "Ernest Hemingway"
        Glide.with(this).load("https://picsum.photos/300/200.jpg").into(binding.ivImage)
    }
}