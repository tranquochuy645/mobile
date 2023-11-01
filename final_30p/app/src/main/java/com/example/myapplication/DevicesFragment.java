package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

public class DevicesFragment extends Fragment {
    interface BackHomeHandler {
        void apply();
    }

    private TextView tv_room_name;
    public DeviceAdapter adapter;
    public BackHomeHandler backHomeHandler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.adapter = new DeviceAdapter(getContext());
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_devices, container, false);
        // Inflates the XML layout file associated with this fragment.
        // The layout will be displayed as the user interface of this fragment.
        // The "container" parameter is the parent view to which the layout will be attached.
        // The "false" argument means the layout should not be attached to the parent view immediately.
        tv_room_name = view.findViewById(R.id.room_name);
        RecyclerView rcv = view.findViewById(R.id.rcv_devices);
        rcv.setLayoutManager(new LinearLayoutManager(getContext()));
        // Get the reference to the room recycler view
        // Construct the adapter object
        rcv.setAdapter(adapter);


        MaterialButton addBtn = view.findViewById(R.id.btn_add_device);
        addBtn.setOnClickListener(e -> adapter.addDevice());

        ImageButton backBtn = view.findViewById(R.id.btn_back_home);
        backBtn.setOnClickListener(e -> backHomeHandler.apply());

        return view;
    }

    public void setCurrentRoom(int roomIndex, Room initData) {
        tv_room_name.setText(initData.room_name);
        adapter.roomData=initData;
        adapter.currentRoomIndex = roomIndex;
        adapter.notifyDataSetChanged();
    }
}