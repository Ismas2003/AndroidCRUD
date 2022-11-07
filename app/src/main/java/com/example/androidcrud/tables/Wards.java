package com.example.androidcrud.tables;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Wards")
public class Wards {

    @PrimaryKey (autoGenerate = true)
    public int wardId;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "description")
    public String description;
}
