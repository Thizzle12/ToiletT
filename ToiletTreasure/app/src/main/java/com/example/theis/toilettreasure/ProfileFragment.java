package com.example.theis.toilettreasure;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Theis on 12-10-2015.
 */

public class ProfileFragment extends android.support.v4.app.Fragment {

    ImageView profilePic;

    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        View root = i.inflate(R.layout.profile_layout, container, false);
        profilePic = (ImageView) root.findViewById(R.id.profile_picture);
        profilePic.setImageResource(R.drawable.henrikprofil);


        return root;

    }
}
