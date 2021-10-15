package com.example.databindingdemo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

/*
------------------------------------------- DATA BINDING -------------------------------------------
The Data Binding Library is a support library that allows you to bind UI components in your
layouts to data sources in your app using a declarative format rather than programmatically.

-------------------------------- ADVANTAGES OF DATA BINDING LIBRARY --------------------------------
1. No findViewById():             Calling findViewById() is a heavy operation it scans the entire
                                  hierarchy to find the specified view id. Data binding uses
                                  declarative format which improves the performance.

2. No NullPointerException:       As binding happens in compile time you will never get
                                  NullPointerException in runtime.

3. Performance & No Memory Leaks: Data binding improves performance of the app and also reduces the
                                  chances of memory leaks.

*/

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_open_simple_data_binding_activity).setOnClickListener {
            startActivity(Intent(this, SimpleDataBindingActivity::class.java))
        }

        findViewById<Button>(R.id.btn_open_fragment_data_binding_activity).setOnClickListener {
            startActivity(Intent(this, FragmentDataBindingActivity::class.java))
        }

        findViewById<Button>(R.id.btn_open_binding_adapter_activity).setOnClickListener {
            startActivity(Intent(this, BindingAdapterActivity::class.java))
        }

        findViewById<Button>(R.id.btn_list_data_binding_activity).setOnClickListener {
            startActivity(Intent(this, ListDataBindingActivity::class.java))
        }
    }
}