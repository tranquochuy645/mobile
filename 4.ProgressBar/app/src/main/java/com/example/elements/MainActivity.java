package com.example.elements;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    // Declare variables
    private ProgressBar progressBarHorizontal;
    private ToggleButton toggleButton;
    private boolean isRunning = false;
    private Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        initializeViews();

        // Set up event listeners for UI components
        setToggleButtonListener();
    }

    private void initializeViews() {
        // Initialize ProgressBars and ToggleButton
        progressBarHorizontal = findViewById(R.id.progressBarHorizontal);
        toggleButton = findViewById(R.id.toggleButton);
    }

    private void setToggleButtonListener() {
        // Set ToggleButton click listener to start/stop progress updates
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isRunning) {
                    // Stop the progress updates
                    handler.removeCallbacks(updateProgress);
                } else {
                    // Start the progress updates
                    handler.postDelayed(updateProgress, 100);
                }
                isRunning = !isRunning; // Toggle the running state
            }
        });
    }

    private Runnable updateProgress = new Runnable() {
        @Override
        public void run() {
            // Update progress bars
            int currentProgress = progressBarHorizontal.getProgress();
            if (currentProgress < 100) {
                currentProgress += 5; // Increment progress by 5
                progressBarHorizontal.setProgress(currentProgress);
                handler.postDelayed(this, 100); // Repeat the update after 100 milliseconds
            } else {
                // Reset progress to 0 when it reaches 100%
                progressBarHorizontal.setProgress(0);
                handler.postDelayed(this, 100); // Repeat the update after 100 milliseconds
            }
        }
    };
}
