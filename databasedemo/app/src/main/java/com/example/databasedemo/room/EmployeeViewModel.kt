package com.example.databasedemo.room

import androidx.lifecycle.*
import kotlinx.coroutines.launch

/* The ViewModel's role is to provide data to the UI and survive configuration changes. A ViewModel
acts as a communication center between the Repository and the UI. You can also use a ViewModel to
share data between fragments. The ViewModel is part of the lifecycle library. */
class EmployeeViewModel(private val employeeRepository: EmployeeRepository) : ViewModel() {
    /* Using LiveData and caching what allWords returns has several benefits:
     - We can put an observer on the data (instead of polling for changes) and only update the UI
       when the data actually changes.
     - Repository is completely separated from the UI through the ViewModel. */
    val allEmployees: LiveData<List<Employee>> = employeeRepository.allEmployees.asLiveData()

    // Launching a new coroutine to insert the data in a non-blocking way.
    fun insertEmployee(employee: Employee) = viewModelScope.launch {
        employeeRepository.insertEmployee(employee)
    }

    // Launching a new coroutine to insert the data in a non-blocking way.
    fun updateEmployee(employee: Employee) = viewModelScope.launch {
        employeeRepository.updateEmployee(employee)
    }

    // Launching a new coroutine to insert the data in a non-blocking way.
    fun deleteEmployee(employee: Employee) = viewModelScope.launch {
        employeeRepository.deleteEmployee(employee)
    }
}

class EmployeeViewModelFactory(private val employeeRepository: EmployeeRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EmployeeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EmployeeViewModel(employeeRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class.")
    }
}