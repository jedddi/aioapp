package com.example.aioapp;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();

        Button button = findViewById(R.id.button);
        button.setOnClickListener(v -> openDashboard());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bottom_nav_menu, menu);
        return true;
    }


    public void openDashboard() {
        Intent intent = new Intent(this, Dashboard_activity.class);
        startActivity(intent);
    }
}
