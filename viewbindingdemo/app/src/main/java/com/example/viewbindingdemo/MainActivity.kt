package com.example.viewbindingdemo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

/*
------------------------------------------- VIEW BINDING -------------------------------------------
View binding is a feature that allows you to more easily write code that interacts with views. Once
view binding is enabled in a module, it generates a binding class for each XML layout file present
in that module. An instance of a binding class contains direct references to all views that have an
ID in the corresponding layout.

-------------------------------- ADVANTAGES OF VIEW BINDING LIBRARY --------------------------------
1. No findViewById().
2. It is null-safe and type-safe.
3. Supports both Java and Kotlin.
4. It helps to reduce the boilerplate code and hence redundant code.
5. Using ViewBinding the compilation of the code is a bit faster as compared to the traditional
   findViewById() method.

------------------- VIEW BINDING VS KOTLIN ANDROID EXTENSIONS (KOTLIN SYNTHETIC) -------------------
1. KAE is deprecated.
2. KAE supports Kotlin only. View Binding supports both Java and Kotlin.
3. KAE exposes the global namespace of views i.e. view from one layout can be accessible in another
   layout which may cause NullPointerException at runtime.

----------------------------------- VIEW BINDING VS BUTTER KNIFE -----------------------------------
1. ButterKnife is deprecated.
2. ButterKnife is not compile-time safe.

----------------------------------- VIEW BINDING VS DATA BINDING -----------------------------------
1. View Binding does not need <layout> tag.
2. View Binding is faster than Data Binding as it avoids overheads of annotation processors.
3. View Binding is a sub-feature of Data Binding, with View Binding you cannot bind layouts with
   data in XML (No binding expressions, No Binding Adapters, and No nor two-way view binding).

*/

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_activity_view_binding).setOnClickListener {
            startActivity(
                Intent(this, ActivityViewBindingActivity::class.java)
            )
        }

        findViewById<Button>(R.id.btn_fragment_view_binding).setOnClickListener {
            startActivity(
                Intent(this, FragmentViewBindingActivity::class.java)
            )
        }

        findViewById<Button>(R.id.btn_list_view_binding).setOnClickListener {
            startActivity(
                Intent(this, ListViewBindingActivity::class.java)
            )
        }
    }
}