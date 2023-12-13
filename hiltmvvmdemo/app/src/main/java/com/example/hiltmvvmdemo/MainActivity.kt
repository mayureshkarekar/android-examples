package com.example.hiltmvvmdemo

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.hiltmvvmdemo.retrofit.FakerAPI
import com.example.hiltmvvmdemo.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel

    private lateinit var tvProducts: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvProducts = findViewById(R.id.tv_products)

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        mainViewModel.product.observe(this) {
            tvProducts.text = it.joinToString(separator = "") { product -> "${product.title}\n\n" }
        }
    }
}