package com.example.aioapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Pomodoro extends AppCompatActivity {
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pomodoro);

        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(v -> openDashboard());
    }
    public void openDashboard(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}