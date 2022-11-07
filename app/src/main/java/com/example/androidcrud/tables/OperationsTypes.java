package com.example.androidcrud.tables;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Operations_types")
public class OperationsTypes {

    @PrimaryKey (autoGenerate = true)
    public int operationTypeId;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "description")
    public String description;

    public OperationsTypes(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
