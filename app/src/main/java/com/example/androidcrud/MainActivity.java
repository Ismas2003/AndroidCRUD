package com.example.androidcrud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidcrud.tables.Doctors;
import com.example.androidcrud.tables.DoctorsDao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public class MainActivity extends AppCompatActivity {

    AppDatabase db;
    TextView captcha;
    DoctorsDao doctorDao;
    static boolean isAdmin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        captcha = findViewById(R.id.captcha);
        captcha.setText(generateCaptcha());

        Room.databaseBuilder(this, AppDatabase.class, "database.sqlite")
                .createFromAsset("database/identifier.sqlite")
                .build();

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "identifier").build();

        doctorDao = db.doctorsDao();
    }

    public static String generateCaptcha() {
        String captcha = "";
        try {
            String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
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

    public void onLoginClick(View view) {
        TextView captchaInput = findViewById(R.id.captchaInput);
        TextView loginTextView = findViewById(R.id.editTextTextPersonName);
        TextView passwordTextView = findViewById(R.id.editTextTextPassword);
        String login = loginTextView.getText().toString();
        String password = sha512(passwordTextView.getText().toString()).toString();
        List<Doctors> doctor = db.doctorsDao().searchAccount(login, password);

        if (doctor != null) {
            if (captchaInput.getText().toString().equals(captcha.getText().toString())) {
               // if (doctor.equals("admin")) {
                    //isAdmin = true;
               // } else {
                   // isAdmin = false;
               // }
                Intent intent = new Intent(this, TablesActivity.class);
                startActivity(intent);
            } else {
                Toast toast = Toast.makeText (getApplicationContext(),
                        "Wrong captcha", Toast.LENGTH_SHORT);
                toast.show();
            }
        } else {
            Toast toast = Toast.makeText (getApplicationContext(),
                    "Wrong login or password", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private StringBuilder sha512(String text) {
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
}