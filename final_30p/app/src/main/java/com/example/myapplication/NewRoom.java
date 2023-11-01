package com.example.myapplication;

import java.util.ArrayList;

public class NewRoom extends Room {
    // Constructor for the NewRoom class, which extends the Room class
    public NewRoom(String name) {
        super(); // Call the superclass constructor

        this.room_name = name; // Set the name of the room

        this.devices = new ArrayList<>(); // Initialize the list of devices for this room
        this.devices.add(new Device.AirConditioner()); // Add an AirConditioner device to the room
        this.devices.add(new Device.LightBulb()); // Add a LightBulb device to the room
        this.devices.add(new Device.DoorBell()); // Add a DoorBell device to the room

        this.devices_count = 3; // Set the count of devices in this room
    }
}
