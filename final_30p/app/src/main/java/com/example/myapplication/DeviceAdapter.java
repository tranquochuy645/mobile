package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

// Import statements for required classes and libraries

public class DeviceAdapter extends RecyclerView.Adapter<DeviceViewHolder> {
    public Room roomData = new Room(); // Create a new instance of the Room class to store data

    final private Context context; // Declare a private variable to store the context
    public int currentRoomIndex = 0; // Initialize the index for the current room

    // Constructor for the DeviceAdapter class, accepting a Context as a parameter
    public DeviceAdapter(Context context) {
        this.context = context; // Initialize the context variable with the provided context
    }

    // This method is called when a new view holder is needed for a RecyclerView item
    @NonNull
    @Override
    public DeviceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view; // Declare a View variable to hold the view for the item
        switch (viewType) {
            case Device.TYPE_AIR_CONDITIONER:
                view = LayoutInflater.from(this.context).inflate(R.layout.device_ac, parent, false);
                // Inflate a specific layout (device_ac) for an Air Conditioner type device
                break;
            case Device.TYPE_DOORBELL:
                view = LayoutInflater.from(this.context).inflate(R.layout.device_doorbell, parent, false);
                // Inflate a specific layout (device_doorbell) for a Doorbell type device
                break;
            case Device.TYPE_LIGHTBULB:
                view = LayoutInflater.from(this.context).inflate(R.layout.device_lightbulb, parent, false);
                // Inflate a specific layout (device_lightbulb) for a Light Bulb type device
                break;
            default:
                throw new IllegalArgumentException("Invalid viewType: " + viewType);
                // Throw an exception if an invalid viewType is provided
        }
        return new DeviceViewHolder(view); // Return a new DeviceViewHolder with the created view
    }

    // This method returns the type of view for a given position in the RecyclerView
    @Override
    public int getItemViewType(int position) {
        return roomData.devices.get(position).TYPE; // Return the type of device at the specified position
    }

    // This method is called to bind data to a view holder for a specific item
    @Override
    public void onBindViewHolder(@NonNull DeviceViewHolder holder, int position) {
        // Bind room data to the view holder
        holder.bindView(
                roomData.devices.get(position),
                (Device dv) -> {
                    this.roomData.devices.set(position, dv);
                    saveData(); // Update and save the data when a device's state changes
                }
        );
        holder.setItemLongClickListener(v -> {
            // Handle the long click event (e.g., remove the device)
            removeDevice(position);
            return true; // Indicate that the long click event was handled
        });
    }

    // This method returns the total number of items in the RecyclerView
    @Override
    public int getItemCount() {
        return roomData.devices.size(); // Return the number of devices in the room
    }

    // Method to add a new device to the room
    public void addDevice() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context); // Create an AlertDialog builder
        builder.setTitle("Add a New Device"); // Set the title of the dialog

        // Create a LinearLayout to hold EditText and RadioGroup
        LinearLayout container = new LinearLayout(context);
        container.setOrientation(LinearLayout.VERTICAL);

        // Create an EditText for user input (device name)
        final EditText input_name = new EditText(context);
        input_name.setHint("Enter device name"); // Set a hint for user input

        // Create a RadioGroup for device type selection
        final RadioGroup input_type = new RadioGroup(context);

        // Create RadioButtons for each device type
        RadioButton radioButtonAirConditioner = new RadioButton(context);
        radioButtonAirConditioner.setText("Air Conditioner");
        RadioButton radioButtonLightBulb = new RadioButton(context);
        radioButtonLightBulb.setText("Light Bulb");
        RadioButton radioButtonDoorBell = new RadioButton(context);
        radioButtonDoorBell.setText("Door Bell");

        input_type.addView(radioButtonAirConditioner);
        input_type.addView(radioButtonLightBulb);
        input_type.addView(radioButtonDoorBell);

        // Add the EditText and RadioGroup to the container layout
        container.addView(input_name);
        container.addView(input_type);

        builder.setView(container); // Set the container as the view for the AlertDialog

        builder.setPositiveButton("OK", (dialog, which) -> {
            // User confirmed, add the device to the list
            String deviceName = input_name.getText().toString();
            int selectedRadioButtonId = input_type.getCheckedRadioButtonId();

            if (!deviceName.isEmpty() && selectedRadioButtonId != -1) {
                RadioButton selectedRadioButton = input_type.findViewById(selectedRadioButtonId);
                String deviceType = selectedRadioButton.getText().toString();

                // Create the device and add it to the list
                Device newDevice = createDevice(deviceName, deviceType);
                roomData.devices.add(newDevice); // Add the new device to the list
                roomData.devices_count = roomData.devices.size(); // Update the device count
                saveData(); // Save the updated data to Firebase Database
            }
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel()); // Handle cancel button

        builder.show(); // Show the AlertDialog to allow the user to input device details
    }

    // Helper method to create a Device based on the chosen type
    private Device createDevice(String deviceName, String deviceType) {
        Device newDv; // Declare a new Device variable
        switch (deviceType) {
            case "Air Conditioner":
                newDv = new Device.AirConditioner(); // Create an Air Conditioner device
                break;
            case "Light Bulb":
                newDv = new Device.LightBulb(); // Create a Light Bulb device
                break;
            case "Door Bell":
                newDv = new Device.DoorBell(); // Create a Door Bell device
                break;
            default:
                throw new IllegalArgumentException(); // Throw an exception for unknown device types
        }
        newDv.name = deviceName; // Set the name of the new device
        return newDv; // Return the created device
    }

    // Method to save data to Firebase Database
    private void saveData() {
        DatabaseReference roomRef = FirebaseDatabase.getInstance()
                .getReference()
                .child("rooms")
                .child(String.valueOf(currentRoomIndex));
        // Get a reference to the room's data in Firebase Database
        roomRef.setValue(this.roomData); // Set the data in the database to the current room data
    }

    // Method to remove a device from the room
    private void removeDevice(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context); // Create an AlertDialog builder
        builder.setTitle("Confirm Deletion"); // Set the title of the dialog
        builder.setMessage("Are you sure you want to delete this device?"); // Set a confirmation message

        builder.setPositiveButton("Yes", (dialog, which) -> {
            // User confirmed, remove the device from the list
            if (position >= 0 && position < roomData.devices.size()) {
                roomData.devices.remove(position); // Remove the device from the list
                roomData.devices_count = roomData.devices.size(); // Update the device count
                saveData(); // Save the updated data to Firebase Database
            }
        });

        builder.setNegativeButton("No", (dialog, which) -> {
            // User canceled, do nothing
            // No action needed if the user cancels the deletion
        });

        builder.create().show(); // Show the AlertDialog for the user to confirm device deletion
    }
}
