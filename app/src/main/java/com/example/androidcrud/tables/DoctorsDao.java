package com.example.androidcrud.tables;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DoctorsDao {
    @Insert
    void insertAll(Doctors ... doctor);

    @Delete
    void delete(Doctors ... doctor);

    @Query("SELECT * FROM doctors")
    List<Doctors> getAll();

    @Query("SELECT * FROM doctors WHERE login = :login AND password = :password")
    List<Doctors> searchAccount(String login, String password);
}