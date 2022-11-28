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

        try {
            String tableName = getIntent().getStringExtra("table");

            switch (tableName) {
                case "doctors":
                    if (MainActivity.isAdmin) {
                        fillDoctorsAdminRecycler(db.doctorsDao().getAll());
                    } else {
                        fillDoctorsUserRecycler(db.doctorsDao().getAll());
                    }
                    setTitle("Doctors");
                    break;
                case "operations":
                    fillOperationsRecycler(db.operationsDao().getAll());
                    setTitle("Operations");
                    break;
                case "operations_types":
                    fillOperationsTypesRecycler(db.operationsTypesDao().getAll());
                    setTitle("Operations types");
                    break;
                case "patients":
                    fillPatientsRecycler(db.patientsDao().getAll());
                    setTitle("Patients");
                    break;
                case "wards":
                    fillWardsRecycler(db.wardsDao().getAll());
                    setTitle("Wards");
                    break;
            }

            noneTextView.setVisibility(View.INVISIBLE);
        }
        catch (Exception e) {
            return;
        }
    }

    private void search(String s) {
        if (getTitle().equals("Doctors")){
            List<Doctors> list = new ArrayList<>();
            for (Doctors items : db.doctorsDao().getAll()) {
                if (items.firstName.toLowerCase().contains(s.toLowerCase()) ||
                        items.lastName.toLowerCase().contains(s.toLowerCase())){
                    list.add(items);
                }
            }

            if (list.isEmpty()){
                Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
            }
            else {
                doctorsAdminAdapter.search(list);
            }
        }
        else if (getTitle().equals("Patients")){
            List<Patients> list = new ArrayList<>();
            for (Patients items : db.patientsDao().getAll()) {
                if (items.firstName.toLowerCase().contains(s.toLowerCase()) ||
                        items.lastName.toLowerCase().contains(s.toLowerCase())){
                    list.add(items);
                }
            }

            if (list.isEmpty()){
                Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
            }
            else {
                patientsAdapter.search(list);
            }
        }
        else if (getTitle().equals("Operations")){
            List<Operations> list = new ArrayList<>();
            for (Operations items : db.operationsDao().getAll()) {
                if (items.name.toLowerCase().contains(s.toLowerCase()) ||
                        String.valueOf(items.operationTypeId).contains(s)){
                    list.add(items);
                }
            }

            if (list.isEmpty()){
                Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
            }
            else {
                operationsAdapter.search(list);
            }
        }
        else if (getTitle().equals("Operations types")){
            List<OperationsTypes> list = new ArrayList<>();
            for (OperationsTypes items : db.operationsTypesDao().getAll()) {
                if (items.name.toLowerCase().contains(s.toLowerCase())) {
                    list.add(items);
                }
            }

            if (list.isEmpty()){
                Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
            }
            else {
                operationsTypesAdapter.search(list);
            }
        }
        else if (getTitle().equals("Wards")){
            List<Wards> list = new ArrayList<>();
            for (Wards items : db.wardsDao().getAll()) {
                if (String.valueOf(items.capacity).contains(s) ||
                        String.valueOf(items.doctorId).contains(s)){
                    list.add(items);
                }
            }

            if (list.isEmpty()){
                Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
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
        btnAdd = findViewById(R.id.btn_add);
        noneTextView.setVisibility(TextView.INVISIBLE);
        btnAdd.setVisibility(View.VISIBLE);
        searchView.setVisibility(View.VISIBLE);
        switch(id){
            case R.id.doctorsMenuItem:
                setTitle("Doctors");
                if (MainActivity.isAdmin) {
                    fillDoctorsAdminRecycler(db.doctorsDao().getAll());
                } else {
                    fillDoctorsUserRecycler(db.doctorsDao().getAll());
                    btnAdd.setVisibility(View.INVISIBLE);
                }
                return true;
            case R.id.operationsMenuItem:
                setTitle("Operations");
                fillOperationsRecycler(db.operationsDao().getAll());
                return true;
            case R.id.operationsTypesMenuItem:
                setTitle("Operations Types");
                fillOperationsTypesRecycler(db.operationsTypesDao().getAll());
                return true;
            case R.id.patientsMenuItem:
                setTitle("Patients");
                fillPatientsRecycler(db.patientsDao().getAll());
                return true;
            case R.id.wardsMenuItem:
                setTitle("Wards");
                fillWardsRecycler(db.wardsDao().getAll());
                return true;
            case R.id.noneMenuItem:
                setTitle("None");
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
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        doctorsAdminAdapter = new DoctorsAdminAdapter(this, list);
        recyclerView.setAdapter(doctorsAdminAdapter);
    }

    public void fillDoctorsUserRecycler(List<Doctors> list) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        doctorsUserAdapter = new DoctorsUserAdapter(this, list);
        recyclerView.setAdapter(doctorsUserAdapter);
    }

    public void fillOperationsRecycler(List<Operations> list) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        operationsAdapter = new OperationsAdapter(this, list);
        recyclerView.setAdapter(operationsAdapter);
    }

    public void fillOperationsTypesRecycler(List<OperationsTypes> list) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        operationsTypesAdapter = new OperationsTypesAdapter(this, list);
        recyclerView.setAdapter(operationsTypesAdapter);
    }

    public void fillPatientsRecycler(List<Patients> list) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        patientsAdapter = new PatientsAdapter(this, list);
        recyclerView.setAdapter(patientsAdapter);
    }

    public void fillWardsRecycler(List<Wards> list) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        wardsAdapter = new WardsAdapter(this, list);
        recyclerView.setAdapter(wardsAdapter);
    }

    public void onBackToMainClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Exit account");
        builder.setMessage("Are you sure you want to exit from account?");
        builder.setPositiveButton("Yes", (dialog, which) -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });
        builder.setNegativeButton("No", (dialog, which) -> dialog.cancel());
        builder.show();
    }

    public void onAddClick(View view) {
        if (getTitle().equals("Doctors")) {
            Intent intent = new Intent(this, DoctorsAeActivity.class);
            startActivity(intent);
        } else if (getTitle().equals("Operations")) {
            Intent intent = new Intent(this, OperationsAeActivity.class);
            startActivity(intent);
        } else if (getTitle().equals("Operations Types")) {
            Intent intent = new Intent(this, OperationsTypesAeActivity.class);
            startActivity(intent);
        } else if (getTitle().equals("Patients")) {
            Intent intent = new Intent(this, PatientsAeActivity.class);
            startActivity(intent);
        } else if (getTitle().equals("Wards")) {
            Intent intent = new Intent(this, WardsAeActivity.class);
            startActivity(intent);
        }
    }
}