package com.example.androidcrud.tables;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface StaffDao {
    @Insert
    void insertAll(Staff... staff);

    @Delete
    void delete(Staff ... staff);

    @Query("SELECT *, first_name AS 'First name', last_name AS 'Last name', patronymic AS 'Patronymic', experience AS 'Experience' FROM staff")
    List<Staff> getAllForUser();

    @Query("SELECT *, first_name AS 'First name', last_name AS 'Last name', patronymic AS 'Patronymic', experience AS 'Experience', login AS 'Login', password AS 'Password' FROM staff")
    List<Staff> getAllForAdmin();

    @Query("SELECT * FROM staff WHERE login = :login AND password = :password")
    List<Staff> searchAccount(String login, String password);
}