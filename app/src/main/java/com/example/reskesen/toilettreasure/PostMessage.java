package com.example.reskesen.toilettreasure;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.*;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.reskesen.toilettreasure.R;

import java.io.IOException;
import java.lang.Override;import java.lang.String;import java.lang.System;
import java.util.List;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

/**
 * Created by Reskesen on 05-11-2015.
 */
public class PostMessage extends Activity implements View.OnClickListener, android.location.LocationListener {

    boolean useLocation = true;
    Button post;
    EditText message;
    Firebase firebase;
    LocationManager locationManager;
    String location;
    String username;
    int spiritanimal;

    long count;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("Jeg er nået ind i oncreate");
        setContentView(R.layout.post_message);
        Firebase.setAndroidContext(this);
        Intent i = getIntent();
        username = i.getExtras().getString("username");
        spiritanimal = i.getExtras().getInt("spiritanimal");
        post = (Button) findViewById(R.id.post);
        message = (EditText) findViewById(R.id.message);
        location = "Location: unknown";

        firebase = new Firebase("https://vivid-inferno-5770.firebaseio.com/");
        System.out.println("Jeg har oprettet firebaseforbindelse");
        post.setOnClickListener(this);


        locationManager  = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        System.out.println("LocationManager er oprettet");

        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this.getBaseContext());
        useLocation = prefs.getBoolean("localization", true);

        System.out.println("Slut på oncreate");

    }

    @Override
    public void onClick(View v) {
        if(v == post) {
            final String text = message.getText().toString();

            final Firebase ref = firebase.child("PostCount");
            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    //dataSnapshot.getChildren();

                    try {
                        count = (long) dataSnapshot.getValue();

                        System.out.println(count);
                        count++;
                        Firebase ref2 = firebase.child("Posts").child("" + count);

                        Post post = new Post(text, location, username, 0, spiritanimal);

                        ref2.setValue(post);

                        ref.setValue(count);

                    } catch (Exception e) {
                        System.out.println("ERROR");
                        System.out.println(e.getMessage());
                    }
                }


                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (useLocation) {

            final Geocoder geocoder = new Geocoder(this);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);

            new AsyncTask() {
                @Override
                protected Object doInBackground(Object... arg0) {
                    try {


                        Criteria kriterium = new Criteria();
                        kriterium.setAccuracy(Criteria.ACCURACY_FINE);
                        String udbyder = locationManager.getBestProvider(kriterium, true); // giver "gps" hvis den er slået til


                        if (udbyder == null) {
                            //  textView.append("\n\nError! You haven't turned on any kind oflocalization providers. Turn on GPS or network-based localization and try again.");
                            startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                        }
/*
                    Location sted;
                    while(true){
                        sted = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

                        if(sted != null){
                            break;
                        }

                    }
                    */
                        Location sted = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        //System.out.println(sted.toString());

                        if (sted != null) {
                            try {

                                List<Address> adresser = geocoder.getFromLocation(sted.getLatitude(), sted.getLongitude(), 1);

                                System.out.println(adresser.toString());

                                return adresser;
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                        return e;
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Object adresser2) {

                    if (adresser2 == null) {
                        location = "Location: unknown";
                    } else {
                        System.out.println("adresser er IKKE lig med null");
                        List<Address> adresser = (List<Address>) adresser2;
                        if (adresser != null && adresser.size() > 0) {
                            Address adresse = adresser.get(0);
                            location = "Location: " + adresse.getLocality();
                            System.out.println("location " + location);
                            //   textView.append("Current location: \n" + adresse.getLocality() + "\n\n");
                        }
                    }

                }
            }.execute();
        }else {
            location = "Location: secret";
        }



    }

    @Override
    protected void onPause() {
        super.onPause();

        locationManager.removeUpdates(this);
    }


    @Override
    public void onLocationChanged(Location location) {
        System.out.println("location changed");
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}