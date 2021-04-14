package com.example.jobintentservicedemo;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;

/*
 * JobIntentService is a subclass of Service and runs on a background thread. It handle its work one
 * after other (In First Come First Serve manner). In API level 25 and lower its uses IntentService
 * and for API level 26 and above it uses JobScheduler to accomplish it's task. For API level 26 and
 * above it tries to mimic IntentService and tries to complete its task as soon as possible. It runs
 * on a background thread and more likely to be killed or deferred by OS. When killed, the
 * JobIntentService will continue its work later with same intent passed previously. Once its work
 * is done it kills itself automatically.
 *
 * Foreground/Background -> It continues its work until its finished.
 * Terminated            -> It reschedules the work at suitable time.
 */
public class SampleJobIntentService extends JobIntentService {
    private static final String TAG = "SampleJobIntentService";

    public static void enqueueWork(Context context, Intent workIntent) {
        enqueueWork(context, SampleJobIntentService.class, 123, workIntent);
    }

    /*
     * This method will be called once in the lifetime of the service. We can perform initialization
     * work here. For next requests, onHandleWork() will be called directly (unless onDestroy() was
     * called previously).
     */
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
    }

    /*
     * In this method we will specify the work we want to accomplish. Once the service is created,
     * this method will be called directly for next requests (unless onDestroy() was called
     * previously). While performing the work service it automatically acquires the wake lock and
     * release it when the work is done. While performing the work we need to keep checking if
     * isStopped() is called to check if current work is stopped by OS. If isStopped() returns true,
     * it means the current work is stopped and will be started again later with the same intent.
     * NOTE: If isStopped() returns true, we should stop our work.
     */
    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        Log.d(TAG, "onHandleWork");
        String inputText = intent.getStringExtra(MainActivity.KEY_INPUT_TEXT);
        count(inputText);
    }

    /*
     * This method will be called when the current work is stopped by the OS due to low memory, dose
     * mode like conditions. By default this method returns true which indicates the work should
     * start again later with same intent. If we returns false then the service will not start the
     * stopped work again.
     */
    @Override
    public boolean onStopCurrentWork() {
        Log.d(TAG, "onStopCurrentWork");
        return super.onStopCurrentWork();
    }

    /*
     * This method will be called when the service stops itself. We can perform memory cleaning
     * tasks here.
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    private void count(String inputText) {
        if (TextUtils.isEmpty(inputText))
            inputText = "NO INPUT";

        for (int i = 0; i <= 500; i++) {
            if (isStopped()) {
                /*
                 * This method indicates if the current work has stopped by the OS, if returns true
                 * we should exit from method.
                 */
                return;
            }

            Log.d(TAG, inputText + "-" + i);
            SystemClock.sleep(100);
        }
    }
}