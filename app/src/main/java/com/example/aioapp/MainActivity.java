package com.example.aioapp;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aioapp.Adapter.todoadapter;
import com.example.aioapp.Model.todomodel;
import com.example.aioapp.utils.databasehandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private todoadapter taskAdapter;
    private List<todomodel> taskList;
    private databasehandler db;
    private RecyclerView tasksRecyclerView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        String firstopen = pref.getString("firstopen", "");

        if(firstopen.equals("Yes")){
            startActivity(new Intent(MainActivity.this, Dashboard_activity.class));
        } else {
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("firstopen", "Yes");
            editor.apply();
        }

        // Create the scale up animation
        ObjectAnimator scaleUpX = ObjectAnimator.ofFloat(imageView, "scaleX", 1f, 1.2f);
        scaleUpX.setDuration(5000);
        scaleUpX.setInterpolator(new AccelerateDecelerateInterpolator());
        scaleUpX.setRepeatCount(ObjectAnimator.INFINITE);
        scaleUpX.setRepeatMode(ObjectAnimator.REVERSE);

        ObjectAnimator scaleUpY = ObjectAnimator.ofFloat(imageView, "scaleY", 1f, 1.2f);
        scaleUpY.setDuration(5000);
        scaleUpY.setInterpolator(new AccelerateDecelerateInterpolator());
        scaleUpY.setRepeatCount(ObjectAnimator.INFINITE);
        scaleUpY.setRepeatMode(ObjectAnimator.REVERSE);

        // Create the fade in animation
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(imageView, "alpha", 0f, 1f);
        fadeIn.setDuration(5000);
        fadeIn.setInterpolator(new AccelerateDecelerateInterpolator());
        fadeIn.setRepeatCount(ObjectAnimator.INFINITE);
        fadeIn.setRepeatMode(ObjectAnimator.REVERSE);

        // Combine the scale up and fade in animations into a set
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(scaleUpX, scaleUpY, fadeIn);

        // Start the animation
        animatorSet.start();

        ConstraintLayout constraintLayout = findViewById(R.id.relativeLayout);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(3500);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();

        Button button = findViewById(R.id.button);
        button.setOnClickListener(v -> openDashboard());

    }



    public void openDashboard() {
        Intent intent = new Intent(this, Dashboard_activity.class);
        startActivity(intent);
    }

}
