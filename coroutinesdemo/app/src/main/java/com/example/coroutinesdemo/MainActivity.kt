/*
---------------------------------------- KOTLIN COROUTINES ----------------------------------------
Coroutines are light-weight threads and the construction of coroutine is very cheap. Like threads,
coroutines can run in parallel, wait and communicate with each other. They do not directly map to
native os threads, because of that they are very faster to create and destroy compared to threads.
There is no additional overhead of switching context between threads. Practically you can have
thousands of or even tens of thousands of coroutines. There might be only one thread having
thousands of coroutines.

The two most important building blocks to create/start/run new coroutines are coroutine scope and
coroutine builders. Coroutine scope consists of all the machinery required to run coroutine, for
example, it knows where (on which thread) to run coroutine and coroutine builders are used to create
a new coroutine.

Features of Kotlin Coroutines:
1. Easy to use         : The syntax for coroutines is simple, easy to use and makes code more
                         readable.
2. Lightweight         : Coroutines are lighter than Threads and supports suspending which allows
                         blocking only current coroutine and let other coroutines in the current
                         thread continue their work.
3. Cancellation support: Cancellation is generated automatically through the running coroutine
                         hierarchy.
4. Fewer memory leaks  : It uses structured concurrency to run operations within a scope which
                         avoids memory leaks.
5. Jetpack integration : Many Jetpack libraries include extensions that provide full coroutines
                         support. Some libraries also provide their own coroutine scope that one can
                          use for structured concurrency.

*/

package com.example.coroutinesdemo

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*
import java.util.*

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        findViewById<Button>(R.id.btn_fake_api_call).setOnClickListener { doFakeApiCall() }
    }

    private fun doFakeApiCall() {
        Toast.makeText(this, getString(R.string.fetching_details), Toast.LENGTH_SHORT).show()

        /* Dispatchers.IO is used create a coroutine that performs IO work such as networking,
        database access. launch is a coroutine builder used to perform the task that do not return a
        value. */
        CoroutineScope(Dispatchers.IO).launch {
            Log.d(TAG, "Calling the fake API.")

            // Blocks the current coroutine but let other coroutines continue their work.
            delay(2000)
            updateDatabase()
        }
    }

    private suspend fun updateDatabase() {
        Log.d(TAG, "Updating the database.")
        delay(2000)
        updateUI()
    }

    private suspend fun updateUI() {
        /* withContext is a another coroutine builder behave similar to async coroutine builder that
        returns a value to its caller. Dispatchers.Main is used to create a coroutine that performs
        UI related work. */
        withContext(Dispatchers.Main) {
            Log.d(TAG, "Updating the UI.")
            findViewById<TextView>(R.id.txt_api_result).text =
                getString(
                    R.string.data_fetched_successfully,
                    Calendar.getInstance(Locale.ENGLISH).timeInMillis.toString()
                )
        }
    }
}