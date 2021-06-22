package com.example.networkingdemo

import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.networkingdemo.httpurlconnection.HttpRequestHelper
import com.example.networkingdemo.retrofit.City
import com.example.networkingdemo.retrofit.RetrofitHelper
import com.example.networkingdemo.retrofit.StudentResponse
import com.example.networkingdemo.volley.VolleyHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    companion object {
        const val BASE_URL = "https://859d70be-6004-48f4-b8a1-940b4d07ba93.mock.pstmn.io/"
        const val ALL_STUDENTS_URL = "${BASE_URL}school/students"
        const val ALL_MALE_STUDENTS_URL = "${ALL_STUDENTS_URL}?gender=male"
        const val ALL_FEMALE_STUDENTS_URL = "${ALL_STUDENTS_URL}?gender=female"
        const val ALL_DELHI_STUDENTS_URL = "${ALL_STUDENTS_URL}/delhi"
        const val ALL_MUMBAI_STUDENTS_URL = "${ALL_STUDENTS_URL}/mumbai"
        const val ALL_BANGLORE_STUDENTS_URL = "${ALL_STUDENTS_URL}/banglore"
    }

    private val HTTP_URL_CONNECTION = 0
    private val VOLLEY = 1
    private val RETROFIT = 2
    private var networkingApi = HTTP_URL_CONNECTION

    private lateinit var tvStudentList: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        tvStudentList = findViewById(R.id.tv_students_list)

        findViewById<RadioGroup>(R.id.rg_networking_api).setOnCheckedChangeListener { _, checkedId ->
            networkingApi = when (checkedId) {
                R.id.rd_http_url_connection -> HTTP_URL_CONNECTION
                R.id.rd_volley -> VOLLEY
                R.id.rd_retrofit -> RETROFIT
                else -> HTTP_URL_CONNECTION
            }
        }

        findViewById<Button>(R.id.btn_all_students).setOnClickListener {
            when (networkingApi) {
                HTTP_URL_CONNECTION -> fetchAllStudentsUsingHttpUrlConnection()
                VOLLEY -> fetchAllStudentsUsingVolley()
                RETROFIT -> fetchAllStudentsUsingRetrofit()
            }
        }

        findViewById<Button>(R.id.btn_male_students).setOnClickListener {
            when (networkingApi) {
                HTTP_URL_CONNECTION -> fetchMaleStudentsUsingHttpUrlConnection()
                VOLLEY -> fetchMaleStudentsUsingVolley()
                RETROFIT -> fetchMaleStudentsUsingRetrofit()
            }
        }

        findViewById<Button>(R.id.btn_female_students).setOnClickListener {
            when (networkingApi) {
                HTTP_URL_CONNECTION -> fetchFemaleStudentsUsingHttpUrlConnection()
                VOLLEY -> fetchFemaleStudentsUsingVolley()
                RETROFIT -> fetchFemaleStudentsUsingRetrofit()
            }
        }

        findViewById<Button>(R.id.btn_delhi_students).setOnClickListener {
            when (networkingApi) {
                HTTP_URL_CONNECTION -> fetchDelhiStudentsUsingHttpUrlConnection()
                VOLLEY -> fetchDelhiStudentsUsingVolley()
                RETROFIT -> fetchDelhiStudentsUsingRetrofit()
            }
        }

        findViewById<Button>(R.id.btn_mumbai_students).setOnClickListener {
            when (networkingApi) {
                HTTP_URL_CONNECTION -> fetchMumbaiStudentsUsingHttpUrlConnection()
                VOLLEY -> fetchMumbaiStudentsUsingVolley()
                RETROFIT -> fetchMumbaiStudentsUsingRetrofit()
            }
        }

        findViewById<Button>(R.id.btn_banglore_students).setOnClickListener {
            when (networkingApi) {
                HTTP_URL_CONNECTION -> fetchBangloreStudentsUsingHttpUrlConnection()
                VOLLEY -> fetchBangloreStudentsUsingVolley()
                RETROFIT -> fetchBangloreStudentsUsingRetrofit()
            }
        }
    }

    private fun fetchAllStudentsUsingHttpUrlConnection() {
        CoroutineScope(Dispatchers.IO).launch {
            HttpRequestHelper.getAllStudents()?.let { studentsList ->
                val students = StringBuilder("---------- HttpUrlConnection ----------\n")

                studentsList.forEach { student ->
                    students.append("${student.rollNo}. ${student.firstName} ${student.lastName}\n")
                }

                updateStudentList(
                    if (students.toString() == "---------- HttpUrlConnection ----------\n") students.append(
                        getString(R.string.no_students)
                    ).toString() else students.toString()
                )
            } ?: updateStudentList(getString(R.string.operation_failed))
        }
    }

    private fun fetchAllStudentsUsingVolley() {
        VolleyHelper.getInstance(this@MainActivity).getAllStudents(
            { response ->
                val studentsArray = response.getJSONArray("students")
                val studentsList = StringBuilder("---------- Volley ----------\n")

                for (i in 0 until studentsArray.length()) {
                    val student = Student(studentsArray.getJSONObject(i))
                    studentsList.append("${student.rollNo}. ${student.firstName} ${student.lastName}\n")
                }

                tvStudentList.text =
                    if (studentsList.toString() == "---------- Volley ----------\n") studentsList.append(
                        getString(R.string.no_students)
                    ) else studentsList.toString()
            },
            { tvStudentList.text = getString(R.string.operation_failed) }
        )
    }

    private fun fetchAllStudentsUsingRetrofit() {
        val allStudentsRequest = RetrofitHelper.studentAPI.getAllStudents()
        allStudentsRequest.enqueue(object : Callback<StudentResponse> {
            override fun onResponse(
                call: Call<StudentResponse>,
                response: Response<StudentResponse>
            ) {
                if (response.isSuccessful) {
                    val studentsResponse = response.body()
                    studentsResponse?.let {
                        val studentsList =
                            java.lang.StringBuilder("---------- Retrofit ----------\n")
                        studentsResponse.students.forEach { student ->
                            studentsList.append("${student.rollNo}. ${student.firstName} ${student.lastName}\n")
                        }

                        tvStudentList.text =
                            if (studentsList.toString() == "---------- Retrofit ----------\n") studentsList.append(
                                getString(R.string.no_students)
                            ) else studentsList.toString()
                    }
                }
            }

            override fun onFailure(call: Call<StudentResponse>, t: Throwable) {
                tvStudentList.text = getString(R.string.operation_failed)
            }
        })
    }

    private fun fetchMaleStudentsUsingHttpUrlConnection() {
        CoroutineScope(Dispatchers.IO).launch {
            HttpRequestHelper.getAllMaleStudents()?.let { studentsList ->
                val students = StringBuilder("---------- HttpUrlConnection ----------\n")

                studentsList.forEach { student ->
                    students.append("${student.rollNo}. ${student.firstName} ${student.lastName}\n")
                }

                updateStudentList(
                    if (students.toString() == "---------- HttpUrlConnection ----------\n") students.append(
                        getString(R.string.no_students)
                    ).toString() else students.toString()
                )
            } ?: updateStudentList(getString(R.string.operation_failed))
        }
    }

    private fun fetchMaleStudentsUsingVolley() {
        VolleyHelper.getInstance(this@MainActivity).getAllMaleStudentsStudents(
            { response ->
                val studentsArray = response.getJSONArray("students")
                val studentsList = StringBuilder("---------- Volley ----------\n")

                for (i in 0 until studentsArray.length()) {
                    val student = Student(studentsArray.getJSONObject(i))
                    studentsList.append("${student.rollNo}. ${student.firstName} ${student.lastName}\n")
                }

                tvStudentList.text =
                    if (studentsList.toString() == "---------- Volley ----------\n") studentsList.append(
                        getString(R.string.no_students)
                    ) else studentsList.toString()
            },
            { tvStudentList.text = getString(R.string.operation_failed) }
        )
    }

    private fun fetchMaleStudentsUsingRetrofit() {
        val allMaleStudentsRequest = RetrofitHelper.studentAPI.getAllStudentsByGender("male")
        allMaleStudentsRequest.enqueue(object : Callback<StudentResponse> {
            override fun onResponse(
                call: Call<StudentResponse>,
                response: Response<StudentResponse>
            ) {
                if (response.isSuccessful) {
                    val studentsResponse = response.body()
                    studentsResponse?.let {
                        val studentsList =
                            java.lang.StringBuilder("---------- Retrofit ----------\n")
                        studentsResponse.students.forEach { student ->
                            studentsList.append("${student.rollNo}. ${student.firstName} ${student.lastName}\n")
                        }

                        tvStudentList.text =
                            if (studentsList.toString() == "---------- Retrofit ----------\n") studentsList.append(
                                getString(R.string.no_students)
                            ) else studentsList.toString()
                    }
                }
            }

            override fun onFailure(call: Call<StudentResponse>, t: Throwable) {
                tvStudentList.text = getString(R.string.operation_failed)
            }
        })
    }

    private fun fetchFemaleStudentsUsingHttpUrlConnection() {
        CoroutineScope(Dispatchers.IO).launch {
            HttpRequestHelper.getAllFemaleStudents()?.let { studentsList ->
                val students = StringBuilder("---------- HttpUrlConnection ----------\n")

                studentsList.forEach { student ->
                    students.append("${student.rollNo}. ${student.firstName} ${student.lastName}\n")
                }

                updateStudentList(
                    if (students.toString() == "---------- HttpUrlConnection ----------\n") students.append(
                        getString(R.string.no_students)
                    ).toString() else students.toString()
                )
            } ?: updateStudentList(getString(R.string.operation_failed))
        }
    }

    private fun fetchFemaleStudentsUsingVolley() {
        VolleyHelper.getInstance(this@MainActivity).getAllFemaleStudentsStudents(
            { response ->
                val studentsArray = response.getJSONArray("students")
                val studentsList = StringBuilder("---------- Volley ----------\n")

                for (i in 0 until studentsArray.length()) {
                    val student = Student(studentsArray.getJSONObject(i))
                    studentsList.append("${student.rollNo}. ${student.firstName} ${student.lastName}\n")
                }

                tvStudentList.text =
                    if (studentsList.toString() == "---------- Volley ----------\n") studentsList.append(
                        getString(R.string.no_students)
                    ) else studentsList.toString()
            },
            { tvStudentList.text = getString(R.string.operation_failed) }
        )
    }

    private fun fetchFemaleStudentsUsingRetrofit() {
        val allFemaleStudentsRequest = RetrofitHelper.studentAPI.getAllStudentsByGender("female")
        allFemaleStudentsRequest.enqueue(object : Callback<StudentResponse> {
            override fun onResponse(
                call: Call<StudentResponse>,
                response: Response<StudentResponse>
            ) {
                if (response.isSuccessful) {
                    val studentsResponse = response.body()
                    studentsResponse?.let {
                        val studentsList =
                            java.lang.StringBuilder("---------- Retrofit ----------\n")
                        studentsResponse.students.forEach { student ->
                            studentsList.append("${student.rollNo}. ${student.firstName} ${student.lastName}\n")
                        }

                        tvStudentList.text =
                            if (studentsList.toString() == "---------- Retrofit ----------\n") studentsList.append(
                                getString(R.string.no_students)
                            ) else studentsList.toString()
                    }
                }
            }

            override fun onFailure(call: Call<StudentResponse>, t: Throwable) {
                tvStudentList.text = getString(R.string.operation_failed)
            }
        })
    }

    private fun fetchDelhiStudentsUsingHttpUrlConnection() {
        CoroutineScope(Dispatchers.IO).launch {
            HttpRequestHelper.getAllDelhiStudents()?.let { studentsList ->
                val students = StringBuilder("---------- HttpUrlConnection ----------\n")

                studentsList.forEach { student ->
                    students.append("${student.rollNo}. ${student.firstName} ${student.lastName}\n")
                }

                updateStudentList(
                    if (students.toString() == "---------- HttpUrlConnection ----------\n") students.append(
                        getString(R.string.no_students)
                    ).toString() else students.toString()
                )
            } ?: updateStudentList(getString(R.string.operation_failed))
        }
    }

    private fun fetchDelhiStudentsUsingVolley() {
        VolleyHelper.getInstance(this@MainActivity).getAllDelhiStudents(
            { response ->
                val studentsArray = JSONObject(response).getJSONArray("students")
                val studentsList = StringBuilder("---------- Volley ----------\n")

                for (i in 0 until studentsArray.length()) {
                    val student = Student(studentsArray.getJSONObject(i))
                    studentsList.append("${student.rollNo}. ${student.firstName} ${student.lastName}\n")
                }

                tvStudentList.text =
                    if (studentsList.toString() == "---------- Volley ----------\n") studentsList.append(
                        getString(R.string.no_students)
                    ) else studentsList.toString()
            },
            { tvStudentList.text = getString(R.string.operation_failed) }
        )
    }

    private fun fetchDelhiStudentsUsingRetrofit() {
        val allDelhiStudentsRequest = RetrofitHelper.studentAPI.getAllDelhiStudents(
            RequestBody.create(MediaType.parse("text/plain"), "Delhi")
        )
        allDelhiStudentsRequest.enqueue(object : Callback<StudentResponse> {
            override fun onResponse(
                call: Call<StudentResponse>,
                response: Response<StudentResponse>
            ) {
                if (response.isSuccessful) {
                    val studentsResponse = response.body()
                    studentsResponse?.let {
                        val studentsList =
                            java.lang.StringBuilder("---------- Retrofit ----------\n")
                        studentsResponse.students.forEach { student ->
                            studentsList.append("${student.rollNo}. ${student.firstName} ${student.lastName}\n")
                        }

                        tvStudentList.text =
                            if (studentsList.toString() == "---------- Retrofit ----------\n") studentsList.append(
                                getString(R.string.no_students)
                            ) else studentsList.toString()
                    }
                }
            }

            override fun onFailure(call: Call<StudentResponse>, t: Throwable) {
                tvStudentList.text = getString(R.string.operation_failed)
            }
        })
    }

    private fun fetchMumbaiStudentsUsingHttpUrlConnection() {
        CoroutineScope(Dispatchers.IO).launch {
            HttpRequestHelper.getAllMumbaiStudents()?.let { studentsList ->
                val students = StringBuilder("---------- HttpUrlConnection ----------\n")

                studentsList.forEach { student ->
                    students.append("${student.rollNo}. ${student.firstName} ${student.lastName}\n")
                }

                updateStudentList(
                    if (students.toString() == "---------- HttpUrlConnection ----------\n") students.append(
                        getString(R.string.no_students)
                    ).toString() else students.toString()
                )
            } ?: updateStudentList(getString(R.string.operation_failed))
        }
    }

    private fun fetchMumbaiStudentsUsingVolley() {
        VolleyHelper.getInstance(this@MainActivity).getAllMumbaiStudentsStudents(
            { response ->
                val studentsArray = response.getJSONArray("students")
                val studentsList = StringBuilder("---------- Volley ----------\n")

                for (i in 0 until studentsArray.length()) {
                    val student = Student(studentsArray.getJSONObject(i))
                    studentsList.append("${student.rollNo}. ${student.firstName} ${student.lastName}\n")
                }

                tvStudentList.text =
                    if (studentsList.toString() == "---------- Volley ----------\n") studentsList.append(
                        getString(R.string.no_students)
                    ) else studentsList.toString()
            },
            { tvStudentList.text = getString(R.string.operation_failed) }
        )
    }

    private fun fetchMumbaiStudentsUsingRetrofit() {
        val allMumbaiStudentsRequest = RetrofitHelper.studentAPI.getAllStudentsByCity(
            "mumbai",
            City("Mumbai")
        )
        allMumbaiStudentsRequest.enqueue(object : Callback<StudentResponse> {
            override fun onResponse(
                call: Call<StudentResponse>,
                response: Response<StudentResponse>
            ) {
                if (response.isSuccessful) {
                    val studentsResponse = response.body()
                    studentsResponse?.let {
                        val studentsList =
                            java.lang.StringBuilder("---------- Retrofit ----------\n")
                        studentsResponse.students.forEach { student ->
                            studentsList.append("${student.rollNo}. ${student.firstName} ${student.lastName}\n")
                        }

                        tvStudentList.text =
                            if (studentsList.toString() == "---------- Retrofit ----------\n") studentsList.append(
                                getString(R.string.no_students)
                            ) else studentsList.toString()
                    }
                }
            }

            override fun onFailure(call: Call<StudentResponse>, t: Throwable) {
                tvStudentList.text = getString(R.string.operation_failed)
            }
        })
    }

    private fun fetchBangloreStudentsUsingHttpUrlConnection() {
        CoroutineScope(Dispatchers.IO).launch {
            HttpRequestHelper.getAllBangloreStudents()?.let { studentsList ->
                val students = StringBuilder("---------- HttpUrlConnection ----------\n")

                studentsList.forEach { student ->
                    students.append("${student.rollNo}. ${student.firstName} ${student.lastName}\n")
                }

                updateStudentList(
                    if (students.toString() == "---------- HttpUrlConnection ----------\n") students.append(
                        getString(R.string.no_students)
                    ).toString() else students.toString()
                )
            } ?: updateStudentList(getString(R.string.operation_failed))
        }
    }

    private fun fetchBangloreStudentsUsingVolley() {
        VolleyHelper.getInstance(this@MainActivity).getAllBangloreStudentsStudents(
            { response ->
                val studentsArray = response.getJSONArray("students")
                val studentsList = StringBuilder("---------- Volley ----------\n")

                for (i in 0 until studentsArray.length()) {
                    val student = Student(studentsArray.getJSONObject(i))
                    studentsList.append("${student.rollNo}. ${student.firstName} ${student.lastName}\n")
                }

                tvStudentList.text =
                    if (studentsList.toString() == "---------- Volley ----------\n") studentsList.append(
                        getString(R.string.no_students)
                    ) else studentsList.toString()
            },
            { tvStudentList.text = getString(R.string.operation_failed) }
        )
    }

    private fun fetchBangloreStudentsUsingRetrofit() {
        val allBangloreStudentsRequest = RetrofitHelper.studentAPI.getAllStudentsByCity(
            "banglore",
            City("Banglore")
        )
        allBangloreStudentsRequest.enqueue(object : Callback<StudentResponse> {
            override fun onResponse(
                call: Call<StudentResponse>,
                response: Response<StudentResponse>
            ) {
                if (response.isSuccessful) {
                    val studentsResponse = response.body()
                    studentsResponse?.let {
                        val studentsList =
                            java.lang.StringBuilder("---------- Retrofit ----------\n")
                        studentsResponse.students.forEach { student ->
                            studentsList.append("${student.rollNo}. ${student.firstName} ${student.lastName}\n")
                        }

                        tvStudentList.text =
                            if (studentsList.toString() == "---------- Retrofit ----------\n") studentsList.append(
                                getString(R.string.no_students)
                            ) else studentsList.toString()
                    }
                } else {
                    tvStudentList.text = getString(R.string.operation_failed)
                }
            }

            override fun onFailure(call: Call<StudentResponse>, t: Throwable) {
                tvStudentList.text = getString(R.string.operation_failed)
            }
        })
    }

    private suspend fun updateStudentList(students: String) {
        withContext(Dispatchers.Main) {
            tvStudentList.text = students
        }
    }
}