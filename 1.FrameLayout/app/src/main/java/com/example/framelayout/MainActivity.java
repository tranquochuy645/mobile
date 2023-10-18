package com.example.framelayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // onCreate method, called when the activity is created

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);// Call the superclass's onCreate method
        // Set the initial layout to frame_layout.xml
        setContentView(R.layout.frame_layout);
        // Set the layout for this activity
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