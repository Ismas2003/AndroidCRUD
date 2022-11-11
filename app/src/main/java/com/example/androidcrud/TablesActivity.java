package com.example.androidcrud;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tables_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        TextView noneTextView = findViewById(R.id.noneTextView);
        noneTextView.setVisibility(TextView.INVISIBLE);
        switch(id){
            case R.id.doctorsMenuItem:
                setTitle("Doctors");
                return true;
            case R.id.operationsMenuItem:
                setTitle("Operations");
                return true;
            case R.id.operationsTypesMenuItem:
                setTitle("Operations Types");
                return true;
            case R.id.patientsMenuItem:
                setTitle("Patients");
                return true;
            case R.id.wardsMenuItem:
                setTitle("Wards");
                return true;
            case R.id.noneMenuItem:
                setTitle("None");
                noneTextView.setVisibility(TextView.VISIBLE);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}