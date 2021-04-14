package com.example.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.Nullable;

/*
 * Restrictions on background services in Android (in Oreo and above)
 * - Foreground -> Service keeps running until its work is done. We have to call stopSelf()
 *                 explicitly after work is done to stop the service.
 * - Background -> Service keeps running for few seconds (approx 60 seconds) then terminates.
 * - Terminated -> Service terminates as soon as app gets removed from recent tasks.
 */
public class BackgroundService extends Service {
    private final String TAG = BackgroundService.class.getSimpleName();
    private volatile boolean shouldStop = false;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        count();
        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        shouldStop = true;
        Log.d(TAG, "onDestroy");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind");
        // Return null if the service does not supports binding.
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind");
        return super.onUnbind(intent);
    }

    private void count() {
        Runnable countRunnable = () -> {
            shouldStop = false;

            for (int i = 0; i <= 500; i++) {
                if (shouldStop)
                    return;

                Log.d(TAG, "Counter value: " + i);
                SystemClock.sleep(500);
            }

            // Stopping the service after work is done.
            stopSelf();
        };

        Thread countThread = new Thread(countRunnable);
        countThread.start();
    }
}