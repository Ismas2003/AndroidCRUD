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
    void delete(Doctors doctor);

    @Query("SELECT *, first_name AS 'First name', last_name AS 'Last name', patronymic AS 'Patronymic', experience AS 'Experience' FROM doctors")
    List<Doctors> getAllForUser();

    @Query("SELECT *, first_name AS 'First name', last_name AS 'Last name', patronymic AS 'Patronymic', experience AS 'Experience', login AS 'Login', password AS 'Password' FROM doctors")
    List<Doctors> getAllForAdmin();

    @Query("SELECT * FROM doctors WHERE login = :login AND password = :password")
    Doctors searchAccount(String login, String password);
}