package com.example.firebase;

import android.os.Bundle;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private Switch switchLight;
    private DatabaseReference switchStateRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the Switch view in your layout by its ID
        switchLight = findViewById(R.id.switchLight);

        // Create a reference to the "switchState" node in Firebase Realtime Database
        switchStateRef = FirebaseDatabase.getInstance().getReference().child("switchState");

        // Listen for changes in the switch state in Firebase Realtime Database
        switchStateRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Retrieve the boolean value from the database
                Boolean isSwitchOn = dataSnapshot.getValue(Boolean.class);
                if (isSwitchOn == null) {
                    // Handle the case where the value is missing or invalid
                    return;
                }

                // Set the state of the Switch view based on the retrieved value
                switchLight.setChecked(isSwitchOn);

                // Update the text of the Switch view to show "ON" or "OFF"
                switchLight.setText(isSwitchOn ? "ON" : "OFF");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle any errors or exceptions that occur during database read
            }
        });

        // Listen for changes in the Switch view's state
        switchLight.setOnCheckedChangeListener((v, isChecked) -> {
            // Update the state in Firebase Realtime Database when the Switch state changes
            switchStateRef.setValue(isChecked);

            // Update the text of the Switch view to show "ON" or "OFF"
            switchLight.setText(isChecked ? "ON" : "OFF");
        });

        // Set the initial state of the view based on the value retrieved from Firebase
        switchStateRef.get().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                // Handle the case where data retrieval from Firebase failed
                return;
            }

            // Retrieve the boolean value from the task result
            Boolean isSwitchOn = task.getResult().getValue(Boolean.class);
            if (isSwitchOn == null) {
                // Handle the case where the value is missing or invalid
                return;
            }

            // Set the initial state of the Switch view and update its text
            switchLight.setChecked(isSwitchOn);
            switchLight.setText(isSwitchOn ? "ON" : "OFF");
        });
    }
}
