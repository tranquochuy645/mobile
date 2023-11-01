package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomViewHolder> {
    // List to hold room data
    private final List<Room> data = new ArrayList<>();
    public Context context;

    // Reference to Firebase Database
    private final DatabaseReference dbRef;

    // Interface to handle room item clicks
    interface RoomClickHandler {
        void apply(int roomIndex, Room initData);
    }

    // Interface to handle data changes
    interface DataChangeHandler {
        void apply(List<Room> dt);
    }

    public RoomClickHandler roomClickHandler;
    public DataChangeHandler dataChangeHandler;

    // Constructor
    public RoomAdapter() {
        // Initialize Firebase Database reference for "rooms"
        this.dbRef = FirebaseDatabase.getInstance().getReference().child("rooms");

        // Fetch initial room data from Firebase
        this.dbRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                final DataSnapshot dt = task.getResult();
                if (dt.exists()) {
                    this.data.clear();
                    for (DataSnapshot roomSnapshot : dt.getChildren()) {
                        Room room = roomSnapshot.getValue(Room.class);
                        if (room != null) {
                            this.data.add(room);
                        }
                    }
                    notifyDataSetChanged(); // Update RecyclerView
                }
            }
        });

        // Listen for data changes in Firebase
        this.dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                refresh(snapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle database error (if needed)
            }
        });
    }

    // Inflates the view for each room item
    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.item_room, parent, false);
        return new RoomViewHolder(view);
    }

    // Binds room data to the view holder
    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder holder, int position) {
        Room room = data.get(position);
        holder.setName(room.room_name);
        holder.setDevicesCount(room.devices_count);

        // Set an item click listener for each room item
        holder.setItemClickListener(v -> {
            roomClickHandler.apply(position, room);
        });

        // Set an item long click listener for each room item
        holder.setItemLongClickListener(v -> {
            removeRoom(position); // Handle room deletion
            return true;
        });
    }

    // Returns the total number of rooms in the list
    @Override
    public int getItemCount() {
        return data.size();
    }

    // Method to add a new room
    public void addRoom() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Enter Room Name");

        final EditText input = new EditText(context);
        builder.setView(input);

        builder.setPositiveButton("OK", (dialog, which) -> {
            String roomName = input.getText().toString();
            Room newRoom = new NewRoom(roomName);
            this.data.add(newRoom);
            saveData(); // Save updated list to Firebase
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
        builder.show();
    }

    // Method to remove a room at the specified position
    private void removeRoom(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Confirm Deletion");
        builder.setMessage("Are you sure you want to delete this room?");

        builder.setPositiveButton("Yes", (dialog, which) -> {
            if (position >= 0 && position < data.size()) {
                data.remove(position);
                saveData(); // Save updated list to Firebase
            }
        });

        builder.setNegativeButton("No", (dialog, which) -> {
            // User canceled deletion
        });

        builder.create().show();
    }

    // Refreshes the local data list based on Firebase changes
    private void refresh(DataSnapshot dataSnapshot) {
        this.data.clear();
        for (DataSnapshot roomSnapshot : dataSnapshot.getChildren()) {
            final Room room = roomSnapshot.getValue(Room.class);
            if (room != null) {
                this.data.add(room);
            }
        }
        notifyDataSetChanged(); // Update RecyclerView
        this.dataChangeHandler.apply(this.data);
    }

    // Saves the updated data list to Firebase
    private void saveData() {
        this.dbRef.setValue(this.data);
    }
}
