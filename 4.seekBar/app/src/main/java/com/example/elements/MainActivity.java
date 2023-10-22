package com.example.elements;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    // Declare variables
    private SeekBar seekBar;
    private TextView
            seekBarTextView,
            seekBarDrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        initializeViews();

        // Set up event listeners for UI components
        setSeekBarListener();
    }

    private void initializeViews() {
        // Initialize SeekBar and associated TextViews
        seekBar = findViewById(R.id.seekBar);
        seekBarTextView = findViewById(R.id.seekBarTextView);
        seekBarDrag = findViewById(R.id.seekBarDrag);
    }

    private void setSeekBarListener() {
        // Set SeekBar listener to update TextViews when progress changes
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Update percentage text based on SeekBar progress
                String percentage = progress + "%";
                seekBarTextView.setText(percentage);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Indicate that SeekBar dragging has started
                seekBarDrag.setText("Drag: ON");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Indicate that SeekBar dragging has stopped
                seekBarDrag.setText("Drag: OFF");
            }
        });
    }
}
