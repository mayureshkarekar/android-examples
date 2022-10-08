package com.example.daggermvvmdemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.daggermvvmdemo.model.Product
import com.example.daggermvvmdemo.repository.ProductRepository
import com.example.daggermvvmdemo.utils.Randomize
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    private val randomize: Randomize
) : ViewModel() {
    val product: LiveData<List<Product>> = productRepository.products

    init {
        viewModelScope.launch {
            productRepository.getProducts()
        }

        randomize.doAction()
    }
}