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
import com.example.androidcrud.tables.Wards;

import java.util.List;


public class WardsAeActivity extends AppCompatActivity {
    TextView capacity, doctorId;

    Wards item;
    List<Wards> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wards_ae);

        list = MainActivity.db.wardsDao().getAll();

        capacity = findViewById(R.id.input_capacity);
        doctorId = findViewById(R.id.input_doctor_id);

        if (getIntent().hasExtra("id")) {
            item = list.get(getIntent().getExtras().getInt("id"));

            capacity.setText(String.valueOf(item.capacity));
            doctorId.setText(String.valueOf(item.doctorId));
        } else {
            item = new Wards(0, 0);
        }
    }

    public void onBackToTablesClick(View view) {
        Intent intent = new Intent(this, TablesActivity.class);
        intent.putExtra("table", "wards");
        startActivity(intent);
    }

    public void onSaveClick(View view) {
        if (capacity.getText().toString().equals("") || doctorId.getText().toString().equals("")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.error);
            builder.setMessage(R.string.not_filled_fields);
            builder.setPositiveButton(R.string.ok, null);
            builder.show();
        } else {
            try {
                item.capacity = Integer.parseInt(capacity.getText().toString());
                item.doctorId = Integer.parseInt(doctorId.getText().toString());
                if (item.capacity < 0) {
                    throw new Exception();
                }
                if (MainActivity.db.doctorsDao().getAll().get(item.doctorId - 1) == null) {
                    throw new Exception();
                }
            }
            catch (Exception e) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.error);
                builder.setMessage(R.string.not_number_capacity_and_w_id);
                builder.setPositiveButton(R.string.ok, null);
                builder.show();
                return;
            }

            MainActivity.db.wardsDao().insertAll(item);

            Intent intent = new Intent(this, TablesActivity.class);
            intent.putExtra("table", "wards");
            startActivity(intent);
        }
    }

    public void onDeleteClick(View view) {
        new AlertDialog.Builder(this)
                .setTitle(R.string.delete_ward)
                .setMessage(R.string.delete_ward_msg)
                .setPositiveButton(R.string.yes, (dialog, which) -> {
                    MainActivity.db.wardsDao().delete(item);
                    Intent intent = new Intent(this, TablesActivity.class);
                    intent.putExtra("table", "wards");
                    startActivity(intent);
                })
                .setNegativeButton(R.string.no, null)
                .show();
    }
}
