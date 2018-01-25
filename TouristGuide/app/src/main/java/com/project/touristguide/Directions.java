package com.project.touristguide;

import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.project.touristguide.gps.GPS;

import java.util.ArrayList;

public class Directions extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private static final LatLng IIT = new LatLng(41.8346737, -87.62673399999);
    private static final LatLng BEAN = new LatLng(41.8825978, -87.62420759);
    private double dLongitude, dLatitude;
    private double mLongitude, mLatitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directions);
        Intent i = new Intent();
        dLongitude = i.getDoubleExtra("longitude", 0.0);
        dLatitude = i.getDoubleExtra("latitude", 0.0);


        // Find My Location

        GPS gps = new GPS(getApplicationContext());
        Location gpsLocation = gps.getMyLocation();

        mLatitude = gpsLocation.getLatitude();
        mLongitude = gpsLocation.getLongitude();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        // mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(IIT,   13));
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        ArrayList points = new ArrayList<LatLng>();
        PolylineOptions polyLineOptions = new PolylineOptions();


        points.add(new LatLng(41.8346737, -87.62673399999));
        points.add(new LatLng(41.8467237, -87.62674669999999));
        points.add(new LatLng(41.8474764, -87.62683559999999));
        points.add(new LatLng(41.848427, -87.62688679999999));
        points.add(new LatLng(41.8526516, -87.62699179999999));
        points.add(new LatLng(41.8527629, -87.62602369999999));
        points.add(new LatLng(41.8527944, -87.6238013));
        points.add(new LatLng(41.867173, -87.62413229999999));
        points.add(new LatLng(41.8672151, -87.62406379999999));
        points.add(new LatLng(41.8745063, -87.6241686));
        points.add(new LatLng(41.8769588, -87.62419169999999));
        points.add(new LatLng(41.8771573, -87.62417679999999));
        points.add(new LatLng(41.880097, -87.62427759999999));
        points.add(new LatLng(41.8817596, -87.62420099999999));
        points.add(new LatLng(41.8825978, -87.62420759999999));


        polyLineOptions.addAll(points);
        polyLineOptions.width(16);
        polyLineOptions.color(Color.BLUE);

        googleMap.addPolyline(polyLineOptions);

        mMap.addMarker(new MarkerOptions().position(IIT).title("from:IIT"));
        mMap.addMarker(new MarkerOptions().position(IIT).title("to:BEAN"));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(IIT, 13));


    }
}
