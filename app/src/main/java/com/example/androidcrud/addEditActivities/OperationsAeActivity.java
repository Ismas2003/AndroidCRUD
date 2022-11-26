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
import com.example.androidcrud.tables.Operations;

import java.util.List;


public class OperationsAeActivity extends AppCompatActivity {
    TextView name, description, operationTypeId, doctorId;

    Operations item;
    List<Operations> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operations_ae);

        list = MainActivity.db.operationsDao().getAll();

        name = findViewById(R.id.input_name);
        description = findViewById(R.id.input_description);
        operationTypeId = findViewById(R.id.input_operation_type_id);
        doctorId = findViewById(R.id.input_doctor_id);

        if (getIntent().hasExtra("id")) {
            item = list.get(getIntent().getExtras().getInt("id"));

            name.setText(item.name);
            description.setText(item.description);
            operationTypeId.setText(String.valueOf(item.operationTypeId));
            doctorId.setText(String.valueOf(item.doctorId));
        } else {
            item = new Operations("", "", 0, 0);
        }
    }

    public void onBackToTablesClick(View view) {
        Intent intent = new Intent(this, TablesActivity.class);
        startActivity(intent);
    }

    public void onSaveClick(View view) {
        if (name.getText().toString().equals("") || description.getText().toString().equals("") ||
                operationTypeId.getText().toString().equals("") ||
                doctorId.getText().toString().equals("")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Error");
            builder.setMessage("All fields must be filled");
            builder.setPositiveButton("OK", null);
            builder.show();
        } else {
            item.name = name.getText().toString();
            item.description = description.getText().toString();
            try {
                item.operationTypeId = Integer.parseInt(operationTypeId.getText().toString());
                item.doctorId = Integer.parseInt(doctorId.getText().toString());
                if (MainActivity.db.doctorsDao().getAll().get(item.doctorId) == null ||
                        MainActivity.db.operationsTypesDao().getAll().get(item.operationTypeId) == null) {
                    throw new Exception();
                }
            } catch(Exception e) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Error");
                builder.setMessage("Operation type ID and Doctor ID must be numbers and existing IDs");
                builder.setPositiveButton("OK", null);
                builder.show();
                return;
            }

            MainActivity.db.operationsDao().insertAll(item);

            Intent intent = new Intent(this, TablesActivity.class);
            startActivity(intent);
        }
    }

    public void onDeleteClick(View view) {
        new AlertDialog.Builder(this)
                .setTitle("Delete operation")
                .setMessage("Are you sure you want to delete this operation?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    MainActivity.db.operationsDao().delete(item);
                    Intent intent = new Intent(this, TablesActivity.class);
                    startActivity(intent);
                })
                .setNegativeButton("No", null)
                .show();
    }
}
