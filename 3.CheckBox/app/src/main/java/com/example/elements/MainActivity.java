package com.example.elements;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    // Declare variables
    private CheckBox checkBox;
    private TextView checkedStateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        initializeViews();

        // Set up event listeners for UI components
        setCheckBoxListener();
    }

    private void initializeViews() {
        // Initialize CheckBox and TextView for checked state
        checkBox = findViewById(R.id.checkBox);
        checkedStateTextView = findViewById(R.id.checkedStateTextView);

    }

    private void setCheckBoxListener() {
        // Set CheckBox listener to track checkbox state changes
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Update the TextView based on the checkbox state
                if (isChecked) {
                    checkedStateTextView.setText("State: Checked");
                } else {
                    checkedStateTextView.setText("State: Unchecked");
                }
            }
        });
    }

}
