package com.example.myapplication;

// Device class to represent different types of devices
public class Device {
    // Constants representing different device types
    public static final int TYPE_AIR_CONDITIONER = 0;
    public static final int TYPE_LIGHTBULB = 1;
    public static final int TYPE_DOORBELL = 2;

    // Common properties for all device types
    public String name; // Name of the device
    public int TYPE; // Type of the device

    public int data; // Additional data specific to the device

    // Private constructor preventing direct instantiation of the Device class
    private Device() {
    }

    // Subclass representing an Air Conditioner device
    public static class AirConditioner extends Device {
        public AirConditioner() {
            super(); // Calling the parent class constructor
            name = "AC"; // Setting the name for the Air Conditioner device
            data = 18; // Setting specific data for the Air Conditioner
            TYPE = Device.TYPE_AIR_CONDITIONER; // Setting the device type
        }
    }

    // Subclass representing a Light Bulb device
    public static class LightBulb extends Device {
        public LightBulb() {
            super(); // Calling the parent class constructor
            name = "Light bulb"; // Setting the name for the Light Bulb device
            data = 0; // Setting specific data for the Light Bulb
            TYPE = Device.TYPE_LIGHTBULB; // Setting the device type
        }
    }

    // Subclass representing a Door Bell device
    public static class DoorBell extends Device {
        public DoorBell() {
            super(); // Calling the parent class constructor
            name = "Bell"; // Setting the name for the Door Bell device
            TYPE = Device.TYPE_DOORBELL; // Setting the device type
        }
    }
}
