package com.example.elements;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText editText2;
    private Button button;
    private TextView
            editTextView,
            editTextView2,
            buttonClickTextView,
            buttonLongClickTextView,
            buttonTouchTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        initializeViews();

        // Set up event listeners for UI components
        setButtonClickListener();
        setButtonTouchListener();
        setButtonLongClickListener();
        setEditTextListener();
    }

    private void setEditTextListener() {
        // Declare variables
        EditText editText = findViewById(R.id.editText);
        editTextView = findViewById(R.id.editTextView);
        // Set up a TextWatcher to listen for text changes in the EditText
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Not needed for this example
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Update the TextView with the current text from the EditText
                editTextView.setText(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Not needed for this example
            }
        });

        // Initialize the second EditText and its corresponding TextView
        editText2 = findViewById(R.id.editText2);
        editTextView2 = findViewById(R.id.editTextView2);

// Set an action listener for the second EditText
        editText2.setOnEditorActionListener((v, actionId, event) -> {
            // Check if the action performed is IME_ACTION_GO
            if (actionId == EditorInfo.IME_ACTION_GO) {
                // Set the text of the second TextView to the text entered in the second EditText
                editTextView2.setText(editText2.getText());

                // Clear the text in the second EditText
                editText2.setText("");

                // Return true to indicate that the action was handled
                return true;
            }

            // Return false if the action was not IME_ACTION_GO
            return false;
        });

    }

    private void initializeViews() {
        // Initialize Button and associated TextViews
        button = findViewById(R.id.button);
        buttonClickTextView = findViewById(R.id.buttonClickTextView);
        buttonLongClickTextView = findViewById(R.id.buttonLongClickTextView);
        buttonTouchTextView = findViewById(R.id.buttonTouchTextView);
    }


    private void setButtonClickListener() {
        // Set Button click listener to update button click count
        button.setOnClickListener(new View.OnClickListener() {
            int count = 0;

            @Override
            public void onClick(View v) {
                buttonClickTextView.setText("Click count: " + ++count);
            }
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setButtonTouchListener() {
        // Set Button touch listener to track touch events
        button.setOnTouchListener(new View.OnTouchListener() {
            int countU = 0;
            int countD = 0;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    countD++; // Increment count when button is touched (ACTION_DOWN)
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    countU++; // Increment count when button is released (ACTION_UP)
                }
                // Update the TextView with touch event counts
                buttonTouchTextView.setText("Touch: " + countD + " down; " + countU + " up");
                return false;
            }
        });
    }

    private void setButtonLongClickListener() {
        // Set Button long click listener to update long-click count
        button.setOnLongClickListener(new View.OnLongClickListener() {
            int count = 0;

            @Override
            public boolean onLongClick(View view) {
                buttonLongClickTextView.setText("Long-click count: " + ++count);
                return true; // Indicate that the long-click event is consumed
            }
        });
    }
}
