package com.example.theis.toilettreasure;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.provider.Settings;

import android.widget.ScrollView;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

/**
 * Created by Reskesen on 29-10-2015.
 */
public class Localization extends Activity implements LocationListener{

    // IGNORER EVENTUELLE FEJL MED locationManager.blablabla  !!!

    TextView textView;
    ScrollView scrollView;
    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textView = new TextView(this);
        scrollView = new ScrollView(this);
        scrollView.addView(textView);

        setContentView(scrollView);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

    }

    @Override
    protected void onResume() {
        super.onResume();

        Criteria kriterium = new Criteria();
        kriterium.setAccuracy(Criteria.ACCURACY_FINE);
        String udbyder = locationManager.getBestProvider(kriterium, true); // giver "gps" hvis den er slået til

        textView.append("\n\n Benytter følgende type udbyder: " + udbyder + "\n\n");

        if (udbyder == null) {
            textView.append("\n\nFejl! Der var ikke tændt for nogen udbyder. Tænd for GPS eller netværksbaseret stedplacering og prøv igen.");
            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            return;
        }

        locationManager.requestLocationUpdates(udbyder, 60000, 20, this);

        Location sted = locationManager.getLastKnownLocation(udbyder);

        if (sted != null) {
            try {
                Geocoder geocoder = new Geocoder(this);
                List<Address> adresser = geocoder.getFromLocation(sted.getLatitude(), sted.getLongitude(), 1);
                if (adresser != null && adresser.size() > 0) {
                    Address adresse = adresser.get(0);
                    textView.append("NÆRMESTE ADRESSE: \n" + adresse + "\n\n");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        locationManager.removeUpdates(this);
    }

    public void onLocationChanged(Location sted) {
        textView.append(sted + "\n\n");
        scrollView.scrollTo(0, textView.getHeight());
    }

    public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
    }

    public void onProviderEnabled(String arg0) {
    }

    public void onProviderDisabled(String arg0) {
    }
}
