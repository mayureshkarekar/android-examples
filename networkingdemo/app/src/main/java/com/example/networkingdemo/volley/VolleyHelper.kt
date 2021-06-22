package com.example.networkingdemo.volley

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.networkingdemo.MainActivity
import org.json.JSONObject

class VolleyHelper private constructor(context: Context) {
    companion object {
        @Volatile
        private var INSTANCE: VolleyHelper? = null

        fun getInstance(context: Context) =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: VolleyHelper(context).also { INSTANCE = it }
            }
    }

    private val requestQueue: RequestQueue by lazy {
        Volley.newRequestQueue(context.applicationContext)
    }

    private fun <T> addToRequestQueue(request: Request<T>) = requestQueue.add(request)

    fun cancelAll(tag: String) = requestQueue.cancelAll(tag)

    // Making GET request.
    fun getAllStudents(
        responseListener: Response.Listener<JSONObject>,
        errorListener: Response.ErrorListener
    ) {
        fetchStudentsUsingGET(MainActivity.ALL_STUDENTS_URL, responseListener, errorListener)
    }

    // Making GET request with query parameter.
    fun getAllMaleStudentsStudents(
        responseListener: Response.Listener<JSONObject>,
        errorListener: Response.ErrorListener
    ) {
        fetchStudentsUsingGET(MainActivity.ALL_MALE_STUDENTS_URL, responseListener, errorListener)
    }

    // Making GET request with query parameter.
    fun getAllFemaleStudentsStudents(
        responseListener: Response.Listener<JSONObject>,
        errorListener: Response.ErrorListener
    ) {
        fetchStudentsUsingGET(MainActivity.ALL_FEMALE_STUDENTS_URL, responseListener, errorListener)
    }

    // Making POST request with form data.
    fun getAllDelhiStudents(
        responseListener: Response.Listener<String>,
        errorListener: Response.ErrorListener
    ) {
        val studentsRequest = object : StringRequest(
            Method.POST,
            MainActivity.ALL_DELHI_STUDENTS_URL,
            responseListener,
            errorListener
        ) {
            override fun getHeaders(): MutableMap<String, String> {
                return HashMap<String, String>().apply {
                    put("Accept", "application/json")
                }
            }

            override fun getParams(): MutableMap<String, String> {
                return HashMap<String, String>().apply {
                    put("city", "Delhi")
                }
            }
        }

        cancelAll(MainActivity.ALL_DELHI_STUDENTS_URL)
        addToRequestQueue(studentsRequest)
    }

    // Making POST request with JSON data.
    fun getAllMumbaiStudentsStudents(
        responseListener: Response.Listener<JSONObject>,
        errorListener: Response.ErrorListener
    ) {
        val data = JSONObject().apply {
            put("city", "Mumbai")
        }

        fetchStudentsUsingPOST(
            MainActivity.ALL_MUMBAI_STUDENTS_URL,
            data,
            responseListener,
            errorListener
        )
    }

    // Making POST request with JSON data.
    fun getAllBangloreStudentsStudents(
        responseListener: Response.Listener<JSONObject>,
        errorListener: Response.ErrorListener
    ) {
        val data = JSONObject().apply {
            put("city", "Banglore")
        }

        fetchStudentsUsingPOST(
            MainActivity.ALL_BANGLORE_STUDENTS_URL,
            data,
            responseListener,
            errorListener
        )
    }

    private fun fetchStudentsUsingGET(
        url: String, responseListener: Response.Listener<JSONObject>,
        errorListener: Response.ErrorListener
    ) {
        val studentsRequest = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            responseListener,
            errorListener
        ).setTag(url)

        cancelAll(url)
        addToRequestQueue(studentsRequest)
    }

    private fun fetchStudentsUsingPOST(
        url: String,
        data: JSONObject,
        responseListener: Response.Listener<JSONObject>,
        errorListener: Response.ErrorListener
    ) {
        val studentsRequest = JsonObjectRequest(
            Request.Method.POST,
            url,
            data,
            responseListener,
            errorListener
        ).setTag(url)

        cancelAll(url)
        addToRequestQueue(studentsRequest)
    }
}