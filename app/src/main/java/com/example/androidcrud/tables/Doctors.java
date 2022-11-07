package com.example.androidcrud.tables;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Doctors")
public class Doctors {

    @PrimaryKey (autoGenerate = true)
    public int doctorId;

    @ColumnInfo(name = "first_name")
    public String firstName;

    @ColumnInfo(name = "last_name")
    public String lastName;

    @ColumnInfo(name = "patronymic")
    public String patronymic;

    @ColumnInfo(name = "experience")
    public int experience;

    @ColumnInfo(name = "login")
    public String login;

    @ColumnInfo(name = "password")
    public String password;

    public Doctors(String firstName, String lastName, String patronymic, int experience, String login, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.experience = experience;
        this.login = login;
        this.password = password;
    }
}
