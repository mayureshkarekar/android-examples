package com.example.navigationcomponentdemo.utils

import androidx.lifecycle.MutableLiveData

object DataHolder {
    var defaultTransferAmount = MutableLiveData(150F)
    var user: User? = null
}

data class User(val username: String)