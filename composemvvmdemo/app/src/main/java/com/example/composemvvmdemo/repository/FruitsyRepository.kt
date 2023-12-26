package com.example.composemvvmdemo.repository

import com.example.composemvvmdemo.api.FruitsyAPI
import com.example.composemvvmdemo.model.Fruit
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class FruitsyRepository @Inject constructor(private val fruitsyAPI: FruitsyAPI) {
    // region fruits
    private val _fruits = MutableStateFlow<List<String>>(emptyList())
    val fruits: StateFlow<List<String>>
        get() = _fruits

    suspend fun getFruits() {
        val response = fruitsyAPI.getFruits()
        if (response.isSuccessful && response.body() != null) {
            _fruits.emit(response.body()!!)
        }
    }
    // endregion

    // region benefits
    private val _benefits = MutableStateFlow<List<Fruit>>(emptyList())
    val benefits: StateFlow<List<Fruit>>
        get() = _benefits

    suspend fun getFruitBenefits(fruit: String) {
        val response = fruitsyAPI.getFruitBenefits("fruits[?(@.fruit==\"$fruit\")]")
        if (response.isSuccessful && response.body() != null) {
            _benefits.emit(response.body()!!)
        }
    }
    // endregion
}