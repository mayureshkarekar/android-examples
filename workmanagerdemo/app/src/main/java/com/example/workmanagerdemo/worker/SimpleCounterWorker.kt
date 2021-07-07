package com.example.workmanagerdemo.worker

import android.content.Context
import android.os.SystemClock
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters

private const val TAG = "SimpleCounterWorker"

class SimpleCounterWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    // Runs asynchronously on a background thread.
    override fun doWork(): Result {
        val input = inputData.getString("sample_input")

        Log.d(TAG, "Performing $input work.")

        for (i in 1..100) {
            Log.d(TAG, "Work done: $i%")
            SystemClock.sleep(250)
        }

        Log.d(TAG, "The $input completed.")

        // Returning optional output.
        val output =
            Data.Builder().putString("sample_output", "This is sample output from $TAG.").build()

        /* Indicates work is completed successfully.
           Result.success(): The work finished successfully.
           Result.failure(): The work failed.
           Result.retry()  : The work failed and should be tried at another time according to its
                             retry policy. */
        return Result.success(output)
    }
}