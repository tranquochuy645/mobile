package com.example.intent;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {

    // Declare UI elements
    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonLogin;

    // Define a HashMap to store valid credentials (username -> password)
    private HashMap<String, String> validCredentials;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize UI elements by linking them with corresponding XML elements
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);

        // Initialize the HashMap with valid credentials
        validCredentials = new HashMap<>();
        validCredentials.put("user1", "password1");
        validCredentials.put("user2", "password2");
        validCredentials.put("user3", "password3");

        buttonLogin = findViewById(R.id.buttonLogin);

        // Set a click listener for the "Login" button
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve the entered username and password from the EditText fields
                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();

                // Replace this with your actual login logic
                if (isValidCredentials(username, password)) {
                    // Successful login, create an intent to navigate to another activity
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                    // Pass username and password as extras to the MainActivity
                    intent.putExtra("username", username);
                    intent.putExtra("password", password);

                    // Start the MainActivity
                    startActivity(intent);
                } else {
                    // Invalid login, display an error message to the user
                    Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Validates the entered credentials against a predefined list
    private boolean isValidCredentials(String username, String password) {
        // Check if the entered username is in the HashMap and the corresponding password matches
        return validCredentials.containsKey(username) && validCredentials.get(username).equals(password);
    }
}
