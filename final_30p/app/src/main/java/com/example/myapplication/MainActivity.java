package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.navigation.NavigationView;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    // A HashMap to associate menu item IDs with their respective fragments
    private final HashMap<Integer, Fragment> fragmentMap = new HashMap<Integer, Fragment>() {{
        put(R.id.nav_home, new HomeFragment());
        put(R.id.nav_settings, new SettingsFragment());
        // Add more mappings for other menu items if needed
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the NavigationView and set a listener for item selection
        NavigationView navigationView = findViewById(R.id.navigation_view);

        // Set navigation item click event handler
        navigationView.setNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.nav_logout) {
                // Close the app
                finish();
                return true;
            }

            // Get the selected fragment based on the menu item's ID
            Fragment fragment = fragmentMap.get(item.getItemId());
            if (fragment == null) {
                // Break if an unknown item is caught
                return true;
            }

            // Load the selected fragment
            loadFragment(fragment);

            // Close the navigation drawer
            closeDrawer();

            return true;
        });

        // Initially load the HomeFragment
        if (savedInstanceState == null) {
            loadFragment(new HomeFragment());
        }
    }

    // Helper method to load a fragment into the container
    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
    }

    // Helper method to close the navigation drawer
    private void closeDrawer() {
        ((DrawerLayout) findViewById(R.id.drawer_layout)).closeDrawer(GravityCompat.START);
    }
}
