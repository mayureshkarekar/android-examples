package com.example.broadcastreceiverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class StaticBroadcastReceiver extends BroadcastReceiver {
    public static final String STATIC_BROADCAST = "com.example.broadcastreceiverdemo.STATIC_BROADCAST";

    @Override
    public void onReceive(Context context, Intent intent) {
        final String RECEIVED_ACTION = intent.getAction();
        Log.d("Broadcast Test", "Static broadcast received for action: " + RECEIVED_ACTION);

        if (STATIC_BROADCAST.equals(RECEIVED_ACTION)) {
            MainApplication.showNotification(context, "New Static Broadcast Received.", "Received Action: " + RECEIVED_ACTION);
        }
    }
}