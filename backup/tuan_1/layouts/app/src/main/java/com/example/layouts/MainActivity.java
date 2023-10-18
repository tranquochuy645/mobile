// Package declaration
package com.example.layouts;

// Import statements
import androidx.appcompat.app.AppCompatActivity; // Import the AppCompatActivity class
import android.os.Bundle; // Import the Bundle class
import android.view.View; // Import the View class
import android.widget.Button; // Import the Button class
import android.widget.TextView; // Import the TextView class

// MainActivity class definition, extending AppCompatActivity
public class MainActivity extends AppCompatActivity {
    // Declare a Button variable for layout switching
    private Button switchLayoutButton; // Declaration of the button for layout switching

    // Declare an integer variable to track the current layout
    private int index = 0; // Variable to track the current layout

    // Declare an array to store layout IDs
    private int[] layoutIds = {
            R.layout.constraint_layout, // Array element for ConstraintLayout ID
            R.layout.relative_layout,   // Array element for RelativeLayout ID
            R.layout.linear_layout,     // Array element for LinearLayout ID
            R.layout.frame_layout      // Array element for FrameLayout ID
    }; // Array to store layout IDs

    // onCreate method, called when the activity is created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // Call the superclass's onCreate method
        // Set the initial layout to constraint_layout
        setContentView(R.layout.constraint_layout); // Set the layout for this activity

        // Find the button in the layout by its ID
        switchLayoutButton = findViewById(R.id.button); // Get a reference to the button in the layout

        // Set a click listener for the layout switching button
        switchLayoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchLayout(); // Call the switchLayout method to change layouts when the button is clicked
            }
        });
    }

    // Method to switch layouts
    public void switchLayout() {
        // Change the layout based on the index variable
        index = (index + 1) % 4; // Increment index and wrap around to 0 if it reaches 4

        // Set the new layout using an ID from the layoutIds array
        setContentView(layoutIds[index]); // Change the layout to the selected one

        // If the current layout is frame_layout, set click listeners for TextViews
        if (index == 3) {
            final TextView textView1 = findViewById(R.id.textView1); // Find TextView1 in the new layout
            final TextView textView2 = findViewById(R.id.textView2); // Find TextView2 in the new layout
            final TextView textView3 = findViewById(R.id.textView3); // Find TextView3 in the new layout

            // Set click listeners to bring TextViews to the front when clicked
            textView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bringViewToFront(R.id.textView1); // Call the bringViewToFront method for TextView1
                }
            });

            textView2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bringViewToFront(R.id.textView2); // Call the bringViewToFront method for TextView2
                }
            });

            textView3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bringViewToFront(R.id.textView3); // Call the bringViewToFront method for TextView3
                }
            });
        }

        // Find the layout switching button in the new layout
        switchLayoutButton = findViewById(R.id.button); // Get a reference to the button in the new layout

        // Set a click listener for the layout switching button in the new layout
        switchLayoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchLayout(); // Call the switchLayout method to change layouts when the button is clicked
            }
        });
    }

    // Method to bring a view to the front
    public void bringViewToFront(int viewId) {
        View viewToBringToFront = findViewById(viewId); // Find the view to bring to the front by its ID
        if (viewToBringToFront != null) {
            viewToBringToFront.bringToFront(); // Bring the view to the front of the layout
            viewToBringToFront.invalidate(); // Invalidate the view to request a redraw
            viewToBringToFront.requestLayout(); // Request a layout update for the view
        }
    }
}
