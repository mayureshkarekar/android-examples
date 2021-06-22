package com.example.databasedemo.room

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

/* A repository class abstracts access to multiple data sources. The repository is not part of the
Architecture Components libraries, but is a suggested best practice for code separation and
architecture. A Repository class provides a clean API for data access to the rest of the application. */

/* Declares the DAO as a private property in the constructor. Pass in the DAO instead of the whole
database, because you only need access to the DAO. */
class EmployeeRepository(private val employeeDao: EmployeeDao) {
    /* Room executes all queries on a separate thread. Observed Flow will notify the observer when
    the data has changed. */
    val allEmployees: Flow<List<Employee>> = employeeDao.fetchAllEmployees()

    /* By default Room runs suspend queries off the main thread, therefore, we don't need to
    implement anything else to ensure we're not doing long running database work off the main thread. */
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertEmployee(employee: Employee) {
        employeeDao.insertEmployee(employee)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateEmployee(employee: Employee) {
        employeeDao.updateEmployee(employee)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteEmployee(employee: Employee) {
        employeeDao.deleteEmployee(employee)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteAllEmployees() {
        employeeDao.deleteAllEmployees()
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun fetchEmployee(employeeId: Int): List<Employee> {
        return employeeDao.fetchEmployee(employeeId)
    }
}