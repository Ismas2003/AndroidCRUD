package com.example.androidcrud.tables;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "Operations", foreignKeys = {
        @ForeignKey(entity = OperationsTypes.class, parentColumns = "operationTypeId", childColumns = "operationTypeId")
})
public class Operations {

    @PrimaryKey (autoGenerate = true)
    public int operationId;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "operationTypeId")
    public int operationTypeId;
}
