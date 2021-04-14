package com.example.jobintentservicedemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etInput;
    public static String KEY_INPUT_TEXT = "input_text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();

        if (viewId == R.id.btn_enqueue_work) {
            String input = etInput.getText().toString();

            Intent serviceIntent = new Intent(this, MainActivity.class);
            serviceIntent.putExtra(KEY_INPUT_TEXT, input);
            SampleJobIntentService.enqueueWork(this, serviceIntent);
        }
    }

    private void init() {
        etInput = findViewById(R.id.et_input);
        findViewById(R.id.btn_enqueue_work).setOnClickListener(this);
    }
}