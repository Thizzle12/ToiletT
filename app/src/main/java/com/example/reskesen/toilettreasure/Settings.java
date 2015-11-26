package com.example.reskesen.toilettreasure;

/**
 * Created by Reskesen on 05-11-2015.
 */
import android.os.Bundle;
import android.preference.PreferenceActivity;
import com.example.reskesen.toilettreasure.R;

public class Settings extends PreferenceActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.layout.settings);
    }
}
