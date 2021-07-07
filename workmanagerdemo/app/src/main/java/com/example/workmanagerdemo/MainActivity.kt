package com.example.workmanagerdemo

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.work.*
import com.example.workmanagerdemo.worker.*
import java.util.concurrent.TimeUnit

/*
------------------------------------------- WorkManager -------------------------------------------
WorkManager is an API that makes it easy to schedule reliable, asynchronous tasks that are expected
to run even if the app exits or the device restarts. The WorkManager API is a suitable and
recommended replacement for all previous Android background scheduling APIs, including
FirebaseJobDispatcher, GcmNetworkManager, and Job Scheduler.

When the work is enqueued, the OS tries to perform the submitted work as soon as the suitable time
comes (and constraints are meet if any). If the doWork() execution starts when the app is in the
foreground/background, the OS continues the work till it completes. If the app terminated in between,
the os cancels the work and starts it later.

 */

class MainActivity : AppCompatActivity() {
    private lateinit var tvOutput: TextView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvOutput = findViewById(R.id.tv_output)

        // Input data to worker.
        val oneTimeRequestInputData =
            Data.Builder().putString("sample_input", "one time").build()
        val periodicRequestInputData =
            Data.Builder().putString("sample_input", "periodic").build()
        val chainedRequestInputData =
            Data.Builder().putString("sample_input", "chained").build()


        // Setting the constraints for work.
        val workConstraints = Constraints.Builder()
            .setRequiresBatteryNotLow(true) // Work requires battery not low.
            .setRequiredNetworkType(NetworkType.NOT_REQUIRED) // Network is not required.
            .build()


        // Creating work request.
        val oneTimeEvenCounterWorkRequest = OneTimeWorkRequestBuilder<EvenCounterWorker>()
            .setInputData(oneTimeRequestInputData) // Setting optional input.
            .setConstraints(workConstraints) // Setting optional constraints.
            .build()
        val oneTimeOddCounterWorkRequest = OneTimeWorkRequestBuilder<OddCounterWorker>()
            .setInputData(oneTimeRequestInputData) // Setting optional input.
            .setConstraints(workConstraints) // Setting optional constraints.
            .build()
        val oneTimeMultiplicationTableOfFourWorkRequest =
            OneTimeWorkRequestBuilder<MultiplicationTableOfFourWorker>()
                .setInputData(oneTimeRequestInputData) // Setting optional input.
                .setConstraints(workConstraints) // Setting optional constraints.
                .build()
        val oneTimeMultiplicationTableOfFiveWorkRequest =
            OneTimeWorkRequestBuilder<MultiplicationTableOfFiveWorker>()
                .setInputData(oneTimeRequestInputData) // Setting optional input.
                .setConstraints(workConstraints) // Setting optional constraints.
                .build()
        val periodicSimpleCounterWorkRequest =
            PeriodicWorkRequestBuilder<SimpleCounterWorker>(
                // Will run in interval of every fifteen minutes minimum.
                15,
                TimeUnit.MINUTES
            )
                .setInputData(periodicRequestInputData) // Setting optional input.
                .setConstraints(workConstraints) // Setting optional constraints.
                .build()


        // Setting optional observer to capture worker status.
        WorkManager.getInstance(this).getWorkInfoByIdLiveData(oneTimeEvenCounterWorkRequest.id)
            .observe(this, {
                if (it.state.isFinished) {
                    tvOutput.text =
                        "OneTimeWorker - ${it.state.name} - ${it.outputData.getString("sample_output")}"
                } else {
                    tvOutput.text = "OneTimeWorker - ${it.state.name}"
                }
            })
        WorkManager.getInstance(this).getWorkInfoByIdLiveData(periodicSimpleCounterWorkRequest.id)
            .observe(this, {
                if (it.state.isFinished) {
                    tvOutput.text =
                        "PeriodicWorker - ${it.state.name} - ${it.outputData.getString("sample_output")}"
                } else {
                    tvOutput.text = "PeriodicWorker - ${it.state.name}"
                }
            })


        findViewById<Button>(R.id.btn_start_one_time_work).setOnClickListener {
            // Enqueuing the work request to singleton instance of WorkManager.
            WorkManager.getInstance(this).enqueue(oneTimeEvenCounterWorkRequest)
            Toast.makeText(this, R.string.request_enqueued, Toast.LENGTH_SHORT).show()
        }
        findViewById<Button>(R.id.btn_start_periodic_work).setOnClickListener {
            // Enqueuing the work request to singleton instance of WorkManager.
            WorkManager.getInstance(this).enqueue(periodicSimpleCounterWorkRequest)
            Toast.makeText(this, R.string.request_enqueued, Toast.LENGTH_SHORT).show()
        }
        findViewById<Button>(R.id.btn_start_chained_work).setOnClickListener {
            // Enqueuing the work request to singleton instance of WorkManager.
            WorkManager.getInstance(this)
                // Candidates to run in parallel.
                .beginWith(listOf(oneTimeEvenCounterWorkRequest, oneTimeOddCounterWorkRequest))
                // Dependent work (only runs after all previous work in chain).
                .then(oneTimeMultiplicationTableOfFourWorkRequest)
                .then(oneTimeMultiplicationTableOfFiveWorkRequest)
                // Call enqueue to kick things off.
                .enqueue()

            Toast.makeText(this, R.string.request_enqueued, Toast.LENGTH_SHORT).show()
        }
        findViewById<Button>(R.id.btn_cancel_one_time_work).setOnClickListener {
            /* Cancelling the work by id.
            NOTE: If the work is already finished, nothing happens. Otherwise, the work's state
            is changed to CANCELLED and the work will not run in the future. Any WorkRequest jobs
            that are dependent on this work will also be CANCELLED. */
            WorkManager.getInstance(this).cancelWorkById(oneTimeEvenCounterWorkRequest.id)
            Toast.makeText(this, R.string.work_cancelled, Toast.LENGTH_SHORT).show()
        }
        findViewById<Button>(R.id.btn_cancel_periodic_work).setOnClickListener {
            /* Cancelling the work by id.
            NOTE: If the work is already finished, nothing happens. Otherwise, the work's state
            is changed to CANCELLED and the work will not run in the future. Any WorkRequest jobs
            that are dependent on this work will also be CANCELLED. */
            WorkManager.getInstance(this).cancelWorkById(periodicSimpleCounterWorkRequest.id)
            Toast.makeText(this, R.string.work_cancelled, Toast.LENGTH_SHORT).show()
        }
        findViewById<Button>(R.id.btn_cancel_chained_work).setOnClickListener {
            /* Cancelling the work by id.
            NOTE: If the work is already finished, nothing happens. Otherwise, the work's state
            is changed to CANCELLED and the work will not run in the future. Any WorkRequest jobs
            that are dependent on this work will also be CANCELLED. */
            WorkManager.getInstance(this).cancelWorkById(oneTimeOddCounterWorkRequest.id)
            Toast.makeText(this, R.string.work_cancelled, Toast.LENGTH_SHORT).show()
        }
    }
}