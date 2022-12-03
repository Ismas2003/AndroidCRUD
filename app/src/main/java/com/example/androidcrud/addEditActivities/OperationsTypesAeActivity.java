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
        intent.putExtra("table", "operations_types");
        startActivity(intent);
    }

    public void onSaveClick(View view) {
        if (name.getText().toString().equals("") || description.getText().toString().equals("")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.error);
            builder.setMessage(R.string.not_filled_fields);
            builder.setPositiveButton(R.string.ok, null);
            builder.show();
        } else {
            item.name = name.getText().toString();
            item.description = description.getText().toString();

            MainActivity.db.operationsTypesDao().insertAll(item);

            Intent intent = new Intent(this, TablesActivity.class);
            intent.putExtra("table", "operations_types");
            startActivity(intent);
        }
    }

    public void onDeleteClick(View view) {
        new AlertDialog.Builder(this)
                .setTitle(R.string.delete_ot)
                .setMessage(R.string.delete_ot_msg)
                .setPositiveButton(R.string.yes, (dialog, which) -> {
                    MainActivity.db.operationsTypesDao().delete(item);
                    Intent intent = new Intent(this, TablesActivity.class);
                    intent.putExtra("table", "operations_types");
                    startActivity(intent);
                })
                .setNegativeButton(R.string.no, null)
                .show();
    }
}
