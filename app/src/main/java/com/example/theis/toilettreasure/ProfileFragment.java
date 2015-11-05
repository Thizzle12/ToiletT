package com.example.theis.toilettreasure;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Theis on 12-10-2015.
 */

public class ProfileFragment extends android.support.v4.app.Fragment implements AdapterView.OnItemClickListener {

    ImageView profilePic;
    FrameLayout listText;


    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View root = i.inflate(R.layout.profile_layout, container, false);
        profilePic = (ImageView) root.findViewById(R.id.profile_picture);

        listText = (FrameLayout) root.findViewById(R.id.listMenu);


        getActivity().getSupportFragmentManager().beginTransaction()
                .add(listText.getId(), new ProfileMenu())
                .addToBackStack(null)
                .commit();
        profilePic.setImageResource(R.drawable.smiley);

            return root;

        }
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



    }
    }
