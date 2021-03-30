package com.example.broadcaster;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String STATIC_BROADCAST = "com.example.broadcastreceiverdemo.STATIC_BROADCAST";
    public static final String DYNAMIC_BROADCAST = "com.example.broadcastreceiverdemo.DYNAMIC_BROADCAST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_broadcast_static_action).setOnClickListener(this);
        findViewById(R.id.btn_broadcast_dynamic_action).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();

        if (viewId == R.id.btn_broadcast_static_action) {
            Intent intent = new Intent();
            intent.setAction(STATIC_BROADCAST);
            sendBroadcast(intent);
            Toast.makeText(this, "Static Broadcast sent.", Toast.LENGTH_SHORT).show();
        } else if (viewId == R.id.btn_broadcast_dynamic_action) {
            Intent intent = new Intent();
            intent.setAction(DYNAMIC_BROADCAST);
            sendBroadcast(intent);
            Toast.makeText(this, "Dynamic Broadcast sent.", Toast.LENGTH_SHORT).show();
        }
    }
}