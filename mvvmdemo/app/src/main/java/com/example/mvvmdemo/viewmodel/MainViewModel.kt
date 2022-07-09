package com.example.mvvmdemo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.Factory
import androidx.lifecycle.viewModelScope
import com.example.mvvmdemo.repository.CharacterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val characterRepository: CharacterRepository) : ViewModel() {
    val characters = characterRepository.characters

    init {
        viewModelScope.launch(Dispatchers.IO) {
            characterRepository.getCharacters()
        }
    }
}

class MainViewModelFactory(private val characterRepository: CharacterRepository) : Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(characterRepository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}