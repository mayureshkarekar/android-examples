package com.example.databindingdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.databindingdemo.databinding.ActivityBindingAdapterBinding

/* Binding Adapters are used to invoke custom code using a custom XML layout attribute. */
class BindingAdapterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBindingAdapterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_binding_adapter)

        val quote = Quote(
            "Eleanor Roosevelt",
            "If life were predictable it would cease to be life, and be without flavor.",
            "https://picsum.photos/300/200.jpg"
        )

        binding.quote = quote
    }
}