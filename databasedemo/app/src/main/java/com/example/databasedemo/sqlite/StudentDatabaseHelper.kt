package com.example.databasedemo.sqlite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.provider.BaseColumns
import android.util.Log
import java.sql.SQLException

object StudentDatabaseHelper {
    private const val TAG = "StudentDatabaseHelper"

    // Inserting data to the database.
    fun insertStudent(context: Context, studentName: String): Long {
        val studentDatabase = StudentDatabase(context)
        val databaseWriter =
            studentDatabase.writableDatabase // Gets the data repository in write mode.

        try {
            // Create a new map of values, where column names are the keys.
            val values = ContentValues().apply {
                put(StudentDatabase.StudentTable.COLUMN_STUDENT_NAME, studentName)
            }

            // Insert the new row, returning the primary key value of the new row.
            val newRowId = databaseWriter.insert(
                StudentDatabase.StudentTable.TABLE_NAME,
                null,
                values
            )

            Log.i(TAG, "Student inserted.")

            return newRowId
        } catch (ex: SQLException) {
            Log.e(TAG, "Failed to insert student.")
        } finally {
            // Closing the resources after use.
            databaseWriter.close()
            studentDatabase.close()
        }

        return -1
    }

    // Updating existing data in the database.
    fun updateStudent(context: Context, studentId: Int, studentName: String): Int {
        val studentDatabase = StudentDatabase(context)
        val databaseWriter =
            studentDatabase.writableDatabase // Gets the data repository in write mode.

        try {
            // New values for record.
            val values = ContentValues().apply {
                put(StudentDatabase.StudentTable.COLUMN_STUDENT_NAME, studentName)
            }

            // Which record to update.
            val selection = "${BaseColumns._ID} = ?"
            val selectionArgs = arrayOf(studentId.toString())

            // Rows affected.
            val count = databaseWriter.update(
                StudentDatabase.StudentTable.TABLE_NAME,
                values,
                selection,
                selectionArgs
            )

            Log.i(TAG, "Student updated.")

            return count
        } catch (ex: SQLException) {
            Log.e(TAG, "Failed to update student.")
        } finally {
            // Closing the resources after use.
            databaseWriter.close()
            studentDatabase.close()
        }

        return -1
    }

    // Deleting existing data in the database.
    fun deleteStudent(context: Context, studentId: Int): Int {
        val studentDatabase = StudentDatabase(context)
        val databaseWriter =
            studentDatabase.writableDatabase // Gets the data repository in write mode.

        try {
            // Which record to delete.
            val selection = "${BaseColumns._ID} = ?"
            val selectionArgs = arrayOf(studentId.toString())

            // Rows deleted.
            val count = databaseWriter.delete(
                StudentDatabase.StudentTable.TABLE_NAME,
                selection,
                selectionArgs
            )

            Log.i(TAG, "Student deleted.")

            return count
        } catch (ex: SQLException) {
            Log.e(TAG, "Failed to delete student.")
        } finally {
            // Closing the resources after use.
            databaseWriter.close()
            studentDatabase.close()
        }

        return -1
    }

    // Fetching data from the database.
    fun fetchStudents(context: Context, studentId: Int = 0): String? {
        val studentDatabase = StudentDatabase(context)
        val databaseReader =
            studentDatabase.readableDatabase // Gets the data repository in read mode.
        var cursor: Cursor? = null // To store returned data.

        try {
            // Which record to fetch.
            var selection: String? = null
            var selectionArgs: Array<String>? = null

            if (studentId > 0) {
                selection = "${BaseColumns._ID} = ?"
                selectionArgs = arrayOf(studentId.toString())
            }

            // How you want the results sorted in the resulting Cursor.
            val sortOrder = "${StudentDatabase.StudentTable.COLUMN_STUDENT_NAME} ASC"

            // Fetched data.
            cursor = databaseReader.query(
                StudentDatabase.StudentTable.TABLE_NAME,
                null, // The array of columns to return (pass null to get all).
                selection, // The columns for the WHERE clause.
                selectionArgs, // The values for the WHERE clause.
                null, // Don't group the rows.
                null, // Don't filter by row groups.
                sortOrder // The sort order.
            )

            cursor?.let {
                it.moveToFirst()

                // Collecting result data to string.
                if (it.count > 0) {
                    var studentsList = ""

                    do {
                        studentsList += "${it.getString(it.getColumnIndex(BaseColumns._ID))} - ${
                        it.getString(
                            it.getColumnIndex(StudentDatabase.StudentTable.COLUMN_STUDENT_NAME)
                        )
                        }\n"
                    } while (it.moveToNext())

                    Log.i(TAG, "Student fetched.")

                    return studentsList
                }
            }
        } catch (ex: SQLException) {
            Log.e(TAG, "Failed to delete student.")
        } finally {
            // Closing the resources after use.
            cursor?.close()
            databaseReader.close()
            studentDatabase.close()
        }

        return null
    }
}