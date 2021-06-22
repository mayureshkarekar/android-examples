package com.example.databasedemo.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow

/* In Room you use data access objects to access and manage your data. The DAO is the main component
of Room and includes methods that offer access to your apps database it has to be annotated with
@Dao. DAOs are used instead of query builders and let you separate different components of your
database e.g. current data and statistics, which allows you to easily test your database. */
@Dao
interface EmployeeDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertEmployee(employee: Employee)

    @Update
    suspend fun updateEmployee(employee: Employee)

    @Delete
    suspend fun deleteEmployee(employee: Employee)

    @Query("DELETE FROM ${Employee.TABLE_NAME}")
    suspend fun deleteAllEmployees()

    @Query(
        "SELECT * FROM ${Employee.TABLE_NAME} " +
                "WHERE ${Employee.COLUMN_ID} = :employeeId " +
                "ORDER BY ${Employee.COLUMN_ID} ASC"
    )
    suspend fun fetchEmployee(employeeId: Int): List<Employee>

    @Query("SELECT * FROM ${Employee.TABLE_NAME} ORDER BY ${Employee.COLUMN_ID} ASC")
    fun fetchAllEmployees(): Flow<List<Employee>>
}