package com.example.aioapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class financetracker extends AppCompatActivity {
    private Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_financetracker);

        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(v -> openDashboard());
    }
    public void openDashboard(){
        Intent intent = new Intent(this, Dashboard_activity.class);
        startActivity(intent);
    }
}