package com.example.jobschedulerdemo;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Build;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.RequiresApi;

/*
 * Since Android Oreo (API 26) application cannot keep background service running anymore. So if you
 * need to do operations when your app is not in the foreground you can use JobScheduler.
 * JobScheduler runs on main thread while doing its job and is available from API 21 and above. It
 * accepts JobInfo object which specifies the criteria for job. When the criteria declared are met,
 * the system will execute this job on the application's JobService. The framework will be
 * intelligent about when it executes jobs, and attempt to batch and defer them as much as possible.
 * Typically if you don't specify a deadline on a job, it can be run at any moment depending on the
 * current state of the JobScheduler's internal queue. While a job is running, the system holds a
 * wakelock on behalf of your app. For this reason, you do not need to take any action to guarantee
 * that the device stays awake for the duration of the job.
 *
 */

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class SampleJobService extends JobService {
    private static final String TAG = "SampleJobService";
    private volatile boolean isJobCancelled = false;

    /* This method will be called when the job starts. */
    @Override
    public boolean onStartJob(JobParameters params) {
        Log.d(TAG, "onStartJob");

        // Do the background work.
        count(params);

        /* If the job is short and can be executed in the scope of this method, return false. It
        tells system that the job is over. For long running tasks we need to return true, it tells
        system to keep CPU awake so out service can finish the job. It also means that we are
        responsible to telling the system that we are finished when the job completes so that system
        can revoke the wake lock. */
        return true;
    }

    /* This method is called when the job is cancelled before completion. */
    @Override
    public boolean onStopJob(JobParameters params) {
        Log.d(TAG, "onStopJob");

        /* As wake lock is removed when job is cancelled, we must stop our job else the may
        misbehave. */
        isJobCancelled = true;

        /* This method return boolean value which indicates if we want to reschedule the job if it
        got cancelled. If the job is not important then we can return false. */
        return false;
    }

    private void count(JobParameters params) {
        Runnable countRunnable = () -> {
            isJobCancelled = false;

            for (int i = 0; i <= 10; i++) {
                if (isJobCancelled)
                    return;

                Log.d(TAG, "Counter value: " + i);
                SystemClock.sleep(1000);
            }

            /* Stopping the service after work is done. Return false to reschedule the job.
            Reschedule is necessary only when something has failed. */
            jobFinished(params, false);
        };

        Thread countThread = new Thread(countRunnable);
        countThread.start();
    }
}