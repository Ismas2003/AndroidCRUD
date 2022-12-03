package com.example.androidcrud;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.androidcrud.tables.Doctors;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static AppDatabase db;
    TextView captcha;
    static boolean isAdmin = false;

    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        captcha = findViewById(R.id.captcha);
        captcha.setText(generateCaptcha());

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database").allowMainThreadQueries().build();
    }

    public static String generateCaptcha() {
        String captcha = "";
        try {
            String letters = "ABCDEFGHJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
            for (int i = 0; i < 5; i++) {
                captcha += letters.charAt((int) (Math.random() * letters.length()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return captcha;
    }

    public void onGenerateClick(View view) {
        captcha.setText(generateCaptcha());
    }

    public static StringBuilder sha512(String text) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] digest = md.digest(text.getBytes());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < digest.length; i++) {
            sb.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb;
    }

    public void onLoginClick(View view) {
        TextView captchaInput = findViewById(R.id.captchaInput);
        TextView loginTextView = findViewById(R.id.txtLogin);
        TextView passwordTextView = findViewById(R.id.txtPassword);
        String login = loginTextView.getText().toString();
        String password = sha512(passwordTextView.getText().toString()).toString();
        List<Doctors> doctor = db.doctorsDao().searchAccount(login, password);

        if (captchaInput.getText().toString().equals(captcha.getText().toString())) {
            if (doctor.size() > 0) {
                isAdmin = doctor.get(0).login.equals("admin");
                Intent intent = new Intent(this, TablesActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Wrong login or password", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Wrong captcha", Toast.LENGTH_SHORT).show();
            captcha.setText(generateCaptcha());
        }
    }

    public void resetDatabase(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Reset database");
        builder.setMessage("Are you sure you want to reset database?");
        builder.setPositiveButton("Yes", (dialog, which) -> {
            db.clearAllTables();
            FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(getApplicationContext());
            mDbHelper.getWritableDatabase().execSQL("DELETE FROM sqlite_sequence WHERE name='Doctors';");
            mDbHelper.getWritableDatabase().execSQL("DELETE FROM sqlite_sequence WHERE name='Operations_types';");
            mDbHelper.getWritableDatabase().execSQL("DELETE FROM sqlite_sequence WHERE name='Patients';");
            mDbHelper.getWritableDatabase().execSQL("DELETE FROM sqlite_sequence WHERE name='Wards';");
            mDbHelper.getWritableDatabase().execSQL("DELETE FROM sqlite_sequence WHERE name='Operations';");
            TablesData tablesData = new TablesData();
            tablesData.fillTables();
            Toast.makeText(this, "Database reset", Toast.LENGTH_SHORT).show();
        });
        builder.setNegativeButton("No", (dialog, which) -> dialog.cancel());
        builder.show();
    }
}