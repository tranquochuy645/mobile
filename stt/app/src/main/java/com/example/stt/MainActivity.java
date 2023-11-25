package com.example.stt;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Package name of the Speech-to-Text (STT) application
    public static final String STT_PACKAGE_NAME = "com.google.android.googlequicksearchbox";

    // TextView to display recognized text
    private TextView tv_recognized_text;

    // ActivityResultLauncher for launching the Speech Recognizer Intent
    private ActivityResultLauncher<Intent> aLauncher;

    // Intent for starting the Speech Recognizer
    private final Intent speechRecognizer = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            .putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize the ActivityResultLauncher
        aLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), this::sttResultHandler);

        // Set the layout for the activity
        setContentView(R.layout.activity_main);

        // Initialize the TextView for displaying recognized text
        tv_recognized_text = findViewById(R.id.tv_recognized_text);

        // Set a click listener on the speak button to initiate the Speech-to-Text process
        findViewById(R.id.btn_speak).setOnClickListener(v -> startStt());
    }

    // Method to start the Speech-to-Text process
    private void startStt() {
        try {
            // Launch the Speech Recognizer using the ActivityResultLauncher
            aLauncher.launch(speechRecognizer);
        } catch (ActivityNotFoundException err) {
            // If Speech Recognizer is not installed, prompt the user to install it
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + STT_PACKAGE_NAME)));
            } catch (ActivityNotFoundException err2) {
                // If Google Play is not available, open the web browser to install Speech Recognizer
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + STT_PACKAGE_NAME)));
            }
        }
    }

    // Method to handle the result of the Speech-to-Text process
    private void sttResultHandler(ActivityResult res) {
        // Check if the result is OK
        if (res.getResultCode() != RESULT_OK) return;

        // Get the raw data from the result
        Intent rawData = res.getData();
        if (rawData == null) return;

        // Extract the list of recognized results
        ArrayList<String> arrResults = rawData.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
        if (arrResults == null) return;

        // Display the first recognized result in the TextView
        tv_recognized_text.setText(arrResults.get(0));
    }
}
