package com.example.aioapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.GridLayout;
import android.widget.Toast;

import com.example.aioapp.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

public class Dashboard extends AppCompatActivity {

    GridLayout gridLayout;
    FloatingActionButton fb1, fb2, fb3;
    Animation fabOpen, rotateBackward, rotateForward, fabClose;
    boolean isOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        setSingleEvent(gridLayout);

        fb1 = (FloatingActionButton) findViewById(R.id.fb1);
        fb2 = (FloatingActionButton) findViewById(R.id.fb2);
        fb3 = (FloatingActionButton) findViewById(R.id.fb3);

        //animations
        fabOpen = AnimationUtils.loadAnimation(this, R.anim.fab_open);
        fabClose = AnimationUtils.loadAnimation(this, R.anim.fab_close);

        rotateForward = AnimationUtils.loadAnimation(this, R.anim.rotate_forward);
        rotateBackward = AnimationUtils.loadAnimation(this, R.anim.rotate_backward);

        //set the click listener
        fb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animatefb1();
            }
        });
        fb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animatefb1();
                Toast.makeText(Dashboard.this, "create clicked", Toast.LENGTH_SHORT).show();

            }
        });
        fb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animatefb1();
                Toast.makeText(Dashboard.this, "finance clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void animatefb1(){
        if (isOpen){
            fb1.startAnimation(rotateForward);
            fb2.startAnimation(fabClose);
            fb3.startAnimation(fabClose);
            fb2.setClickable(false);
            isOpen=false;
        }
        else{
            fb1.startAnimation(rotateBackward);
            fb2.startAnimation(fabOpen);
            fb3.startAnimation(fabOpen);
            fb2.setClickable(true);
            fb3.setClickable(true);
            isOpen=true;
        }
    }
    private void setSingleEvent(GridLayout gridLayout) {
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            CardView cardView = (CardView) gridLayout.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(v -> {
                if (finalI==0){
                    Intent intent = new Intent(Dashboard.this, Todolist.class);
                    startActivity(intent);
                }
                else if (finalI==1){
                    Intent intent = new Intent(Dashboard.this, financetracker.class);
                    startActivity(intent);
                } else if (finalI==2) {
                    Intent intent = new Intent(Dashboard.this, Pomodoro.class);
                    startActivity(intent);
                } else if(finalI==3) {
                    Intent intent = new Intent(Dashboard.this, Journal.class);
                    startActivity(intent);
                }


            });
        }

    }

}