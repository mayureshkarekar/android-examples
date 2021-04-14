package com.example.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.Nullable;

/*
 * Bound service runs until all bound components unbound.
 */
public class BoundService extends Service {
    private final String TAG = BoundService.class.getSimpleName();
    private volatile boolean shouldStop = false;
    private final IBinder iBinder = new Binder();
    private int counter = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
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
        count();
        return iBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind");
        return super.onUnbind(intent);
    }

    public class Binder extends android.os.Binder {
        public BoundService getService() {
            return BoundService.this;
        }
    }

    private void count() {
        Runnable countRunnable = () -> {
            counter = 0;
            shouldStop = false;

            while (counter <= 500) {
                if (shouldStop)
                    return;

                Log.d(TAG, "Counter value: " + counter++);
                SystemClock.sleep(500);
            }

            // Stopping the service after work is done.
            stopSelf();
        };

        Thread countThread = new Thread(countRunnable);
        countThread.start();
    }

    public int getCounter() {
        return counter;
    }
}