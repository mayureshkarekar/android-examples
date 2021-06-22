package com.example.databasedemo.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.databasedemo.room.Employee.Companion.TABLE_NAME

/* The Entity represents a table within the database and has to be annotated with @Enity. Each
Entity consist of a minimum of one field has to define a primary key. */
@Entity(tableName = TABLE_NAME)
data class Employee(
    @ColumnInfo(name = COLUMN_EMPLOYEE_NAME) var employeeName: String? = null
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COLUMN_ID)
    var employeeId: Int = 0

    companion object {
        const val TABLE_NAME = "employee"
        const val COLUMN_ID = "id"
        const val COLUMN_EMPLOYEE_NAME = "employee_name"
    }
}