package com.example.aioapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Settings extends AppCompatActivity {

    private ImageButton sendFeedbackButton;
    private ImageButton backButton;
    private ImageButton goButtonau;
    private ImageButton goButtonAa;

    private ImageView messageIcon;
    private TextView sendFeedbackText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_page);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        // Handle home item click
                        startActivity(new Intent(getApplicationContext(), Dashboard_activity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.notification:
                        // Handle notification item click
                        startActivity(new Intent(getApplicationContext(), NotificationActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.settings:
                        // Handle settings item click
                        return true;
                }
                return false;
            }
        });

        backButton = findViewById(R.id.returnDash);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back_Dashboard = new Intent(Settings.this, Dashboard_activity.class);
                startActivity(back_Dashboard);
            }
        });

        goButtonau = findViewById(R.id.buttonAu);
        goButtonau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go_AboutUs = new Intent(Settings.this, AboutUs.class);
                startActivity(go_AboutUs);
            }
        });

        goButtonAa = findViewById(R.id.bottonAa);
        goButtonAa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go_AboutAa = new Intent(Settings.this, AboutApp.class);
                startActivity(go_AboutAa);
            }
        });


        sendFeedbackButton = findViewById(R.id.sendFeedbackButton);
        sendFeedbackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendFeedback();
            }
        });

        messageIcon = findViewById(R.id.messageIcon);
        sendFeedbackText = findViewById(R.id.sendFeedbackText);

        messageIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendFeedback();
            }
        });

        sendFeedbackText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendFeedback();
            }
        });
    }

    private void sendFeedback() {
        String subject = "Feedback";
        String body = "Please provide your feedback here.";

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:cyber6suprt@gmail.com"));
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, body);
        startActivity(Intent.createChooser(intent, "Send Email"));
    }
}
