package com.example.contentproviderdemo;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.util.ArrayList;

public class SimpleContentProviderActivity extends AppCompatActivity {
    private final int READ_CONTACT_PERMISSION_REQUEST_CODE = 1;
    private TextView tvError;
    private ListView lvContactList;
    private ArrayList<String> contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_content_provider);

        init();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            fetchContacts();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, READ_CONTACT_PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == READ_CONTACT_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                fetchContacts();
            } else {
                lvContactList.setVisibility(View.GONE);
                tvError.setText(R.string.failed_to_fetch_the_contact_list);
                tvError.setVisibility(View.VISIBLE);
                Toast.makeText(this, R.string.read_contact_permission_required, Toast.LENGTH_SHORT).show();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void init() {
        tvError = findViewById(R.id.tv_error);
        lvContactList = findViewById(R.id.lv_contact_list);
        contactList = new ArrayList<>();
    }

    private void fetchContacts() {
        ContentResolver contentResolver = getContentResolver();
        Uri contactsContentUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

        Cursor cursor = contentResolver.query(contactsContentUri, null, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                contactList.add(name + "\n" + number);
            }

            ArrayAdapter<String> contactListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contactList);
            lvContactList.setAdapter(contactListAdapter);

            lvContactList.setVisibility(View.VISIBLE);
            tvError.setVisibility(View.GONE);
        } else {
            lvContactList.setVisibility(View.GONE);
            tvError.setText(R.string.no_contacts);
            tvError.setVisibility(View.VISIBLE);
        }

        cursor.close();
    }
}