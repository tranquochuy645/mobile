package com.example.myapplication;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RoomViewHolder extends RecyclerView.ViewHolder {
    // TextViews to display room name and the count of devices
    private final TextView tv_room_name, tv_devices_count;
    private final View itemView;

    // Constructor to initialize the views
    public RoomViewHolder(@NonNull View itemView) {
        super(itemView);
        this.itemView = itemView;
        // Find and assign the TextViews in the layout to local variables
        tv_room_name = itemView.findViewById(R.id.txt_room);
        tv_devices_count = itemView.findViewById(R.id.txt_device);
    }

    // Method to set the room name in the corresponding TextView
    public void setName(String name) {
        tv_room_name.setText(name);
    }

    // Method to set the devices count in the corresponding TextView
    public void setDevicesCount(int count) {
        tv_devices_count.setText(String.valueOf(count) + " devices");
    }

    // Method to set a long click listener for the item
    public void setItemLongClickListener(View.OnLongClickListener listener) {
        itemView.setOnLongClickListener(listener);
    }

    // Method to set a click listener for the item
    public void setItemClickListener(View.OnClickListener listener) {
        itemView.setOnClickListener(listener);
    }
}
