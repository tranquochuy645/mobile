package com.example.stt;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String GOOGLE_STT_PACKAGE = "com.google.android.googlequicksearchbox";

    private ActivityResultLauncher<Intent> SttLauncher;
    private TextView tv_recognized_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SttLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                res -> {
                    if (res.getResultCode() != RESULT_OK) return;
                    Intent data = res.getData();
                    if (data == null) return;
                    final ArrayList<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    final String recognized_text = (results == null) ? "Noi j z ai hieu ba" : results.get(0);
                    tv_recognized_text.setText(recognized_text);

                });
        setContentView(R.layout.activity_main);
        tv_recognized_text = findViewById(R.id.tv_recognized_text);
        final Button btn = findViewById(R.id.btn_speak);
        btn.setOnClickListener(v -> startSpeechToText());

    }


    private void startSpeechToText() {
        // Everytime the user hit Speak button, this function is fired
        // Need a new Intent on every call, since it is an external service (From Google)
        // I've never tested this with other provider other than Google
        Intent speechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "vi-VN"); // Set default language to Vietnamese
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_PROMPT, ""); // This text appears on the screen as a prompt
        try {
            SttLauncher.launch(speechRecognizerIntent);
            // Result is handled in the onActivityResult method above
        } catch (ActivityNotFoundException e) {
            Log.e("SpeechToText", "Speech recognition not supported");
            // This happen when no STT engine detected on user's device
            // Google STT engine package name
            try {
                //Try to download from Google play
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + GOOGLE_STT_PACKAGE)));
            } catch (android.content.ActivityNotFoundException e2) {
                //Try to download again from default market app
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + GOOGLE_STT_PACKAGE)));
            }
        }
    }

}