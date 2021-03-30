package com.example.broadcastreceiverdemo;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    DynamicBroadcastReceiver dynamicBroadcastReceiver = new DynamicBroadcastReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_broadcast_static_action).setOnClickListener(this);
        findViewById(R.id.btn_broadcast_dynamic_action).setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(DynamicBroadcastReceiver.DYNAMIC_BROADCAST);
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(dynamicBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(dynamicBroadcastReceiver);
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();

        if (viewId == R.id.btn_broadcast_static_action) {
            Intent intent = new Intent();
            intent.setAction(StaticBroadcastReceiver.STATIC_BROADCAST);
            sendBroadcast(intent);
        } else if (viewId == R.id.btn_broadcast_dynamic_action) {
            Intent intent = new Intent();
            intent.setAction(DynamicBroadcastReceiver.DYNAMIC_BROADCAST);
            sendBroadcast(intent);
        }
    }
}