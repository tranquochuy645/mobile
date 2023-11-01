// HomeFragment.java
package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.List;

// HomeFragment class extending Fragment
public class HomeFragment extends Fragment {
    private final DevicesFragment devicesFragment = new DevicesFragment();
    private final RoomsFragment roomsFragment = new RoomsFragment();

    private int currentRoom = -1; // Keeps track of the currently selected room index, -1 indicates no room is selected

    // Constructor
    public HomeFragment() {
        // Set up click handlers to manage fragment visibility
        this.roomsFragment.adapter.roomClickHandler = this::showDevicesFragment;
        this.roomsFragment.adapter.dataChangeHandler = this::dataChangeHandler;
        this.devicesFragment.backHomeHandler = this::showRoomsFragment;
    }

    // Called to create the view hierarchy associated with the fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();

        // Add nested fragments to their respective FrameLayout containers
        transaction.add(R.id.frame_devices, devicesFragment);
        transaction.add(R.id.frame_rooms, roomsFragment);
        transaction.commit();

        // Initially, display the rooms fragment
        showRoomsFragment();

        return view;
    }

    // Method to show the rooms fragment and hide the devices fragment
    private void showRoomsFragment() {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.show(roomsFragment);
        transaction.hide(devicesFragment);
        transaction.commit();
        this.currentRoom = -1; // No room is currently selected
    }

    // Method to show the devices fragment and hide the rooms fragment
    private void showDevicesFragment(int roomIndex, Room initData) {
        devicesFragment.setCurrentRoom(roomIndex, initData);
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.show(devicesFragment);
        transaction.hide(roomsFragment);
        transaction.commit();
        this.currentRoom = roomIndex; // Set the current room index
    }

    // Method to handle data change in the list of rooms
    private void dataChangeHandler(List<Room> data) {
        if (currentRoom != -1) {
            devicesFragment.setCurrentRoom(currentRoom, data.get(currentRoom));
        }
    }
}
