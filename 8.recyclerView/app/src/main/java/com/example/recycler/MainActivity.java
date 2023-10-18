package com.example.recycler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ItemsAdapter itemsAdt;  // Declare an instance of ButtonAdapter
    private Spinner typeSpinner;  // Declare an instance of Spinner

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);  // Find the RecyclerView by its ID
        recyclerView.setLayoutManager(new LinearLayoutManager(this));  // Set a LinearLayoutManager for the RecyclerView
        // Initialize and set the adapter
        itemsAdt = new ItemsAdapter(this, new ArrayList<>());  // Create a new ButtonAdapter with an empty list
        recyclerView.setAdapter(itemsAdt);  // Set the ButtonAdapter as the adapter for the RecyclerView

        // Find the "Add Button" view (Spinner)
        typeSpinner = findViewById(R.id.typeSpinner);

        // Set up a spinner adapter with Type 1 and Type 2 options
        ArrayAdapter<String> buttonTypeAdt = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                new String[]{"Type 1", "Type 2"});  // Create an ArrayAdapter with two options
        buttonTypeAdt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);  // Set a dropdown layout for the Spinner
        typeSpinner.setAdapter(buttonTypeAdt);  // Set the ArrayAdapter as the adapter for the Spinner
    }

    public void addItemHandler(View view) {
        // Handle the "Add Button" button click event
        switch (typeSpinner.getSelectedItem().toString()) {
            case "Type 1":
                itemsAdt.addItem(new ItemModel(ItemModel.TYPE_TYPE1, "Button type 1"));  // Add a Type 1 button to the RecyclerView
                break;
            case "Type 2":
                itemsAdt.addItem(new ItemModel(ItemModel.TYPE_TYPE2, "Button type 2"));  // Add a Type 2 button to the RecyclerView
                break;
            default:
                break;
        }
    }

}
