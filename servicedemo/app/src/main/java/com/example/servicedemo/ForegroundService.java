package com.example.servicedemo;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

/*
 * Foreground services has no restrictions, it runs until stopService() or stopSelf() is called.
 */

public class ForegroundService extends Service {
    private final String TAG = ForegroundService.class.getSimpleName();
    private volatile boolean shouldStop = false;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        // Generating the notification to indicate foreground service is running. If we do not show
        // notification then OS will automatically show a notification.
        Notification foregroundServiceNotification = generateForegroundServiceNotification();

        // This method should be called within 5 seconds of calling startForeground() method.
        startForeground(1, foregroundServiceNotification);
        count();
        return Service.START_NOT_STICKY;
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

    private Notification generateForegroundServiceNotification() {
        Intent mainActivityIntent = new Intent(this, MainActivity.class);
        PendingIntent mainActivityPendingIntent = PendingIntent.getActivity(this, 0, mainActivityIntent, 0);

        return new NotificationCompat.Builder(this, MainActivity.NOTIFICATION_CHANNEL_ID)
                .setContentTitle("ForegroundService")
                .setContentText("Foreground service is running.")
                .setSmallIcon(R.drawable.ic_notification)
                .setContentIntent(mainActivityPendingIntent)
                .build();
    }
}