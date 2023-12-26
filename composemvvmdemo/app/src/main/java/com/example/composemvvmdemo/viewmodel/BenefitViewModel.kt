package com.example.composemvvmdemo.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composemvvmdemo.model.Fruit
import com.example.composemvvmdemo.repository.FruitsyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BenefitViewModel @Inject constructor(
    private val fruitsyRepository: FruitsyRepository, savedStateHandle: SavedStateHandle
) : ViewModel() {
    val benefit: StateFlow<List<Fruit>>
        get() = fruitsyRepository.benefits

    init {
        val fruit = savedStateHandle.get<String>("fruit") ?: "Apple"
        viewModelScope.launch {
            fruitsyRepository.getFruitBenefits(fruit)
        }
    }
}