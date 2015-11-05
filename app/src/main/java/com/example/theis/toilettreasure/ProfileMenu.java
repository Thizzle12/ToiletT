package com.example.theis.toilettreasure;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Henrik on 29/10/2015.
 */
public class ProfileMenu extends android.support.v4.app.Fragment implements AdapterView.OnItemClickListener{


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        String[] list ={"Login", "Localization", "LocationListener", "Sittings"};

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
    public void onItemClick(AdapterView<?> listMenu, View view, int position, long id) {

        if (position == 0) {
            Intent int0 = new Intent(getActivity(), Login.class);
            startActivity(int0);
        }

        else if (position == 1) {
            Intent int1 = new Intent(getActivity(), Localization.class);
            startActivity(int1);
        }
        else if (position == 2) {
            Intent int2 = new Intent(getActivity(), LocationListener.class);
            startActivity(int2);
        }
        else if (position == 3) {
            Intent int2 = new Intent(getActivity(), Settings.class);
            startActivity(int2);
        }

        }


}
