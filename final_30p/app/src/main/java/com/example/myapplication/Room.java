package com.example.myapplication;

import java.util.List;

public class Room {
    // This class exists to help firebase sdk extract the room data
    public int devices_count; // Indicates the count of devices in the room
    public String room_name; // Represents the name of the room
    public List<Device> devices; // Holds a list of devices associated with the room

    // Constructor for the Room class (although currently empty)
    public Room() {
        // The constructor does not contain any initialization logic
        // It's empty, so when a Room object is created, its properties remain at their default values.
    }
}
