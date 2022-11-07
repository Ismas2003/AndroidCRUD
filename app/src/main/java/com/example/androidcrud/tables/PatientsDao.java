package com.example.androidcrud.tables;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PatientsDao {
    @Insert
    void insertAll(Patients ... patient);

    @Delete
    void delete(Patients patient);

    @Query("SELECT *, first_name AS 'First name', last_name AS 'Last name', patronymic AS 'Patronymic', address AS 'Address' FROM patients")
    List<Patients> getAll();
}