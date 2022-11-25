package com.example.androidcrud.tables;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface OperationsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Operations ... operation);

    @Delete
    void delete(Operations ... operation);

    @Query("SELECT *, name AS 'Name', description AS 'Description' FROM operations")
    List<Operations> getAll();
}
