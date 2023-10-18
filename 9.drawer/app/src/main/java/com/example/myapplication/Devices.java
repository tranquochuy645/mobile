package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Devices extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.devices, container, false);
        // Inflate the XML layout file associated with this fragment.
        // The layout will be displayed as the user interface of this fragment.
        // The "container" parameter is the parent view to which the layout will be attached.
        // The "false" argument means the layout should not be attached to the parent view immediately.
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Your fragment initialization code can go here
        // This method is called after the view has been created.
        // You can perform any additional setup or UI-related operations here.
    }
}
