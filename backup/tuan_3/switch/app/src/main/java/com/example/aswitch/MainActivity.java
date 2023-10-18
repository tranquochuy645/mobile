package com.example.aswitch;

import androidx.annotation.ColorRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {
    private ConstraintLayout constraintLayout; // Declare a reference to the ConstraintLayout in the XML layout.
    private Switch switch1; // Declare a reference to the Switch in the XML layout.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Set the content view to the specified XML layout.

        // Initialize views by finding them in the XML layout using their IDs.
        constraintLayout = findViewById(R.id.constraintLayout); // Find the ConstraintLayout by its ID.
        switch1 = findViewById(R.id.switch1); // Find the Switch by its ID.

        // Set a listener for the Switch to handle changes in its state.
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Call the switchTheme method to change the background color and text color based on the Switch state.
                switchTheme(isChecked);
            }
        });
    }

    private void switchTheme(boolean dark) {
        // Change the background color of the ConstraintLayout based on the Switch state.
        // Use ContextCompat.getColor to retrieve a color resource and set it as the background color.
        constraintLayout.setBackgroundColor(ContextCompat.getColor(this, dark ? R.color.black : R.color.white));

        // Change the text color of the Switch based on the Switch state.
        // Use ContextCompat.getColor to retrieve a color resource and set it as the text color.
        switch1.setTextColor(ContextCompat.getColor(this, dark ? R.color.white : R.color.black));
    }
}
