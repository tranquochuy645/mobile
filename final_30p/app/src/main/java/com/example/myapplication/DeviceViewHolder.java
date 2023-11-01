package com.example.myapplication;

import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

// DeviceViewHolder class for managing individual views in a RecyclerView
public class DeviceViewHolder extends RecyclerView.ViewHolder {
    interface DeviceCallback {
        void apply(Device dv);
    }

    private final TextView deviceNameTextView;

    // Constructor for the DeviceViewHolder class
    public DeviceViewHolder(@NonNull View itemView) {
        super(itemView);
        // Initialize the TextView for device name
        deviceNameTextView = itemView.findViewById(R.id.device_name);
    }

    // Method to bind device data to the views within the ViewHolder
    public void bindView(Device device, DeviceCallback cb) {
        deviceNameTextView.setText(device.name); // Set the device name to the TextView
        // Check the type of the device and bind the appropriate view elements
        switch (device.TYPE) {
            case Device.TYPE_AIR_CONDITIONER:
                bindAC(device, cb); // Bind Air Conditioner views
                break;
            case Device.TYPE_LIGHTBULB:
                bindLightBulb(device, cb); // Bind Light Bulb views
                break;
            case Device.TYPE_DOORBELL:
                // No specific UI for the Door Bell, so no action required
                break;
            default:
                throw new IllegalArgumentException("Unknown device type: " + device.TYPE);
        }
    }

    // Method to bind views for the Air Conditioner device type
    private void bindAC(Device dv, DeviceCallback cb) {
        SeekBar sb = itemView.findViewById(R.id.seek_bar); // SeekBar for temperature control
        TextView tv = itemView.findViewById(R.id.temp); // TextView to display temperature
        ImageView iv = itemView.findViewById(R.id.cold); // ImageView for visual representation

        // Set initial data to the views
        tv.setText("Temp: " + String.valueOf(dv.data) + " ℃");
        iv.setImageAlpha(255 - (dv.data - 18) * 20);
        sb.setProgress(dv.data);

        // SeekBar change listener to update data and trigger callback on stop tracking touch
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tv.setText("Temp: " + String.valueOf(progress) + " ℃");
                iv.setImageAlpha(255 - (dv.data - 18) * 20);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Not utilized in this scenario
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                dv.data = seekBar.getProgress(); // Update device data
                cb.apply(dv); // Apply the updated data via the callback
            }
        });
    }

    // Method to bind views for the Light Bulb device type
    private void bindLightBulb(Device dv, DeviceCallback cb) {
        ToggleButton tg_btn = itemView.findViewById(R.id.sw); // ToggleButton for on/off state

        // Set initial data to the ToggleButton
        tg_btn.setChecked(dv.data == 1);

        // ToggleButton listener to update data and trigger callback on state change
        tg_btn.setOnCheckedChangeListener((buttonView, isChecked) -> {
            dv.data = isChecked ? 1 : 0; // Update device data based on the state
            cb.apply(dv); // Apply the updated data via the callback
        });
    }

    // Method to set a long click listener on the item view
    public void setItemLongClickListener(View.OnLongClickListener listener) {
        itemView.setOnLongClickListener(listener); // Set a long click listener on the item view
    }
}
