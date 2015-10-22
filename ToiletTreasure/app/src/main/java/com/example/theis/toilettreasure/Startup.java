package com.example.theis.toilettreasure;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Theis on 03-10-2015.
 */
public class Startup extends android.support.v4.app.Fragment implements Runnable {

    static Startup fragmentDerVisesNu = null;
    Handler handler = new Handler();

    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        View root = i.inflate(R.layout.logo_layout, container, false);
        ImageView iv = (ImageView) root.findViewById(R.id.logo);
        iv.setImageResource(R.drawable.logobillede);



        if (savedInstanceState == null) {
            handler.postDelayed(this, 500); // <1> Kør run() om 3 sekunder
        }
        fragmentDerVisesNu = this;

        return root;



    }

    @Override
    public void run() {
        Bundle arg = getArguments();
        android.support.v4.app.Fragment login = new Login();
        login.setArguments(arg);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.hovedlayout, login)
                .addToBackStack(null)
                .commit();
        fragmentDerVisesNu = null;

    }
}
