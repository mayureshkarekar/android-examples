package com.example.contentproviderdemo;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class StudentProvider extends ContentProvider {
    private static final String AUTHORITY = "com.example.contentproviderdemo.StudentProvider";

    private static final String PATH_ALL_STUDENTS = "students_list";
    private static final String PATH_STUDENT_WITH_ID = "student_with_id/#";
    private static final String PATH_STUDENTS_IN_CITY = "students_in_city";

    private static final int TYPE_ALL_STUDENTS = 1;
    private static final int TYPE_STUDENT_WITH_ID = 2;
    private static final int TYPE_STUDENTS_IN_CITY = 3;

    public static final Uri CONTENT_URI_ALL_STUDENTS = Uri.parse("content://" + AUTHORITY + "/" + PATH_ALL_STUDENTS);
    public static final Uri CONTENT_URI_STUDENT_WITH_ID = Uri.parse("content://" + AUTHORITY + "/" + PATH_STUDENT_WITH_ID);
    public static final Uri CONTENT_URI_STUDENTS_IN_CITY = Uri.parse("content://" + AUTHORITY + "/" + PATH_STUDENTS_IN_CITY);

    private static final UriMatcher uriMatcher;

    private SQLiteDatabase database;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, PATH_ALL_STUDENTS, TYPE_ALL_STUDENTS);
        uriMatcher.addURI(AUTHORITY, PATH_STUDENT_WITH_ID, TYPE_STUDENT_WITH_ID);
        uriMatcher.addURI(AUTHORITY, PATH_STUDENTS_IN_CITY, TYPE_STUDENTS_IN_CITY);
    }

    @Override
    public boolean onCreate() {
        StudentDatabaseHelper databaseHelper = new StudentDatabaseHelper(getContext());
        database = databaseHelper.getWritableDatabase();
        return database != null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)) {
            case TYPE_ALL_STUDENTS:
                return ContentResolver.CURSOR_DIR_BASE_TYPE + "/vnd." + AUTHORITY + ".students_list";

            case TYPE_STUDENT_WITH_ID:
                return ContentResolver.CURSOR_ITEM_BASE_TYPE + "/vnd." + AUTHORITY + ".student_with_id";

            case TYPE_STUDENTS_IN_CITY:
                return ContentResolver.CURSOR_DIR_BASE_TYPE + "/vnd." + AUTHORITY + ".students_in_city";

            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        switch (uriMatcher.match(uri)) {
            case TYPE_ALL_STUDENTS: {
                long rowId = database.insert(StudentDatabaseHelper.STUDENTS_TABLE_NAME, "", values);

                if (rowId > 0) {
                    getContext().getContentResolver().notifyChange(uri, null);
                    return ContentUris.withAppendedId(CONTENT_URI_ALL_STUDENTS, rowId);
                }

                return null;
            }

            default:
                throw new UnsupportedOperationException("Insert operation not supported");
        }
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int count = -1;

        switch (uriMatcher.match(uri)) {
            case TYPE_ALL_STUDENTS: {
                database.delete(StudentDatabaseHelper.STUDENTS_TABLE_NAME, selection, selectionArgs);
            }
            break;

            case TYPE_STUDENT_WITH_ID: {
                String id = uri.getPathSegments().get(1);
                count = database.delete(StudentDatabaseHelper.STUDENTS_TABLE_NAME, StudentDatabaseHelper.STUDENT_ID_COLUMN_NAME + "=" + id, null);
            }
            break;

            default:
                throw new UnsupportedOperationException("Delete operation not supported");
        }

        return count;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        int count = -1;

        switch (uriMatcher.match(uri)) {
            case TYPE_ALL_STUDENTS: {
                database.update(StudentDatabaseHelper.STUDENTS_TABLE_NAME, values, selection, selectionArgs);
            }
            break;

            case TYPE_STUDENT_WITH_ID: {
                String id = uri.getPathSegments().get(1);
                count = database.update(StudentDatabaseHelper.STUDENTS_TABLE_NAME, values, StudentDatabaseHelper.STUDENT_ID_COLUMN_NAME + "=" + id, null);
            }
            break;

            default:
                throw new UnsupportedOperationException("Update operation not supported");
        }

        return count;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor cursor = null;

        switch (uriMatcher.match(uri)) {
            case TYPE_ALL_STUDENTS: {
                String[] columns = {StudentDatabaseHelper.STUDENT_ID_COLUMN_NAME, StudentDatabaseHelper.STUDENT_NAME_COLUMN_NAME, StudentDatabaseHelper.STUDENT_CITY_COLUMN_NAME};
                cursor = database.query(StudentDatabaseHelper.STUDENTS_TABLE_NAME, columns, null, null, null, null, StudentDatabaseHelper.STUDENT_ID_COLUMN_NAME);
            }
            break;

            case TYPE_STUDENT_WITH_ID: {
                String id = uri.getPathSegments().get(1);
                String[] columns = {StudentDatabaseHelper.STUDENT_ID_COLUMN_NAME, StudentDatabaseHelper.STUDENT_NAME_COLUMN_NAME, StudentDatabaseHelper.STUDENT_CITY_COLUMN_NAME};
                cursor = database.query(StudentDatabaseHelper.STUDENTS_TABLE_NAME, columns, StudentDatabaseHelper.STUDENT_ID_COLUMN_NAME + "=" + id, null, null, null, null);
            }
            break;
        }

        return cursor;
    }
}