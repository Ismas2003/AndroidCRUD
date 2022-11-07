package com.example.androidcrud.tables;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface OperationsTypesDao {
    @Insert
    void insertAll(OperationsTypes ... operationType);

    @Delete
    void delete(OperationsTypes operationType);

    @Query("SELECT *, name AS 'Name', description AS 'Description' FROM operations_types")
    List<OperationsTypes> getAll();
}
