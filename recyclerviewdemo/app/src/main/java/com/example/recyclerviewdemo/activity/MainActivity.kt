package com.example.recyclerviewdemo.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.recyclerviewdemo.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnVerticalList.setOnClickListener {
            startActivity(Intent(this, VerticalListActivity::class.java))
        }

        btnGridList.setOnClickListener {
            startActivity(Intent(this, GridListActivity::class.java))
        }
    }
}