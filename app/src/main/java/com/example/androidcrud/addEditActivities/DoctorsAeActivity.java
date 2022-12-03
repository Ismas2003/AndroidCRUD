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

    Doctors item;
    List<Doctors> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_ae);

        list = MainActivity.db.doctorsDao().getAll();

        firstName = findViewById(R.id.input_first_name);
        lastName = findViewById(R.id.input_last_name);
        patronymic = findViewById(R.id.input_patronymic);
        experience = findViewById(R.id.input_experience);
        login = findViewById(R.id.input_login);
        password = findViewById(R.id.input_password);

        if (getIntent().hasExtra("id")) {
            item = list.get(getIntent().getExtras().getInt("id"));

            firstName.setText(item.firstName);
            lastName.setText(item.lastName);
            patronymic.setText(item.patronymic);
            experience.setText(String.valueOf(item.experience));
            login.setText(item.login);
            password.setText(item.password);
        } else {
            item = new Doctors("", "", "", 0, "", "");
        }
    }

    public void onBackToTablesClick(View view) {
        Intent intent = new Intent(this, TablesActivity.class);
        intent.putExtra("table", "doctors");
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
            item.firstName = firstName.getText().toString();
            item.lastName = lastName.getText().toString();
            item.patronymic = patronymic.getText().toString();
            try {
                item.experience = Integer.parseInt(experience.getText().toString());
            } catch(NumberFormatException nfe) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Error");
                builder.setMessage("Experience must be a number");
                builder.setPositiveButton("OK", null);
                builder.show();
                return;
            }
            item.login = login.getText().toString();
            if (!item.password.equals(password.getText().toString())) {
                item.password = MainActivity.sha512(password.getText().toString()).toString();
            }

            MainActivity.db.doctorsDao().insertAll(item);

            Intent intent = new Intent(this, TablesActivity.class);
            intent.putExtra("table", "doctors");
            startActivity(intent);
        }
    }

    public void onDeleteClick(View view) {
        new AlertDialog.Builder(this)
                .setTitle("Delete doctor")
                .setMessage("Are you sure you want to delete this doctor?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    MainActivity.db.doctorsDao().delete(item);
                    Intent intent = new Intent(this, TablesActivity.class);
                    intent.putExtra("table", "doctors");
                    startActivity(intent);
                })
                .setNegativeButton("No", null)
                .show();
    }
}
