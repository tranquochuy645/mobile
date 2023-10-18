package com.example.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Retrieve the username and password from the intent extras
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            // Extract the username and password from the extras
            String username = extras.getString("username");
            String password = extras.getString("password");

            // Display the username and password in the UI
            TextView usernameTextView = findViewById(R.id.usernameTextView);
            TextView passwordTextView = findViewById(R.id.passwordTextView);

            // Set the text of the TextViews to display the username and password
            usernameTextView.setText("Username: " + username);
            passwordTextView.setText("Password: " + password);
        }

        // Get a reference to the "Go Back to Login" button
        Button buttonGoBack = findViewById(R.id.buttonGoBack);

        // Set a click listener for the button
        buttonGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to navigate back to the LoginActivity
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);

                // Start the LoginActivity
                startActivity(intent);
            }
        });
    }
}
