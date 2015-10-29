package com.example.theis.toilettreasure;

import android.location.Location;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;

public interface LocationListener {


    void onLocationChanged(Location location);


    void onStatusChanged(String provider, int status, Bundle extras);


    void onProviderEnabled(String provider);


    void onProviderDisabled(String provider);
}
