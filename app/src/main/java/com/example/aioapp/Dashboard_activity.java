package com.example.aioapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Calendar;

public class Dashboard_activity extends AppCompatActivity {

    private Button todo_btn;
    private Button finance_btn;
    private Button pomodoro_btn;
    private Button journal_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_activity);

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

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.home:
                        return true;
                    case R.id.notification:
                        startActivity(new Intent(getApplicationContext(),NotificationActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.settings:
                        startActivity(new Intent(getApplicationContext(),Settings.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

       /* CalendarView calendarView = findViewById(R.id.calendarView2);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                // Handle the selected date here
                String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                Toast.makeText(Dashboard_activity.this, "Selected date: " + selectedDate, Toast.LENGTH_SHORT).show();
            }
        });*/

    }

    private void setSingleEvent(GridLayout gridLayout) {
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            CardView cardView = (CardView) gridLayout.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(v -> {
                if (finalI==0){
                    Intent intent = new Intent(Dashboard_activity.this, Todolist.class);
                    startActivity(intent);
                }
                else if (finalI==1){
                    Intent intent = new Intent(Dashboard_activity.this, financetracker.class);
                    startActivity(intent);
                } else if (finalI==2) {
                    Intent intent = new Intent(Dashboard_activity.this, Pomodoro.class);
                    startActivity(intent);
                } else if(finalI==3) {
                    Intent intent = new Intent(Dashboard_activity.this, Journal.class);
                    startActivity(intent);
                }


            });
        }

    }

}