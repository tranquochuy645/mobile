package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

public class RoomsFragment extends Fragment {

    public RoomAdapter adapter = new RoomAdapter();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Set the context for the RoomAdapter
        this.adapter.context = getContext();

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rooms, container, false);
        // Inflates the XML layout file associated with this fragment.
        // The layout will be displayed as the user interface of this fragment.
        // The "container" parameter is the parent view to which the layout will be attached.
        // The "false" argument means the layout should not be attached to the parent view immediately.

        RecyclerView rcv = view.findViewById(R.id.rcv_rooms);
        // Get the reference to the room recycler view

        // Set the layout manager for the RecyclerView
        rcv.setLayoutManager(new LinearLayoutManager(getContext()));

        // Set the RoomAdapter for the RecyclerView
        rcv.setAdapter(adapter);

        MaterialButton addBtn = view.findViewById(R.id.btn_add_room);
        addBtn.setOnClickListener(e -> adapter.addRoom());
        // Set an onClickListener for the "Add Room" button, triggering the RoomAdapter's addRoom() method

        return view;
    }
}
