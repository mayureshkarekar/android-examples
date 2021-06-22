package com.example.databasedemo.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

/* To use SQLiteOpenHelper, create a subclass that overrides the onCreate() and onUpgrade() callback
methods. You may also want to implement the onDowngrade() or onOpen() methods, but they are not
required. */
class StudentDatabase(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        /* Increment the database version before deploying the app if database structure is modified
        in new version. */
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "student.db"
    }

    /* NOTE: By implementing the BaseColumns interface, your inner class can inherit a primary key
    field called _ID that some Android classes such as CursorAdapter expect it to have. It's not
    required, but this can help your database work harmoniously with the Android framework. */
    object StudentTable : BaseColumns {
        const val TABLE_NAME = "student"
        const val COLUMN_STUDENT_NAME = "student_name"
        const val CREATE_TABLE_QUERY = "CREATE TABLE $TABLE_NAME (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                "$COLUMN_STUDENT_NAME TEXT)"
        const val DROP_TABLE_QUERY = "DROP TABLE IF EXISTS $TABLE_NAME "
    }

    // This method will be called when the database is freshly created.
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(StudentTable.CREATE_TABLE_QUERY)
    }

    // This method will be called when the database version is upgraded.
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Deleting all the tables and creating tables again.
        db?.execSQL(StudentTable.DROP_TABLE_QUERY)
        onCreate(db)
    }

    // This method will be called when the database version is downgraded.
    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }
}