// HomeFragment.java
package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;

public class HomeFragment extends Fragment {
    // A HashMap to map menu item IDs to their respective Fragment classes
    private final HashMap<Integer, Fragment> fragmentMap = new HashMap<Integer, Fragment>() {{
        // Map the Dashboard menu item to the Dashboard Fragment
        put(R.id.bt_nav_dashboard, new Dashboard());
        // Map the Devices menu item to the Devices Fragment
        put(R.id.bt_nav_devices, new Devices());
        // You can add more mappings for other menu items if needed
    }};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Find the BottomNavigationView in the fragment's layout
        BottomNavigationView bottomNavigationView = view.findViewById(R.id.bottom_navigation);

        // Set an item selected listener for the BottomNavigationView
        bottomNavigationView.setOnItemSelectedListener(
                menuItem -> {
                    // Retrieve the Fragment associated with the selected menu item
                    Fragment fragment = fragmentMap.get(menuItem.getItemId());
                    if (fragment != null) {
                        // Load the selected fragment into the container
                        loadFragment(fragment);
                    }
                    return true; // Indicate that the item selection has been handled
                }
        );

        // Set the initial fragment (DashboardFragment) when the HomeFragment is created
        if (savedInstanceState == null) {
            loadFragment(new Dashboard());
        }

        return view;
    }

    // Helper method to load a fragment into the container
    private void loadFragment(Fragment fragment) {
        // Get the FragmentManager from the parent Activity and begin a transaction
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.home_container, fragment) // Replace the container with the selected fragment
                .commit(); // Commit the transaction to apply the change
    }
}
