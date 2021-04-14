package com.example.contentproviderclient;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etStudentId, etStudentName, etStudentCity;
    private TextView tvError;
    private ListView lvStudentList;
    private ArrayList<String> studentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();

        if (viewId == R.id.btn_add_student) {
            addStudent(etStudentName.getText().toString(), etStudentCity.getText().toString());
        } else if (viewId == R.id.btn_update_student) {
            updateStudent(etStudentId.getText().toString(), etStudentName.getText().toString(), etStudentCity.getText().toString());
        } else if (viewId == R.id.btn_delete_student) {
            deleteStudent(etStudentId.getText().toString());
        } else if (viewId == R.id.btn_search_by_id) {
            fetchStudent(etStudentId.getText().toString());
        } else if (viewId == R.id.btn_show_all_students) {
            fetchAllStudents();
        }
    }

    private void init() {
        etStudentId = findViewById(R.id.et_student_id);
        etStudentName = findViewById(R.id.et_student_name);
        etStudentCity = findViewById(R.id.et_student_city);
        tvError = findViewById(R.id.tv_error);
        findViewById(R.id.btn_add_student).setOnClickListener(this);
        findViewById(R.id.btn_update_student).setOnClickListener(this);
        findViewById(R.id.btn_delete_student).setOnClickListener(this);
        findViewById(R.id.btn_search_by_id).setOnClickListener(this);
        findViewById(R.id.btn_show_all_students).setOnClickListener(this);
        lvStudentList = findViewById(R.id.lv_student_list);
        studentList = new ArrayList<>();
    }

    private void addStudent(String studentName, String city) {
        if (isNotEmpty(studentName, city)) {
            ContentResolver contentResolver = getContentResolver();

            ContentValues contentValues = new ContentValues();
            contentValues.put("name", studentName);
            contentValues.put("city", city);

            Uri uri = contentResolver.insert(Uri.parse("content://com.example.contentproviderdemo.StudentProvider/students_list"), contentValues);
            if (uri != null) {
                Toast.makeText(this, R.string.student_added_successfully, Toast.LENGTH_SHORT).show();
                clearInputs();
            } else {
                Toast.makeText(this, R.string.failed_to_add_student, Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, R.string.enter_name_and_city, Toast.LENGTH_SHORT).show();
        }
    }

    private void updateStudent(String id, String studentName, String city) {
        if (isNotEmpty(id, studentName, city)) {
            ContentResolver contentResolver = getContentResolver();

            ContentValues contentValues = new ContentValues();
            contentValues.put("name", studentName);
            contentValues.put("city", city);

            Uri contentUriStudentWithId = Uri.parse("content://com.example.contentproviderdemo.StudentProvider/student_with_id/" + id);
            int count = contentResolver.update(contentUriStudentWithId, contentValues, null, null);
            if (count == 1) {
                Toast.makeText(this, R.string.student_details_updated_successfully, Toast.LENGTH_SHORT).show();
                clearInputs();
            } else {
                Toast.makeText(this, R.string.failed_to_update_student_details, Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, R.string.enter_all_the_details, Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteStudent(String id) {
        if (isNotEmpty(id)) {
            ContentResolver contentResolver = getContentResolver();

            Uri contentUriStudentWithId = Uri.parse("content://com.example.contentproviderdemo.StudentProvider/student_with_id/" + id);
            int count = contentResolver.delete(contentUriStudentWithId, null, null);
            if (count == 1) {
                Toast.makeText(this, R.string.student_deleted_successfully, Toast.LENGTH_SHORT).show();
                clearInputs();
            } else {
                Toast.makeText(this, R.string.failed_to_delete_student, Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, R.string.enter_id, Toast.LENGTH_SHORT).show();
        }
    }

    private void fetchStudent(String id) {
        if (isNotEmpty(id)) {
            ContentResolver contentResolver = getContentResolver();
            Cursor cursor = contentResolver.query(Uri.parse("content://com.example.contentproviderdemo.StudentProvider/student_with_id/" + id), null, null, null, null);

            if (cursor != null && cursor.getCount() > 0) {
                studentList.clear();

                while (cursor.moveToNext()) {
                    String studentId = cursor.getString(cursor.getColumnIndex("id"));
                    String studentName = cursor.getString(cursor.getColumnIndex("name"));
                    String studentCity = cursor.getString(cursor.getColumnIndex("city"));

                    studentList.add(studentId + "\n" + studentName + "\n" + studentCity);
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, studentList);
                lvStudentList.setAdapter(adapter);

                tvError.setVisibility(View.GONE);
                lvStudentList.setVisibility(View.VISIBLE);
            } else {
                tvError.setVisibility(View.VISIBLE);
                lvStudentList.setVisibility(View.GONE);
            }

            if (cursor != null)
                cursor.close();
        } else {
            Toast.makeText(this, R.string.enter_id, Toast.LENGTH_SHORT).show();
        }
    }

    private void fetchAllStudents() {
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(Uri.parse("content://com.example.contentproviderdemo.StudentProvider/students_list"), null, null, null, null);

        if (cursor != null && cursor.getCount() > 0) {
            studentList.clear();

            while (cursor.moveToNext()) {
                String studentId = cursor.getString(cursor.getColumnIndex("id"));
                String studentName = cursor.getString(cursor.getColumnIndex("name"));
                String studentCity = cursor.getString(cursor.getColumnIndex("city"));

                studentList.add(studentId + "\n" + studentName + "\n" + studentCity);
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, studentList);
            lvStudentList.setAdapter(adapter);

            tvError.setVisibility(View.GONE);
            lvStudentList.setVisibility(View.VISIBLE);
        } else {
            tvError.setVisibility(View.VISIBLE);
            lvStudentList.setVisibility(View.GONE);
        }

        if (cursor != null)
            cursor.close();
    }

    private boolean isNotEmpty(String... inputs) {
        for (String input : inputs) {
            if (TextUtils.isEmpty(input))
                return false;
        }

        return true;
    }

    private void clearInputs() {
        etStudentId.setText("");
        etStudentName.setText("");
        etStudentCity.setText("");
    }
}