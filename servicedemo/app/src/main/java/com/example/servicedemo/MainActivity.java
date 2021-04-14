package com.example.servicedemo;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    public static final String NOTIFICATION_CHANNEL_ID = "fsnc";

    private boolean isBound = false;
    private TextView tvMessage;
    private BoundService boundService;

    private final ServiceConnection boundServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder iBinder) {
            isBound = true;
            BoundService.Binder binder = (BoundService.Binder) iBinder;
            boundService = binder.getService();
            Log.d(TAG, "Bound service connected.");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
            Log.d(TAG, "Bound service disconnected.");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNotificationChannel();
        init();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_start_bg_service: {
                Intent backgroundServiceIntent = new Intent(this, BackgroundService.class);
                startService(backgroundServiceIntent);
                Toast.makeText(this, R.string.background_service_started, Toast.LENGTH_SHORT).show();
            }
            break;

            case R.id.btn_stop_bg_service: {
                Intent backgroundServiceIntent = new Intent(this, BackgroundService.class);
                stopService(backgroundServiceIntent);
                Toast.makeText(this, R.string.background_service_stopped, Toast.LENGTH_SHORT).show();
            }
            break;

            case R.id.btn_start_fg_service: {
                Intent foregroundServiceIntent = new Intent(this, ForegroundService.class);

                // This method will call startForegroundService() or startService() depending on
                // the API level. After calling startForegroundService() the app has 5 seconds to
                // call startForeground() method. If app does not call startForeground() in time,
                // the OS will terminate the service.
                ContextCompat.startForegroundService(this, foregroundServiceIntent);

                Toast.makeText(this, R.string.foreground_service_started, Toast.LENGTH_SHORT).show();
            }
            break;

            case R.id.btn_stop_fg_service: {
                Intent foregroundServiceIntent = new Intent(this, ForegroundService.class);
                stopService(foregroundServiceIntent);
                Toast.makeText(this, R.string.foreground_service_stopped, Toast.LENGTH_SHORT).show();
            }
            break;

            case R.id.btn_bind_service: {
                Intent boundServiceIntent = new Intent(this, BoundService.class);
                bindService(boundServiceIntent, boundServiceConnection, Context.BIND_AUTO_CREATE);
                Toast.makeText(this, R.string.service_bound, Toast.LENGTH_SHORT).show();
            }
            break;

            case R.id.btn_unbind_service: {
                if (isBound) {
                    unbindService(boundServiceConnection);
                    isBound = false;
                    Toast.makeText(this, R.string.service_unbound, Toast.LENGTH_SHORT).show();
                }
            }
            break;

            case R.id.tv_message: {
                if (isBound) {
                    tvMessage.setText(getString(R.string.counter_value_value, String.valueOf(boundService.getCounter())));
                } else {
                    tvMessage.setText(getString(R.string.service_not_bound));
                }
            }
            break;
        }
    }

    private void init() {
        findViewById(R.id.btn_start_bg_service).setOnClickListener(this);
        findViewById(R.id.btn_stop_bg_service).setOnClickListener(this);
        findViewById(R.id.btn_start_fg_service).setOnClickListener(this);
        findViewById(R.id.btn_stop_fg_service).setOnClickListener(this);
        findViewById(R.id.btn_bind_service).setOnClickListener(this);
        findViewById(R.id.btn_unbind_service).setOnClickListener(this);
        tvMessage = findViewById(R.id.tv_message);
        tvMessage.setOnClickListener(this);
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Foreground Service Channel";
            String description = "This is the notification channel for foreground service.";
            int importance = NotificationManager.IMPORTANCE_MIN;
            NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}