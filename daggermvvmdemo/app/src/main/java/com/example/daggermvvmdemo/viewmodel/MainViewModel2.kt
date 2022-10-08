package com.example.daggermvvmdemo.viewmodel

import androidx.lifecycle.ViewModel
import com.example.daggermvvmdemo.utils.Randomize
import javax.inject.Inject

class MainViewModel2 @Inject constructor(
    private val randomize: Randomize
) : ViewModel() {
    init {
        randomize.doAction()
    }
}