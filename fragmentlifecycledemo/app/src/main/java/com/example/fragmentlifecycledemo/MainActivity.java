package com.example.fragmentlifecycledemo;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_fragments_using_xml).setOnClickListener(v -> startActivity(new Intent(this, XMLFragmentActivity.class)));
        findViewById(R.id.btn_fragments_using_program).setOnClickListener(v -> startActivity(new Intent(this, ProgrammedFragmentActivity.class)));
    }
}