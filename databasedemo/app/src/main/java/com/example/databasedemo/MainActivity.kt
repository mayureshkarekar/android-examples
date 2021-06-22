package com.example.databasedemo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.databasedemo.room.RoomActivity
import com.example.databasedemo.sqlite.SQLiteActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        findViewById<Button>(R.id.btn_sqlite).setOnClickListener {
            startActivity(Intent(this, SQLiteActivity::class.java))
        }

        findViewById<Button>(R.id.btn_room).setOnClickListener {
            startActivity(Intent(this, RoomActivity::class.java))
        }
    }
}