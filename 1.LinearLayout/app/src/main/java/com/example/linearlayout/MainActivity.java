package com.example.linearlayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    // onCreate method, called when the activity is created

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);// Call the superclass's onCreate method
        setContentView(R.layout.linear_layout);// Set the layout for this activity
    }
}