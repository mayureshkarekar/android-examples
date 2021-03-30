package com.example.broadcastreceiverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.util.Log;

public class DynamicBroadcastReceiver extends BroadcastReceiver {
    public static final String DYNAMIC_BROADCAST = "com.example.broadcastreceiverdemo.DYNAMIC_BROADCAST";

    @Override
    public void onReceive(Context context, Intent intent) {
        final String RECEIVED_ACTION = intent.getAction();
        Log.d("Broadcast Test", "Dynamic broadcast received for action: " + RECEIVED_ACTION);

        if (DYNAMIC_BROADCAST.equals(RECEIVED_ACTION)) {
            MainApplication.showNotification(context, "New Dynamic Broadcast Received.", "Received Action: " + RECEIVED_ACTION);
        } else if (ConnectivityManager.CONNECTIVITY_ACTION.equals(RECEIVED_ACTION)) {
            boolean disconnected = intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false);
            if (disconnected) {
                MainApplication.showNotification(context, "New Dynamic Broadcast Received.", "Network Disconnected.");
            } else {
                MainApplication.showNotification(context, "New Dynamic Broadcast Received.", "Network Connected.");
            }
        }
    }
}