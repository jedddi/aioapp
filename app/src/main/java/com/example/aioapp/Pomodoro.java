package com.example.aioapp;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Locale;

public class Pomodoro extends AppCompatActivity {
    private TextView clockTextView;
    private EditText minutesEditText;
    private Button startButton;
    private Button pauseResumeButton;
    private Button stopButton;
    private Button default25Button;
    private Button default5Button;

    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;
    private boolean isTimerRunning;
    private long pausedTimeInMillis;

    private boolean defaultButtonClicked = false;

    private MediaPlayer mediaPlayer;
    private ImageView imageView;
    private Button backButton;
    private boolean startBreakAutomatically = false;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pomodoro);

        ImageButton backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back_Dashboard = new Intent(Pomodoro.this, Dashboard_activity.class);
                startActivity(back_Dashboard);
            }
        });

        imageView = findViewById(R.id.imageView);

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

        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(imageView, "alpha", 0f, 1f);
        fadeIn.setDuration(5000);
        fadeIn.setInterpolator(new AccelerateDecelerateInterpolator());
        fadeIn.setRepeatCount(ObjectAnimator.INFINITE);
        fadeIn.setRepeatMode(ObjectAnimator.REVERSE);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(scaleUpX, scaleUpY, fadeIn);

        animatorSet.start();

        ConstraintLayout constraintLayout = findViewById(R.id.pomodoro);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(3500);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();

        clockTextView = findViewById(R.id.clockTextView);
        minutesEditText = findViewById(R.id.minutesEditText);
        startButton = findViewById(R.id.startButton);
        pauseResumeButton = findViewById(R.id.pauseResumeButton);
        stopButton = findViewById(R.id.stopButton);
        default25Button = findViewById(R.id.default25Button);
        default5Button = findViewById(R.id.default5Button);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startBreakAutomatically = false;
                startTimer();
            }
        });

        pauseResumeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTimerRunning) {
                    pauseTimer();
                } else {
                    resumeTimer();
                }
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopTimer();
            }
        });

        default25Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDefaultTimer(25);
                startButton.setEnabled(true);
                defaultButtonClicked = true;
                startBreakAutomatically = true;
                startTimer();
            }
        });

        default5Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDefaultTimer(5);
            }
        });
    }

    private void setDefaultTimer(int minutes) {
        minutesEditText.setText(String.valueOf(minutes));
    }

    private void startTimer() {
        String minutesInput = minutesEditText.getText().toString().trim();
        if (minutesInput.isEmpty()) {
            return;
        }

        long millisInput = Long.parseLong(minutesInput) * 60000;
        if (millisInput == 0) {
            return;
        }

        timeLeftInMillis = millisInput;
        minutesEditText.setText("");
        startButton.setEnabled(false);
        pauseResumeButton.setEnabled(true);
        pauseResumeButton.setText("Pause");
        stopButton.setEnabled(true);
        default25Button.setEnabled(false);
        default5Button.setEnabled(false);
        minutesEditText.setEnabled(false);

        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateClockText();
            }

            @Override
            public void onFinish() {
                isTimerRunning = false;
                updateButtonState();
                playEndSound();

                if (startBreakAutomatically && defaultButtonClicked) {
                    startBreakAutomatically = false; // Reset the flag
                    startTimerWithBreak(); // Start the break timer automatically
                }
            }
        }.start();

        isTimerRunning = true;
        updateButtonState();
    }


    private void pauseTimer() {
        countDownTimer.cancel();
        isTimerRunning = false;
        pausedTimeInMillis = timeLeftInMillis;
        updateButtonState();
    }

    private void resumeTimer() {
        startTimerWithTime(pausedTimeInMillis);
    }

    private void stopTimer() {
        countDownTimer.cancel();
        isTimerRunning = false;
        timeLeftInMillis = 0;
        updateClockText();
        updateButtonState();
    }

    private void startTimerWithTime(long timeInMillis) {
        timeLeftInMillis = timeInMillis;
        startButton.setEnabled(false);
        pauseResumeButton.setEnabled(true);
        pauseResumeButton.setText("Pause");
        stopButton.setEnabled(true);
        default25Button.setEnabled(false);
        default5Button.setEnabled(false);
        minutesEditText.setEnabled(false);

        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateClockText();
            }

            @Override
            public void onFinish() {
                isTimerRunning = false;
                updateButtonState();
                playEndSound();

                if (startBreakAutomatically && defaultButtonClicked) {
                    startBreakAutomatically = false; // Reset the flag
                    startTimerWithBreak(); // Start the break timer automatically
                }
            }
        }.start();

        isTimerRunning = true;
        updateButtonState();
    }

    private void startTimerWithBreak() {
        setDefaultTimer(5); // Set break time to 5 minutes (modify as needed)
        startTimer();
    }


    private void updateClockText() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        clockTextView.setText(timeLeftFormatted);
    }

    private void updateButtonState() {
        if (isTimerRunning) {
            startButton.setEnabled(false);
            pauseResumeButton.setEnabled(true);
            pauseResumeButton.setText("Pause");
            stopButton.setEnabled(true);
            default25Button.setEnabled(false);
            default5Button.setEnabled(false);
            minutesEditText.setEnabled(false);
        } else {
            startButton.setEnabled(true);
            pauseResumeButton.setEnabled(true);
            pauseResumeButton.setText("Resume");
            stopButton.setEnabled(false);
            default25Button.setEnabled(true);
            default5Button.setEnabled(true);
            minutesEditText.setEnabled(true);
        }
    }

    private void playEndSound() {
        mediaPlayer = MediaPlayer.create(this, R.raw.end_sound);
        mediaPlayer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
