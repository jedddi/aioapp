package com.example.aioapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Dashboard_activity extends AppCompatActivity {

    private Button todo_btn;
    private Button finance_btn;
    private Button pomodoro_btn;
    private Button journal_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_activity);

//Tools Button Code
        ImageButton todo_btn = findViewById(R.id.todolist_btn);
        todo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent opentodo = new Intent(Dashboard_activity.this, Todolist.class);
                startActivity(opentodo);
            }
        });
        ImageButton finance_btn = findViewById(R.id.finance_btn);
        finance_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openfinance = new Intent(Dashboard_activity.this, financetracker.class);
                startActivity(openfinance);
            }
        });
        ImageButton pomodoro_btn = findViewById(R.id.pomodoro_btn);
        pomodoro_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openpomodoro = new Intent(Dashboard_activity.this, Pomodoro.class);
                startActivity(openpomodoro);
            }
        });
        ImageButton journal_btn = findViewById(R.id.journal_btn);
        journal_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openjournal = new Intent(Dashboard_activity.this, Journal.class);
                startActivity(openjournal);
            }
        });


    }

}