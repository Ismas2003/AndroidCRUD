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
import com.example.androidcrud.tables.OperationsTypes;

import java.util.List;


public class OperationsTypesAeActivity extends AppCompatActivity {
    TextView name, description;

    OperationsTypes item;
    List<OperationsTypes> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operations_types_ae);

        list = MainActivity.db.operationsTypesDao().getAll();

        name = findViewById(R.id.input_name);
        description = findViewById(R.id.input_description);

        if (getIntent().hasExtra("id")) {
            item = list.get(getIntent().getExtras().getInt("id"));

            name.setText(item.name);
            description.setText(item.description);

        } else {
            item = new OperationsTypes("", "");
        }
    }

    public void onBackToTablesClick(View view) {
        Intent intent = new Intent(this, TablesActivity.class);
        startActivity(intent);
    }

    public void onSaveClick(View view) {
        if (name.getText().toString().equals("") || description.getText().toString().equals("")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Error");
            builder.setMessage("All fields must be filled");
            builder.setPositiveButton("OK", null);
            builder.show();
        } else {
            item.name = name.getText().toString();
            item.description = description.getText().toString();

            MainActivity.db.operationsTypesDao().insertAll(item);

            Intent intent = new Intent(this, TablesActivity.class);
            startActivity(intent);
        }
    }

    public void onDeleteClick(View view) {
        new AlertDialog.Builder(this)
                .setTitle("Delete operation type")
                .setMessage("Are you sure you want to delete this operation type?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    MainActivity.db.operationsTypesDao().delete(item);
                    Intent intent = new Intent(this, TablesActivity.class);
                    startActivity(intent);
                })
                .setNegativeButton("No", null)
                .show();
    }
}
