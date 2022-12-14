package com.example.androidcrud;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.androidcrud.tables.Doctors;
import com.example.androidcrud.tables.DoctorsDao;
import com.example.androidcrud.tables.Operations;
import com.example.androidcrud.tables.OperationsDao;
import com.example.androidcrud.tables.OperationsTypes;
import com.example.androidcrud.tables.OperationsTypesDao;
import com.example.androidcrud.tables.Patients;
import com.example.androidcrud.tables.PatientsDao;
import com.example.androidcrud.tables.Wards;
import com.example.androidcrud.tables.WardsDao;

@Database(entities = {Doctors.class, Operations.class, OperationsTypes.class, Patients.class, Wards.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DoctorsDao doctorsDao();
    public abstract OperationsDao operationsDao();
    public abstract OperationsTypesDao operationsTypesDao();
    public abstract PatientsDao patientsDao();
    public abstract WardsDao wardsDao();
}