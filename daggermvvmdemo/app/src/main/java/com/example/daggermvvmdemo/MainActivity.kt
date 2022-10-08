package com.example.daggermvvmdemo

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.daggermvvmdemo.viewmodel.MainViewModel
import com.example.daggermvvmdemo.viewmodel.ViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var tvProducts: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvProducts = findViewById(R.id.tv_products)

        (application as FakerApplication).appComponent.inject(this)

        mainViewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        mainViewModel.product.observe(this) {
            tvProducts.text = it.joinToString(separator = "") { product -> "${product.title}\n\n" }
        }
    }
}