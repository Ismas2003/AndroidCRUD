package com.example.androidcrud.tables;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "Wards", foreignKeys = {
        @ForeignKey(entity = Doctors.class, parentColumns = "doctorId", childColumns = "doctorId", onDelete = ForeignKey.CASCADE)
})
public class Wards {

    @PrimaryKey (autoGenerate = true)
    public int wardId;

    @ColumnInfo(name = "capacity")
    public int capacity;

    @ColumnInfo(name = "doctorId")
    public int doctorId;

    public Wards(int capacity, int doctorId) {
        this.capacity = capacity;
        this.doctorId = doctorId;
    }
}
