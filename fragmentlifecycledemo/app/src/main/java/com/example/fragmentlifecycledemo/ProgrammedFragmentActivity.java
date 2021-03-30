package com.example.fragmentlifecycledemo;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class ProgrammedFragmentActivity extends AppCompatActivity {
    private final String TAG = ProgrammedFragmentActivity.class.getSimpleName() + " Test";

    public ProgrammedFragmentActivity() {
        Log.d(TAG, "ProgrammedFragmentActivity Constructor");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programmed_fragment);

        findViewById(R.id.btn_fragment_one).setOnClickListener(v -> replaceFragment(OneFragment.class));
        findViewById(R.id.btn_fragment_two).setOnClickListener(v -> replaceFragment(TwoFragment.class));
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

    private void replaceFragment(Class<? extends Fragment> fragmentClass) {
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fcv, fragmentClass, null)
                .commit();
    }
}