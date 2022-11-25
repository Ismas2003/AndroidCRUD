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
import com.example.androidcrud.tables.Doctors;

import java.util.List;


public class DoctorsAeActivity extends AppCompatActivity {
    TextView firstName, lastName, patronymic, experience, login, password;

    Doctors doctor;
    List<Doctors> doctors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_ae);

        doctors = MainActivity.db.doctorsDao().getAll();

        firstName = findViewById(R.id.input_first_name);
        lastName = findViewById(R.id.input_last_name);
        patronymic = findViewById(R.id.input_patronymic);
        experience = findViewById(R.id.input_experience);
        login = findViewById(R.id.input_login);
        password = findViewById(R.id.input_password);

        if (getIntent().hasExtra("id")) {
            doctor = doctors.get(getIntent().getExtras().getInt("id"));

            firstName.setText(doctor.firstName);
            lastName.setText(doctor.lastName);
            patronymic.setText(doctor.patronymic);
            experience.setText(String.valueOf(doctor.experience));
            login.setText(doctor.login);
            password.setText(doctor.password);
        } else {
            doctor = new Doctors("", "", "", 0, "", "");
        }
    }

    public void onBackToTablesClick(View view) {
        Intent intent = new Intent(this, TablesActivity.class);
        startActivity(intent);
    }

    public void onSaveClick(View view) {
        if (firstName.getText().toString().equals("") || lastName.getText().toString().equals("") ||
                patronymic.getText().toString().equals("") || experience.getText().toString().equals("") ||
                login.getText().toString().equals("") || password.getText().toString().equals("")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Error");
            builder.setMessage("All fields must be filled");
            builder.setPositiveButton("OK", null);
            builder.show();
        } else {
            doctor.firstName = firstName.getText().toString();
            doctor.lastName = lastName.getText().toString();
            doctor.patronymic = patronymic.getText().toString();
            doctor.experience = Integer.parseInt(experience.getText().toString());
            doctor.login = login.getText().toString();
            doctor.password = password.getText().toString();

            MainActivity.db.doctorsDao().insertAll(doctor);

            Intent intent = new Intent(this, TablesActivity.class);
            startActivity(intent);
        }
    }

    public void onDeleteClick(View view) {
        new AlertDialog.Builder(this)
                .setTitle("Delete doctor")
                .setMessage("Are you sure you want to delete this doctor?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    MainActivity.db.doctorsDao().delete(doctor);
                    Intent intent = new Intent(this, TablesActivity.class);
                    startActivity(intent);
                })
                .setNegativeButton("No", null)
                .show();
    }
}
