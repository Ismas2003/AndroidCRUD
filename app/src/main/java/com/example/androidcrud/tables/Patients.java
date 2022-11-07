package com.example.androidcrud.tables;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "Patients", foreignKeys = {
        @ForeignKey(entity = Wards.class, parentColumns = "wardId", childColumns = "wardId")
})
public class Patients {

    @PrimaryKey (autoGenerate = true)
    public int patientId;

    @ColumnInfo(name = "first_name")
    public String firstName;

    @ColumnInfo(name = "last_name")
    public String lastName;

    @ColumnInfo(name = "patronymic")
    public String patronymic;

    @ColumnInfo(name = "address")
    public String address;

    @ColumnInfo(name = "wardId")
    public int wardId;
}
