package com.example.contentproviderdemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class StudentDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "StudentsDatabase";
    private static final int DATABASE_VERSION = 1;

    public final static String STUDENTS_TABLE_NAME = "students";
    public final static String STUDENT_ID_COLUMN_NAME = "id";
    public final static String STUDENT_NAME_COLUMN_NAME = "name";
    public final static String STUDENT_CITY_COLUMN_NAME = "city";

    public StudentDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + STUDENTS_TABLE_NAME + "(" + STUDENT_ID_COLUMN_NAME + " INTEGER PRIMARY KEY AUTOINCREMENT, " + STUDENT_NAME_COLUMN_NAME + " TEXT NOT NULL, " + STUDENT_CITY_COLUMN_NAME + " TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + STUDENTS_TABLE_NAME);
        onCreate(db);
    }
}