package com.example.activitylifecycledemo;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class OneActivity extends AppCompatActivity {
    public final String TAG = OneActivity.class.getSimpleName() + " Lifecycle";
    public String lastFruit = "Apple";
    private EditText editText1, editText2;

    public OneActivity() {
        Log.d(TAG, "Constructor");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
        Log.d(TAG, "onCreate");

        editText1 = findViewById(R.id.editText_1);
        editText2 = findViewById(R.id.editText_2);

        boolean isSavedInstanceStateNull = (savedInstanceState == null);

        Log.d(TAG, "Is SavedInstanceState Null?: " + isSavedInstanceStateNull);

        if (isSavedInstanceStateNull)
            Log.d(TAG, "Last Fruit: " + lastFruit);
        else
            Log.d(TAG, "Last Fruit: " + savedInstanceState.getString("last_fruit"));
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
        Log.d(TAG, "onSaveInstanceState");
        outState.putString("last_fruit", "Mango");
        outState.putString("edit_text_1", editText1.getText().toString() + " New");
        outState.putString("edit_text_2", editText2.getText().toString() + " New");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "onRestoreInstanceState");
        editText1.setText(savedInstanceState.getString("edit_text_1", ""));
        editText2.setText(savedInstanceState.getString("edit_text_2", ""));
    }
}