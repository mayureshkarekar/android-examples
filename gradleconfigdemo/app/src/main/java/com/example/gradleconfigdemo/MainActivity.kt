package com.example.gradleconfigdemo

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.btn_base_url).setOnClickListener {
            Toast.makeText(
                this,
                "Base URL: ${BuildConfig.BASE_URL}",
                Toast.LENGTH_SHORT
            ).show()

            Toast.makeText(
                this,
                "Server name: ${getString(R.string.server_name)}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}