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
        intent.putExtra("table", "operations");
        startActivity(intent);
    }

    public void onSaveClick(View view) {
        if (name.getText().toString().equals("") || description.getText().toString().equals("") ||
                operationTypeId.getText().toString().equals("") ||
                doctorId.getText().toString().equals("")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.error);
            builder.setMessage(R.string.not_filled_fields);
            builder.setPositiveButton(R.string.ok, null);
            builder.show();
        } else {
            item.name = name.getText().toString();
            item.description = description.getText().toString();
            try {
                item.operationTypeId = Integer.parseInt(operationTypeId.getText().toString());
                item.doctorId = Integer.parseInt(doctorId.getText().toString());
                if (MainActivity.db.doctorsDao().getAll().get(item.doctorId - 1) == null ||
                        MainActivity.db.operationsTypesDao().getAll().get(item.operationTypeId - 1) == null) {
                    throw new Exception();
                }
            } catch(Exception e) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.error);
                builder.setMessage(R.string.not_number_ot_id_and_d_id);
                builder.setPositiveButton("OK", null);
                builder.show();
                return;
            }

            MainActivity.db.operationsDao().insertAll(item);

            Intent intent = new Intent(this, TablesActivity.class);
            intent.putExtra("table", "operations");
            startActivity(intent);
        }
    }

    public void onDeleteClick(View view) {
        new AlertDialog.Builder(this)
                .setTitle(R.string.delete_operation)
                .setMessage(R.string.delete_operation_msg)
                .setPositiveButton(R.string.yes, (dialog, which) -> {
                    MainActivity.db.operationsDao().delete(item);
                    Intent intent = new Intent(this, TablesActivity.class);
                    intent.putExtra("table", "operations");
                    startActivity(intent);
                })
                .setNegativeButton(R.string.no, null)
                .show();
    }
}
