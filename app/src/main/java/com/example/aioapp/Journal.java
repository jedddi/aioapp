package com.example.aioapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Journal extends AppCompatActivity {
    private Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal);

        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(v -> openDashboard());
    }
    public void openDashboard(){
        Intent intent = new Intent(this, Dashboard_activity.class);
        startActivity(intent);
    }
}