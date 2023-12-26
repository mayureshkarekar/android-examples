package com.example.composemvvmdemo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composemvvmdemo.repository.FruitsyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FruitViewModel @Inject constructor(private val fruitsyRepository: FruitsyRepository) :
    ViewModel() {
    val fruits: StateFlow<List<String>>
        get() = fruitsyRepository.fruits

    init {
        viewModelScope.launch {
            fruitsyRepository.getFruits()
        }
    }
}