package com.example.hiltmvvmdemo.utils

import android.util.Log
import javax.inject.Inject

class Randomize @Inject constructor() {
    fun doAction() {
        Log.d(Randomize::class.simpleName, "Performing action.")
    }
}