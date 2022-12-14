package com.example.androidcrud;

import static com.example.androidcrud.MainActivity.db;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
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
import com.example.androidcrud.adapters.OperationsAdapter;
import com.example.androidcrud.adapters.OperationsTypesAdapter;
import com.example.androidcrud.adapters.PatientsAdapter;
import com.example.androidcrud.adapters.WardsAdapter;
import com.example.androidcrud.addEditActivities.DoctorsAeActivity;
import com.example.androidcrud.addEditActivities.OperationsAeActivity;
import com.example.androidcrud.addEditActivities.OperationsTypesAeActivity;
import com.example.androidcrud.addEditActivities.PatientsAeActivity;
import com.example.androidcrud.addEditActivities.WardsAeActivity;
import com.example.androidcrud.tables.Doctors;
import com.example.androidcrud.tables.Operations;
import com.example.androidcrud.tables.OperationsTypes;
import com.example.androidcrud.tables.Patients;
import com.example.androidcrud.tables.Wards;

import java.util.ArrayList;
import java.util.List;

public class TablesActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DoctorsAdminAdapter doctorsAdminAdapter;
    DoctorsUserAdapter doctorsUserAdapter;
    OperationsAdapter operationsAdapter;
    OperationsTypesAdapter operationsTypesAdapter;
    PatientsAdapter patientsAdapter;
    WardsAdapter wardsAdapter;
    View btnAdd;
    SearchView searchView;
    TextView noneTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tables);

        searchView = findViewById(R.id.searchView);
        noneTextView = findViewById(R.id.noneTextView);
        btnAdd = findViewById(R.id.btn_add);

        if (MainActivity.isAdmin) {
            setTitle(getString(R.string.admin));
        } else {
            setTitle(getString(R.string.user));
        }

        recyclerView = findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

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

        try {
            String tableName = getIntent().getStringExtra("table");

            switch (tableName) {
                case "doctors":
                    if (MainActivity.isAdmin) {
                        fillDoctorsAdminRecycler(db.doctorsDao().getAll());
                    } else {
                        fillDoctorsUserRecycler(db.doctorsDao().getAll());
                    }
                    setTitle(R.string.doctors);
                    break;
                case "operations":
                    fillOperationsRecycler(db.operationsDao().getAll());
                    setTitle(R.string.operations);
                    break;
                case "operations_types":
                    fillOperationsTypesRecycler(db.operationsTypesDao().getAll());
                    setTitle(R.string.operation_types);
                    break;
                case "patients":
                    fillPatientsRecycler(db.patientsDao().getAll());
                    setTitle(R.string.patients);
                    break;
                case "wards":
                    fillWardsRecycler(db.wardsDao().getAll());
                    setTitle(R.string.wards);
                    break;
            }
            searchView.setVisibility(View.VISIBLE);
            btnAdd.setVisibility(View.VISIBLE);
            noneTextView.setVisibility(View.INVISIBLE);
        }
        catch (Exception e) {
            return;
        }
    }

    private void search(String s) {
        if (getTitle().equals(getString(R.string.doctors))) {
            List<Doctors> list = new ArrayList<>();
            for (Doctors items : db.doctorsDao().getAll()) {
                if (items.firstName.toLowerCase().contains(s.toLowerCase()) ||
                        items.lastName.toLowerCase().contains(s.toLowerCase())){
                    list.add(items);
                }
            }

            if (list.isEmpty()){
                Toast.makeText(this, getString(R.string.no_data_found), Toast.LENGTH_SHORT).show();
            }
            else {
                doctorsAdminAdapter.search(list);
            }
        }
        else if (getTitle().equals(getString(R.string.operations))) {
            List<Patients> list = new ArrayList<>();
            for (Patients items : db.patientsDao().getAll()) {
                if (items.firstName.toLowerCase().contains(s.toLowerCase()) ||
                        items.lastName.toLowerCase().contains(s.toLowerCase())){
                    list.add(items);
                }
            }

            if (list.isEmpty()){
                Toast.makeText(this, getString(R.string.no_data_found), Toast.LENGTH_SHORT).show();
            }
            else {
                patientsAdapter.search(list);
            }
        }
        else if (getTitle().equals(getString(R.string.operation_types))) {
            List<Operations> list = new ArrayList<>();
            for (Operations items : db.operationsDao().getAll()) {
                if (items.name.toLowerCase().contains(s.toLowerCase()) ||
                        String.valueOf(items.operationTypeId).contains(s)){
                    list.add(items);
                }
            }

            if (list.isEmpty()){
                Toast.makeText(this, getString(R.string.no_data_found), Toast.LENGTH_SHORT).show();
            }
            else {
                operationsAdapter.search(list);
            }
        }
        else if (getTitle().equals(getString(R.string.patients))) {
            List<OperationsTypes> list = new ArrayList<>();
            for (OperationsTypes items : db.operationsTypesDao().getAll()) {
                if (items.name.toLowerCase().contains(s.toLowerCase())) {
                    list.add(items);
                }
            }

            if (list.isEmpty()){
                Toast.makeText(this, getString(R.string.no_data_found), Toast.LENGTH_SHORT).show();
            }
            else {
                operationsTypesAdapter.search(list);
            }
        }
        else if (getTitle().equals(getString(R.string.wards))) {
            List<Wards> list = new ArrayList<>();
            for (Wards items : db.wardsDao().getAll()) {
                if (String.valueOf(items.capacity).contains(s) ||
                        String.valueOf(items.doctorId).contains(s)){
                    list.add(items);
                }
            }

            if (list.isEmpty()){
                Toast.makeText(this, getString(R.string.no_data_found), Toast.LENGTH_SHORT).show();
            }
            else {
                wardsAdapter.search(list);
            }
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
        noneTextView.setVisibility(TextView.INVISIBLE);
        btnAdd.setVisibility(View.VISIBLE);
        searchView.setVisibility(View.VISIBLE);
        switch(id){
            case R.id.doctorsMenuItem:
                setTitle(R.string.doctors);
                if (MainActivity.isAdmin) {
                    fillDoctorsAdminRecycler(db.doctorsDao().getAll());
                } else {
                    fillDoctorsUserRecycler(db.doctorsDao().getAll());
                    btnAdd.setVisibility(View.INVISIBLE);
                }
                return true;
            case R.id.operationsMenuItem:
                setTitle(R.string.operations);
                fillOperationsRecycler(db.operationsDao().getAll());
                return true;
            case R.id.operationsTypesMenuItem:
                setTitle(R.string.operation_types);
                fillOperationsTypesRecycler(db.operationsTypesDao().getAll());
                return true;
            case R.id.patientsMenuItem:
                setTitle(R.string.patients);
                fillPatientsRecycler(db.patientsDao().getAll());
                return true;
            case R.id.wardsMenuItem:
                setTitle(R.string.wards);
                fillWardsRecycler(db.wardsDao().getAll());
                return true;
            case R.id.noneMenuItem:
                if (MainActivity.isAdmin) setTitle(getString(R.string.admin));
                else setTitle(getString(R.string.user));
                noneTextView.setVisibility(TextView.VISIBLE);
                clearRecyclerView();
                btnAdd.setVisibility(View.INVISIBLE);
                searchView.setVisibility(View.INVISIBLE);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void clearRecyclerView() {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setAdapter(null);
    }

    public void fillDoctorsAdminRecycler(List<Doctors> list) {
        doctorsAdminAdapter = new DoctorsAdminAdapter(this, list);
        recyclerView.setAdapter(doctorsAdminAdapter);
    }

    public void fillDoctorsUserRecycler(List<Doctors> list) {
        doctorsUserAdapter = new DoctorsUserAdapter(this, list);
        recyclerView.setAdapter(doctorsUserAdapter);
    }

    public void fillOperationsRecycler(List<Operations> list) {
        operationsAdapter = new OperationsAdapter(this, list);
        recyclerView.setAdapter(operationsAdapter);
    }

    public void fillOperationsTypesRecycler(List<OperationsTypes> list) {
        operationsTypesAdapter = new OperationsTypesAdapter(this, list);
        recyclerView.setAdapter(operationsTypesAdapter);
    }

    public void fillPatientsRecycler(List<Patients> list) {
        patientsAdapter = new PatientsAdapter(this, list);
        recyclerView.setAdapter(patientsAdapter);
    }

    public void fillWardsRecycler(List<Wards> list) {
        wardsAdapter = new WardsAdapter(this, list);
        recyclerView.setAdapter(wardsAdapter);
    }

    public void onBackToMainClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.exit_account);
        builder.setMessage(R.string.exit_account_msg);
        builder.setPositiveButton(R.string.yes, (dialog, which) -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });
        builder.setNegativeButton(R.string.no, (dialog, which) -> dialog.cancel());
        builder.show();
    }

    public void onAddClick(View view) {
        if (getTitle().equals(R.string.doctors)) {
            Intent intent = new Intent(this, DoctorsAeActivity.class);
            startActivity(intent);
        } else if (getTitle().equals(R.string.operations)) {
            Intent intent = new Intent(this, OperationsAeActivity.class);
            startActivity(intent);
        } else if (getTitle().equals(R.string.operation_types)) {
            Intent intent = new Intent(this, OperationsTypesAeActivity.class);
            startActivity(intent);
        } else if (getTitle().equals(R.string.patients)) {
            Intent intent = new Intent(this, PatientsAeActivity.class);
            startActivity(intent);
        } else if (getTitle().equals(R.string.wards)) {
            Intent intent = new Intent(this, WardsAeActivity.class);
            startActivity(intent);
        }
    }
}