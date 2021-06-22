package com.example.networkingdemo.httpurlconnection

import com.example.networkingdemo.MainActivity
import com.example.networkingdemo.MainActivity.Companion.ALL_BANGLORE_STUDENTS_URL
import com.example.networkingdemo.MainActivity.Companion.ALL_DELHI_STUDENTS_URL
import com.example.networkingdemo.MainActivity.Companion.ALL_FEMALE_STUDENTS_URL
import com.example.networkingdemo.MainActivity.Companion.ALL_MALE_STUDENTS_URL
import com.example.networkingdemo.MainActivity.Companion.ALL_MUMBAI_STUDENTS_URL
import com.example.networkingdemo.Student
import com.google.gson.Gson
import org.json.JSONObject
import javax.net.ssl.HttpsURLConnection

object HttpRequestHelper {
    // Making GET request.
    suspend fun getAllStudents(): List<Student>? {
        return fetchStudentsUsingGET(MainActivity.ALL_STUDENTS_URL)
    }

    // Making GET request with query parameter.
    suspend fun getAllMaleStudents(): List<Student>? {
        return fetchStudentsUsingGET(ALL_MALE_STUDENTS_URL)
    }

    // Making GET request with query parameter.
    suspend fun getAllFemaleStudents(): List<Student>? {
        return fetchStudentsUsingGET(ALL_FEMALE_STUDENTS_URL)
    }

    // Making POST request with form data.
    suspend fun getAllDelhiStudents(): List<Student>? {
        val headers = HashMap<String, String>().apply {
            put("Accept", "application/json")
        }
        val data = "city=delhi"

        return fetchStudentsUsingPOST(ALL_DELHI_STUDENTS_URL, headers, data)
    }

    // Making POST request with JSON data.
    suspend fun getAllMumbaiStudents(): List<Student>? {
        val headers = HashMap<String, String>().apply {
            put("Content-Type", "application/json")
            put("Accept", "application/json")
        }
        val data = JSONObject().apply {
            put("city", "Mumbai")
        }.toString()

        return fetchStudentsUsingPOST(ALL_MUMBAI_STUDENTS_URL, headers, data)
    }

    // Making POST request with JSON data.
    suspend fun getAllBangloreStudents(): List<Student>? {
        val headers = HashMap<String, String>().apply {
            put("Content-Type", "application/json")
            put("Accept", "application/json")
        }
        val data = JSONObject().apply {
            put("city", "Banglore")
        }.toString()

        return fetchStudentsUsingPOST(ALL_BANGLORE_STUDENTS_URL, headers, data)
    }

    private suspend fun fetchStudentsUsingGET(
        url: String
    ): List<Student>? {
        // Setting headers for request.
        val httpRequest = HttpRequest(HttpRequestMethod.GET, url).apply {
            headers = HashMap<String, String>().apply {
                put("Content-Type", "application/json")
                put("Accept", "application/json")
            }
        }

        HttpRequestMaker.makeRequest(httpRequest)?.let { httpResponse ->
            if (httpResponse.responseCode == HttpsURLConnection.HTTP_OK) {
                // Processing the response if response is successful.
                httpResponse.response?.let { response ->
                    val studentsList = mutableListOf<Student>()
                    val studentsArray = JSONObject(response).getJSONArray("students")

                    for (i in 0 until studentsArray.length()) {
                        studentsList.add(Student(studentsArray.getJSONObject(i)))
                    }

                    return studentsList
                }
            }
        }

        return null
    }

    private suspend fun fetchStudentsUsingPOST(
        url: String,
        headers: HashMap<String, String>,
        data: String
    ): List<Student>? {
        val httpRequest = HttpRequest(HttpRequestMethod.POST, url).apply {
            this.headers = headers // Setting headers for request.
            this.doOutput = true // Allows to send data to the target.
            this.data = data // Data that is to be send.
        }

        HttpRequestMaker.makeRequest(httpRequest)?.let { httpResponse ->
            if (httpResponse.responseCode == HttpsURLConnection.HTTP_OK) {
                // Processing the response if response is successful.
                httpResponse.response?.let { response ->
                    val studentsList = mutableListOf<Student>()
                    val studentsArray = JSONObject(response).getJSONArray("students")
                    val gson = Gson()

                    for (i in 0 until studentsArray.length()) {
                        // Using GSON to convert JSON to Student object.
                        studentsList.add(
                            gson.fromJson(
                                studentsArray.getJSONObject(i).toString(),
                                Student::class.java
                            )
                        )
                    }

                    return studentsList
                }
            }
        }

        return null
    }
}