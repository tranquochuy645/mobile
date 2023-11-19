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
    public static final String STT_PACKAGE_NAME = "com.google.android.googlequicksearchbox";

    private TextView tv_recognized_text;
    private ActivityResultLauncher<Intent> aLauncher;

    private final Intent speechRecognizer = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        aLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), this::sttResultHandler);
        setContentView(R.layout.activity_main);
        tv_recognized_text = findViewById(R.id.tv_recognized_text);
        findViewById(R.id.btn_speak).setOnClickListener(v -> startStt());
    }

    private void startStt() {
        try {
            aLauncher.launch(speechRecognizer);
        } catch (ActivityNotFoundException err) {
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + STT_PACKAGE_NAME)));
            } catch (ActivityNotFoundException err2) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + STT_PACKAGE_NAME)));
            }
        }
    }

    private void sttResultHandler(ActivityResult res) {
        if (res.getResultCode() != RESULT_OK) return;
        Intent rawData = res.getData();
        if (rawData == null) return;
        ArrayList<String> arrResults = rawData.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
        if (arrResults == null) return;
        tv_recognized_text.setText(arrResults.get(0));
    }
}