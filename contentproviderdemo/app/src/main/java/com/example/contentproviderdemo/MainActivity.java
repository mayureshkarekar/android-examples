package com.example.contentproviderdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        Intent intent = new Intent();

        if (viewId == R.id.btn_simple_content_provider) {
            intent.setClass(this, SimpleContentProviderActivity.class);
        } else if (viewId == R.id.btn_custom_content_provider) {
            intent.setClass(this, CustomContentProviderActivity.class);
        }

        startActivity(intent);
    }

    private void init() {
        findViewById(R.id.btn_simple_content_provider).setOnClickListener(this);
        findViewById(R.id.btn_custom_content_provider).setOnClickListener(this);
    }
}