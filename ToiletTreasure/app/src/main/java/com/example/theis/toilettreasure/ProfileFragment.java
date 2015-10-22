package com.example.theis.toilettreasure;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

/**
 * Created by Theis on 12-10-2015.
 */

public class ProfileFragment extends android.support.v4.app.Fragment implements AdapterView.OnItemClickListener {

    ImageView profilePic;

    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        View root = i.inflate(R.layout.profile_layout, container, false);
        profilePic = (ImageView) root.findViewById(R.id.profile_picture);
        profilePic.setImageResource(R.drawable.henrikprofil);


        String[] profile ={"Mine tanker", "Gemte tanker", "Bred ymer", "Instillinger"};

        ArrayAdapter adapter = new ArrayAdapter(getActivity(), R.layout.profile_layout, R.id.profile_name, profile) {
            @Override
            public View getView(int position, View cachedView, ViewGroup parent) {
                View view = super.getView(position, cachedView, parent);

                return view;
            }
        };

        ListView listView = new ListView(getActivity());
        listView.setOnItemClickListener(this);



        listView.setAdapter(adapter);
        return listView;

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
