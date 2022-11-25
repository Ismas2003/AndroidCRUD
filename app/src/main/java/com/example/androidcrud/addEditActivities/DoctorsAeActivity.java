package com.example.androidcrud.addEditActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidcrud.MainActivity;
import com.example.androidcrud.R;
import com.example.androidcrud.TablesActivity;
import com.example.androidcrud.tables.Doctors;

import java.util.List;


public class DoctorsAeActivity extends AppCompatActivity {
    TextView firstName, lastName, patronymic, experience, login, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_ae);

        int id = getIntent().getExtras().getInt("id");
        List<Doctors> doctors = MainActivity.db.doctorsDao().getAll();
        Doctors doctor = doctors.get(id);

        firstName = findViewById(R.id.input_first_name);
        lastName = findViewById(R.id.input_last_name);
        patronymic = findViewById(R.id.input_patronymic);
        experience = findViewById(R.id.input_experience);
        login = findViewById(R.id.input_login);
        password = findViewById(R.id.input_password);

        firstName.setText(doctor.firstName);
        lastName.setText(doctor.lastName);
        patronymic.setText(doctor.patronymic);
        experience.setText(String.valueOf(doctor.experience));
        login.setText(doctor.login);
        password.setText(doctor.password);
    }

    public void onBackToTablesClick(View view) {
        Intent intent = new Intent(this, TablesActivity.class);
        startActivity(intent);
    }
}
