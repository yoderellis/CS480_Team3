package com.example.cs480projectteam3;

import androidx.fragment.app.FragmentActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

public class CampusMap extends FragmentActivity implements AdapterView.OnItemSelectedListener, OnMapReadyCallback {

    private Spinner spin;
    private final String[] buildings = {"", "Smith Academic Technology Center", "Student Center"};
    private final double[] lats = {42.386934, 42.388060, 42.385906};
    private final double[] longs = {-71.221603, -71.220845, -71.222795};

    private GoogleMap mMap;
    private static final float zoom = 17f;
    private String TAG = "Map";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campus_map);

        // Assign spinner widget and set onclicklistener
        spin = (Spinner) findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);

        //Create an ArrayAdapter and a default spinner layout
        ArrayAdapter<String> buildAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                buildings);

        //Specify the layout to use when the list of choices appears
        buildAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(buildAdapter);  //connect ArrayAdapter to <Spinner>

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Center map and set zoom level
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(42.386934, -71.221603), zoom));

        // Set Google Map style
        try {
            boolean success = mMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            this, R.raw.map_styles));

            if (!success) {
                Log.e(TAG, "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e(TAG, "Can't find style. Error: ", e);
        }

        // Arrays for marker info
        double[] acadLats = {42.388060, 42.387965};
        double[] acadLongs = {-71.220845, -71.219907};
        String[] acadTitles = {"Smith Academic Technology Center", "Library"};
        String[] acadSnips = {"Classrooms, Trading Room, CIS Sandbox",
                "IT Help Desk, Meeting Rooms, Study Spaces"};
        double[] resLats = {42.386370};
        double[] resLongs = {-71.222724};
        String[] resTitles = {"Rhodes Hall"};
        String[] resSnips = {"Health Center"};
        double[] otherLats = {42.385906};
        double[] otherLongs = {-71.222795};
        String[] otherTitles = {"Student Center"};
        String[] otherSnips = {"921 Dining Hall, Harry's Pub, Dunkin Donuts, The Bubble, Living Room, Study Spaces"};

        // Set markers
        for (int i=0; i<acadLats.length; i++) {
            mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(acadLats[i], acadLongs[i]))
                    .title(acadTitles[i])
                    .snippet(acadSnips[i])
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.academic)));
        }
        for (int i=0; i<resLats.length; i++) {
            mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(resLats[i], resLongs[i]))
                    .title(resTitles[i])
                    .snippet(resSnips[i])
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.residential)));
        }
        for (int i=0; i<resLats.length; i++) {
            mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(otherLats[i], otherLongs[i]))
                    .title(otherTitles[i])
                    .snippet(otherSnips[i])
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.other)));
        }
    }

    // Go to location of building
    public void onItemSelected(AdapterView<?> parent, View v, int pos, long id) {
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lats[pos], longs[pos]), zoom));
    }

    public void onNothingSelected(AdapterView<?> parent) {
    }
}
