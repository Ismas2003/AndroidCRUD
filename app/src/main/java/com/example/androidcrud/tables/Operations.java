package com.example.androidcrud.tables;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "Operations", foreignKeys = {
        @ForeignKey(entity = OperationsTypes.class, parentColumns = "operationTypeId", childColumns = "operationTypeId"),
        @ForeignKey(entity = Doctors.class, parentColumns = "doctorId", childColumns = "doctorId")
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

    @ColumnInfo(name = "doctorId")
    public int doctorId;

    public Operations(String name, String description, int operationTypeId, int doctorId) {
        this.name = name;
        this.description = description;
        this.operationTypeId = operationTypeId;
        this.doctorId = doctorId;
    }
}
