package com.example.theis.toilettreasure;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Henrik on 29/10/2015.
 */
public class ProfileMenu extends android.support.v4.app.Fragment implements AdapterView.OnItemClickListener{


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        String[] list ={"My Thoughts", "Kept Thoughts", "Top Thoughts", "Edit Profil", "Sittings"};

        ArrayAdapter adapter = new ArrayAdapter(getActivity(), R.layout.profile_menu, R.id.MenuText, list) {
            public View getView(int position, View cachedView, ViewGroup parent) {
                View view = super.getView(position, cachedView, parent);

                return view;
            }
        };
        ListView listMenu = new ListView(getActivity());

        listMenu.setOnItemClickListener(this);

        listMenu.setAdapter(adapter);
        return listMenu;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }


}
