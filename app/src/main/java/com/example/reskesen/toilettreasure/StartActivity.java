package com.example.reskesen.toilettreasure;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Display;
import android.view.Window;

public class StartActivity extends FragmentActivity {

    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);
        Display display = getWindowManager().getDefaultDisplay();
        int height = display.getHeight();
        Bundle arg = new Bundle();
        arg.putInt("height", height);


        if (savedInstanceState == null) {
            android.support.v4.app.Fragment fragment = new Login();
            fragment.setArguments(arg);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.startlayout, fragment) // tom container i layout
                    .commit();
        }

    }


}