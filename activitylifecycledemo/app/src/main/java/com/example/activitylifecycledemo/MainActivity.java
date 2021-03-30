package com.example.activitylifecycledemo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public final String TAG = MainActivity.class.getSimpleName() + " Lifecycle";

    public MainActivity() {
        Log.d(TAG, "Constructor");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_open_activity_one).setOnClickListener(this);
        findViewById(R.id.btn_open_activity_two).setOnClickListener(this);
        findViewById(R.id.btn_open_activity_three).setOnClickListener(this);
        findViewById(R.id.btn_open_activity_four).setOnClickListener(this);
        findViewById(R.id.btn_open_activity_five).setOnClickListener(this);
        findViewById(R.id.btn_open_activity_seven).setOnClickListener(this);

        Log.d(TAG, "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "onRestoreInstanceState");
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.btn_open_activity_one: {
                intent = new Intent(this, OneActivity.class);
                break;
            }

            case R.id.btn_open_activity_two: {
                intent = new Intent(this, TwoActivity.class);
                break;
            }

            case R.id.btn_open_activity_three: {
                intent = new Intent(this, ThreeActivity.class);
                break;
            }

            case R.id.btn_open_activity_four: {
                intent = new Intent(this, FourActivity.class);
                intent.putExtra("count", 1);
                startActivity(intent);
                intent.putExtra("count", 2);
                break;
            }

            case R.id.btn_open_activity_five: {
                intent = new Intent(this, FiveActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                break;
            }

            case R.id.btn_open_activity_seven: {
                intent = new Intent(this, SevenActivity.class);
                break;
            }
        }

        startActivity(intent);
    }
}