package com.example.basadannih;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView captcha = findViewById(R.id.captcha);
        captcha.setText(generateCaptcha());

        Room.databaseBuilder(this, AppDatabase.class, "database.sqlite")
                .createFromAsset("database/identifier.sqlite")
                .build();
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
        TextView captcha = findViewById(R.id.captcha);
        captcha.setText(generateCaptcha());
    }

    public void onLoginClick(View view) {
    }
}