package com.example.hiltmvvmdemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hiltmvvmdemo.model.Product
import com.example.hiltmvvmdemo.repository.ProductRepository
import com.example.hiltmvvmdemo.utils.Randomize
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val productRepository: ProductRepository, private val randomize: Randomize
) : ViewModel() {
    val product: LiveData<List<Product>> = productRepository.products

    init {
        viewModelScope.launch {
            productRepository.getProducts()
        }

        randomize.doAction()
    }
}