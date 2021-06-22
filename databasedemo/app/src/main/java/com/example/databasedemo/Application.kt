package com.example.databasedemo

import android.app.Application
import com.example.databasedemo.room.EmployeeDatabase
import com.example.databasedemo.room.EmployeeRepository

class Application : Application() {
    private val employeeDatabase by lazy { EmployeeDatabase.getDatabase(this) }
    val employeeRepository by lazy { EmployeeRepository(employeeDatabase.employeeDao()) }
}