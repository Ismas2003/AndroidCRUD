package com.example.androidcrud;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class TablesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tables);

        if (MainActivity.isAdmin) {
            setTitle("Admin");
        } else {
            setTitle("User");
        }
    }
}