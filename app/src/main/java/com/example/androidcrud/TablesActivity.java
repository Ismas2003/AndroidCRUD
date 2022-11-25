package com.example.androidcrud;

import static com.example.androidcrud.MainActivity.db;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.example.androidcrud.adapters.DoctorsAdminAdapter;
import com.example.androidcrud.adapters.DoctorsUserAdapter;
import com.example.androidcrud.addEditActivities.DoctorsAeActivity;
import com.example.androidcrud.tables.Doctors;

import java.util.ArrayList;
import java.util.List;

public class TablesActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DoctorsAdminAdapter doctorsAdminAdapter;
    DoctorsUserAdapter doctorsUserAdapter;
    View footer;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tables);

        footer = findViewById(R.id.footerLinearLayout);
        searchView = findViewById(R.id.searchView);

        if (MainActivity.isAdmin) {
            setTitle("Admin");
        } else {
            setTitle("User");
        }

        recyclerView = findViewById(R.id.recycler_view);

        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                search(s);
                return false;
            }
        });
    }

    private void search(String s) {
        List<Doctors> doctorsList = new ArrayList<>();
        for (Doctors doctors : db.doctorsDao().getAll()) {
            if (doctors.firstName.toLowerCase().contains(s.toLowerCase()) ||
                    doctors.lastName.toLowerCase().contains(s.toLowerCase())){
                doctorsList.add(doctors);
            }
        }

        if (doctorsList.isEmpty()){
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
        }
        else {
            doctorsAdminAdapter.search(doctorsList);
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
        footer.setVisibility(View.VISIBLE);
        searchView.setVisibility(View.VISIBLE);
        switch(id){
            case R.id.doctorsMenuItem:
                setTitle("Doctors");
                if (MainActivity.isAdmin) {
                    fillDoctorsAdminRecycler(db.doctorsDao().getAll());
                } else {
                    fillDoctorsUserRecycler(db.doctorsDao().getAll());
                }
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
                clearRecyclerView();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void clearRecyclerView() {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setAdapter(null);
    }

    private void fillDoctorsAdminRecycler(List<Doctors> list) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        doctorsAdminAdapter = new DoctorsAdminAdapter(this, list);
        recyclerView.setAdapter(doctorsAdminAdapter);
    }

    private void fillDoctorsUserRecycler(List<Doctors> list) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        doctorsUserAdapter = new DoctorsUserAdapter(this, list);
        recyclerView.setAdapter(doctorsUserAdapter);
    }

    public void onBackToMainClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onAddClick(View view) {
        Intent intent = new Intent(this, DoctorsAeActivity.class);
        startActivity(intent);
    }
}