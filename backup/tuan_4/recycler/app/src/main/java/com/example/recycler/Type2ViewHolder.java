package com.example.recycler;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;

public class Type2ViewHolder extends RecyclerView.ViewHolder {
    private MaterialTextView label;
    private Button trigger;
    private Button clearEl;

    private Toast toast;

    public Type2ViewHolder(@NonNull View itemView) {
        super(itemView);
        label = itemView.findViewById(R.id.label);       // Find the MaterialTextView with id "label"
        trigger = itemView.findViewById(R.id.trigger);   // Find the Button with id "trigger"
        clearEl = itemView.findViewById(R.id.clearEl);   // Find the Button with id "clearEl"

        // Initialize a Toast message here for Type 2 button clicks
        toast = Toast.makeText(itemView.getContext(), "Type 2 button clicked", Toast.LENGTH_SHORT);
    }

    public void bindData(ItemModel item, ItemsAdapter adapter) {
        // Bind data to the Type2 ViewHolder

        // Set the label text to the data provided by the ItemModel
        label.setText(item.getLabel());

        // Set an onClickListener for the trigger button
        trigger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Display the Toast message when the trigger button is clicked
                toast.show();
            }
        });

        // Set an onClickListener for the clearEl button
        clearEl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Use the callback provided by the adapter to handle the clearEl button click
                // This typically removes the item from the RecyclerView
                adapter.removeItem(item);
            }
        });
    }
}
