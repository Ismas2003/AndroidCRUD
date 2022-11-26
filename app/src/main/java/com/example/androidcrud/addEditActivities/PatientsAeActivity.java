package com.example.androidcrud.addEditActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidcrud.MainActivity;
import com.example.androidcrud.R;
import com.example.androidcrud.TablesActivity;
import com.example.androidcrud.tables.Patients;

import java.util.List;


public class PatientsAeActivity extends AppCompatActivity {
    TextView firstName, lastName, patronymic, address, wardId;

    Patients item;
    List<Patients> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patients_ae);

        list = MainActivity.db.patientsDao().getAll();

        firstName = findViewById(R.id.input_first_name);
        lastName = findViewById(R.id.input_last_name);
        patronymic = findViewById(R.id.input_patronymic);
        address = findViewById(R.id.input_address);
        wardId = findViewById(R.id.input_ward_id);

        if (getIntent().hasExtra("id")) {
            item = list.get(getIntent().getExtras().getInt("id"));

            firstName.setText(item.firstName);
            lastName.setText(item.lastName);
            patronymic.setText(item.patronymic);
            address.setText(item.address);
            wardId.setText(String.valueOf(item.wardId));
        } else {
            item = new Patients("", "", "", "", 0);
        }
    }

    public void onBackToTablesClick(View view) {
        Intent intent = new Intent(this, TablesActivity.class);
        startActivity(intent);
    }

    public void onSaveClick(View view) {
        if (firstName.getText().toString().equals("") || lastName.getText().toString().equals("") ||
                patronymic.getText().toString().equals("") || address.getText().toString().equals("") ||
                wardId.getText().toString().equals("")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Error");
            builder.setMessage("All fields must be filled");
            builder.setPositiveButton("OK", null);
            builder.show();
        } else {
            item.firstName = firstName.getText().toString();
            item.lastName = lastName.getText().toString();
            item.patronymic = patronymic.getText().toString();
            item.address = address.getText().toString();
            try {
                item.wardId = Integer.parseInt(wardId.getText().toString());
                if (MainActivity.db.wardsDao().getAll().get(item.wardId) == null) {
                    throw new Exception();
                }
            } catch(Exception e) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Error");
                builder.setMessage("Ward ID must be a number");
                builder.setPositiveButton("OK", null);
                builder.show();
                return;
            }

            MainActivity.db.patientsDao().insertAll(item);

            Intent intent = new Intent(this, TablesActivity.class);
            startActivity(intent);
        }
    }

    public void onDeleteClick(View view) {
        new AlertDialog.Builder(this)
                .setTitle("Delete patient")
                .setMessage("Are you sure you want to delete this patient?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    MainActivity.db.patientsDao().delete(item);
                    Intent intent = new Intent(this, TablesActivity.class);
                    startActivity(intent);
                })
                .setNegativeButton("No", null)
                .show();
    }
}
