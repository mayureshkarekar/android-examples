package com.example.databasedemo.room

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.databasedemo.Application
import com.example.databasedemo.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/*
The Room persistence library provides an abstraction layer over SQLite to allow fluent database
access while harnessing the full power of SQLite. In particular, Room provides the following
benefits:
1. Compile-time verification of SQL queries.
2. Convenience annotations that minimize repetitive and error-prone boilerplate code.
3. Streamlined database migration paths.

Because of these considerations, Android highly recommends that you use Room instead of using the
SQLite APIs directly.


There are three major components in Room:
1. Room database: The database class that holds the database and serves as the main access point for
                  the underlying connection to your app's persisted data.
2. Entity       : Data entities that represent tables in your app's database.
3. DAO          : Data access objects (DAOs) that provide methods that your app can use to query,
                  update, insert, and delete data in the database.

*/
class RoomActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var etId: EditText
    private lateinit var etName: EditText
    private lateinit var tvList: TextView

    /* Creating object of ViewModel using viewModel delegates, passing in an instance of our
    EmployeeViewModelFactory. This is constructed based on the repository retrieved from the
    Application class. */
    private val employeeViewModel: EmployeeViewModel by viewModels {
        EmployeeViewModelFactory((application as Application).employeeRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        init()

        /* Setting an observer to the LiveData shared. The onChanged() method (the default method
        for our Lambda) fires when the observed data changes and the activity is in the foreground. */
        employeeViewModel.allEmployees.observe(this, Observer { employees ->
            employees?.let {
                var employeeList = ""

                it.forEach { employee ->
                    employeeList += "${employee.employeeId} ${employee.employeeName}\n"
                }

                tvList.text = employeeList
            }
        })
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btn_add -> addEmployee()
            R.id.btn_update -> updateEmployee()
            R.id.btn_delete -> deleteEmployee()
            R.id.btn_fetch -> fetchEmployees()
        }
    }

    private fun init() {
        findViewById<TextView>(R.id.tv_title).text = getString(R.string.employee)
        findViewById<Button>(R.id.btn_add).setOnClickListener(this)
        findViewById<Button>(R.id.btn_update).setOnClickListener(this)
        findViewById<Button>(R.id.btn_delete).setOnClickListener(this)
        findViewById<Button>(R.id.btn_fetch).setOnClickListener(this)

        etId = findViewById(R.id.et_id)
        etName = findViewById(R.id.et_name)
        tvList = findViewById(R.id.tv_list)
    }

    private fun addEmployee() {
        val employeeName = etName.text.toString()

        if (employeeName.isNotEmpty()) {
            // Invoking a method on a view model object.
            employeeViewModel.insertEmployee(Employee(employeeName))
            Toast.makeText(this, R.string.record_added, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, R.string.enter_name, Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateEmployee() {
        val employeeId = etId.text.toString()
        val employeeName = etName.text.toString()

        if (employeeId.isNotEmpty() && employeeName.isNotEmpty()) {
            val employee = Employee(employeeName)
            employee.employeeId = employeeId.toInt()

            // Invoking a method on a view model object.
            employeeViewModel.updateEmployee(employee)
            Toast.makeText(this, R.string.record_updated, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, R.string.all_fields_are_compulsory, Toast.LENGTH_SHORT).show()
        }
    }

    private fun deleteEmployee() {
        val employeeId = etId.text.toString()

        if (employeeId.isNotEmpty()) {
            val employee = Employee()
            employee.employeeId = employeeId.toInt()

            // Invoking a method on a view model object.
            employeeViewModel.deleteEmployee(employee)
            Toast.makeText(this, R.string.record_deleted, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, R.string.enter_id, Toast.LENGTH_SHORT).show()
        }
    }

    /* When you don't have access to view model you can use repository/dao and coroutines to access
    the database. */
    private fun fetchEmployees() {
        val employeeId = etId.text.toString()

        if (employeeId.isNotEmpty()) {
            val employeeRepository = (application as Application).employeeRepository

            CoroutineScope(Dispatchers.IO).launch {
                var employeeList = ""

                employeeRepository.fetchEmployee(employeeId.toInt()).forEach { employee ->
                    employeeList += "${employee.employeeId} - ${employee.employeeName}"
                }

                // To perform UI operations we need to use Main dispatcher.
                withContext(Dispatchers.Main) {
                    tvList.text = employeeList
                }
            }
        } else {
            Toast.makeText(this, R.string.enter_id, Toast.LENGTH_SHORT).show()
        }
    }

    /* When you don't have access to view model you can use repository/dao and coroutines to access
    the database. */
    private fun deleteAllEmployees() {
        CoroutineScope(Dispatchers.IO).launch {
            (application as Application).employeeRepository.deleteAllEmployees()
        }

        Toast.makeText(this, R.string.deleted_all, Toast.LENGTH_SHORT).show()
    }
}