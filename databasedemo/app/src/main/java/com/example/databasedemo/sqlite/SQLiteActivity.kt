package com.example.databasedemo.sqlite

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.databasedemo.R

/*
In Android, there are several ways to store persistent data. SQLite is one way of storing app data.
It is very lightweight and open-source database that comes with Android OS by default. SQLite
supports all the relational database features inserting, manipulating or retrieving persistent data
from database.

Although these APIs are powerful, they are fairly low-level and require a great deal of time and
effort to use. For these reasons it is highly recommended to use Room Persistence Library as an
abstraction layer for accessing information in your app's SQLite databases.
*/
class SQLiteActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var etId: EditText
    private lateinit var etName: EditText
    private lateinit var tvList: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        init()
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btn_add -> addStudent()
            R.id.btn_update -> updateStudent()
            R.id.btn_delete -> deleteStudent()
            R.id.btn_fetch -> fetchStudents()
        }
    }

    private fun init() {
        findViewById<TextView>(R.id.tv_title).text = getString(R.string.student)
        findViewById<Button>(R.id.btn_add).setOnClickListener(this)
        findViewById<Button>(R.id.btn_update).setOnClickListener(this)
        findViewById<Button>(R.id.btn_delete).setOnClickListener(this)
        findViewById<Button>(R.id.btn_fetch).setOnClickListener(this)

        etId = findViewById(R.id.et_id)
        etName = findViewById(R.id.et_name)
        tvList = findViewById(R.id.tv_list)
    }

    private fun addStudent() {
        val studentName = etName.text.toString()

        if (studentName.isNotEmpty()) {
            val newRowId = StudentDatabaseHelper.insertStudent(this, studentName)

            if (newRowId > 0)
                Toast.makeText(
                    this,
                    getString(R.string.record_added, newRowId.toString()),
                    Toast.LENGTH_SHORT
                ).show()
            else
                Toast.makeText(this, R.string.operation_failed, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, R.string.enter_name, Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateStudent() {
        val studentId = etId.text.toString()
        val studentName = etName.text.toString()

        if (studentId.isNotEmpty() && studentName.isNotEmpty()) {
            val rowsAffected =
                StudentDatabaseHelper.updateStudent(this, studentId.toInt(), studentName)

            if (rowsAffected > 0)
                Toast.makeText(
                    this,
                    getString(R.string.record_updated, rowsAffected.toString()),
                    Toast.LENGTH_SHORT
                ).show()
            else
                Toast.makeText(this, R.string.operation_failed, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, R.string.all_fields_are_compulsory, Toast.LENGTH_SHORT).show()
        }
    }

    private fun deleteStudent() {
        val studentId = etId.text.toString()

        if (studentId.isNotEmpty()) {
            val rowsDeleted = StudentDatabaseHelper.deleteStudent(this, studentId.toInt())

            if (rowsDeleted > 0)
                Toast.makeText(
                    this,
                    getString(R.string.record_deleted, rowsDeleted.toString()),
                    Toast.LENGTH_SHORT
                ).show()
            else
                Toast.makeText(this, R.string.operation_failed, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, R.string.enter_id, Toast.LENGTH_SHORT).show()
        }
    }

    private fun fetchStudents() {
        val studentId = etId.text.toString()

        val studentsList = if (studentId.isNotEmpty()) StudentDatabaseHelper.fetchStudents(
            this,
            studentId.toInt()
        ) else StudentDatabaseHelper.fetchStudents(this)

        tvList.text = studentsList ?: ""
    }
}

/*
https://developer.android.com/codelabs/android-room-with-a-view-kotlin#15
https://developer.android.com/training/data-storage/room
https://developer.android.com/training/data-storage/sqlite
*/