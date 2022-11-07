package com.example.androidcrud.tables;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface WardsDao {
    @Insert
    void insertAll(Wards ... ward);

    @Delete
    void delete(Wards ward);

    @Query("SELECT *, capacity AS 'Capacity' FROM wards")
    List<Wards> getAll();
}
