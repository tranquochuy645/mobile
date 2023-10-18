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
    private EditText editText, editText2;
    private ProgressBar progressBarHorizontal;
    private ToggleButton toggleButton;
    private boolean isRunning = false;
    private Handler handler = new Handler();
    private SeekBar seekBar;
    private Button button;
    private CheckBox checkBox;
    private RadioGroup radioGroup;
    private TextView
            editTextView,
            editTextView2,
            selectedOptionTextView,
            buttonClickTextView,
            seekBarTextView,
            seekBarDrag,
            buttonLongClickTextView,
            buttonTouchTextView,
            checkedStateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        initializeViews();

        // Set up event listeners for UI components
        setSeekBarListener();
        setButtonClickListener();
        setButtonTouchListener();
        setButtonLongClickListener();
        setRadioGroupListener();
        setCheckBoxListener();
        setToggleButtonListener();
        setEditTextListener();
    }

    private void setEditTextListener() {
        editText = findViewById(R.id.editText);
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
        editText2.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
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
            }
        });

    }

    private void initializeViews() {
        // Initialize SeekBar and associated TextViews
        seekBar = findViewById(R.id.seekBar);
        seekBarTextView = findViewById(R.id.seekBarTextView);
        seekBarDrag = findViewById(R.id.seekBarDrag);

        // Initialize Button and associated TextViews
        button = findViewById(R.id.button);
        buttonClickTextView = findViewById(R.id.buttonClickTextView);
        buttonLongClickTextView = findViewById(R.id.buttonLongClickTextView);
        buttonTouchTextView = findViewById(R.id.buttonTouchTextView);

        // Initialize RadioGroup and TextView for selected option
        radioGroup = findViewById(R.id.radioGroup);
        selectedOptionTextView = findViewById(R.id.selectedOptionTextView);

        // Initialize CheckBox and TextView for checked state
        checkBox = findViewById(R.id.checkBox);
        checkedStateTextView = findViewById(R.id.checkedStateTextView);

        // Initialize ProgressBars and ToggleButton
        progressBarHorizontal = findViewById(R.id.progressBarHorizontal);
        toggleButton = findViewById(R.id.toggleButton);
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

    private void setButtonClickListener() {
        // Set Button click listener to update button click count
        button.setOnClickListener(new View.OnClickListener() {
            int count = 0;

            @Override
            public void onClick(View v) {
                buttonClickTextView.setText("Click count: " + String.valueOf(++count));
            }
        });
    }

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
                buttonTouchTextView.setText("Touch: " + String.valueOf(countD) + " down; " + String.valueOf(countU) + " up");
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
                buttonLongClickTextView.setText("Long-click count: " + String.valueOf(++count));
                return true; // Indicate that the long-click event is consumed
            }
        });
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

    private void setToggleButtonListener() {
        // Set ToggleButton click listener to start/stop progress updates
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isRunning) {
                    // Stop the progress updates
                    handler.removeCallbacks(updateProgress);
                } else {
                    // Start the progress updates
                    handler.postDelayed(updateProgress, 100);
                }
                isRunning = !isRunning; // Toggle the running state
            }
        });
    }

    private Runnable updateProgress = new Runnable() {
        @Override
        public void run() {
            // Update progress bars
            int currentProgress = progressBarHorizontal.getProgress();
            if (currentProgress < 100) {
                currentProgress += 5; // Increment progress by 5
                progressBarHorizontal.setProgress(currentProgress);
                handler.postDelayed(this, 100); // Repeat the update after 100 milliseconds
            } else {
                // Reset progress to 0 when it reaches 100%
                progressBarHorizontal.setProgress(0);
                handler.postDelayed(this, 100); // Repeat the update after 100 milliseconds
            }
        }
    };
}
