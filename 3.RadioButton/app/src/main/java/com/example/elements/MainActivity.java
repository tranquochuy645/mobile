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
    private RadioGroup radioGroup;
    private TextView selectedOptionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        initializeViews();

        // Set up event listeners for UI components
        setRadioGroupListener();
    }


    private void initializeViews() {
        // Initialize RadioGroup and TextView for selected option
        radioGroup = findViewById(R.id.radioGroup);
        selectedOptionTextView = findViewById(R.id.selectedOptionTextView);
    }

    private void setRadioGroupListener() {
        // Set RadioGroup listener to track radio button selection
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Check which radio button is selected and update the TextView accordingly
                if (checkedId == R.id.radioButton1) {
                    selectedOptionTextView.setText("Selected: Option 1");
                } else if (checkedId == R.id.radioButton2) {
                    selectedOptionTextView.setText("Selected: Option 2");
                }
            }
        });
    }
}
