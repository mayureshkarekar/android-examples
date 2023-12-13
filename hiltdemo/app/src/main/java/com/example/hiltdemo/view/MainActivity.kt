/*
------------------------------------------- Hilt -------------------------------------------
Hilt is dependency injection framework developed by Google. It is build on the top of Dagger2.
As Dagger2 does not have any standards to follow for its usage, Hilt came into the picture.

Every Hilt project requires a application class to be annotated with @HiltAndroidApp. If injection
is used in activity or fragment they should be annotated with @AndroidEntryPoint. Fragment's parent
activity must be annotated if fragment used injection irrespective of activity use it or not.

*/

package com.example.hiltdemo.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hiltdemo.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint // As its child fragment uses injection, activity is annotated.
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}