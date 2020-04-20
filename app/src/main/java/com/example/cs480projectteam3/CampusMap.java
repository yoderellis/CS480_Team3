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
    private final String[] buildings = {"", "LaCava Center", "Bentley Library", "Morison Hall",
            "Adamian Academic Center", "Smith Academic Technology Center", "Lindsay Hall",
            "Jennison Hall", "Stratton House", "Boylston A", "Boylston B", "Rhodes Hall",
            "Collins Hall", "Kresge Hall", "Forest Hall", "Miller Hall", "Falcone North",
            "Falcone West", "Falcone East", "Slade Hall", "Tree Dorms", "Orchard North",
            "Castle Hall", "Cape House", "Orchard South", "Copley South", "Copley North",
            "Fenway Hall", "North A", "North B", "North C", "North D", "Harrington House",
            "Student Center", "Lewis Hall", "Dana Center", "Dovecote", "Arena", "University Police",
            "Counseling Center", "Rauch Administration Center", "President's House"};
    private final double[] lats = {42.386934, 42.388884, 42.387965, 42.387902, 42.387154, 42.387232,
            42.387276, 42.388070, 42.385203, 42.386229, 42.386298, 42.386370, 42.386786, 42.386240,
            42.386640, 42.387735, 42.387736, 42.387429, 42.387455, 42.386007, 42.386160, 42.385155,
            42.385010, 42.385123, 42.384802, 42.384616, 42.384988, 42.384159, 42.394864, 42.394642,
            42.394081, 42.394285, 42.385187, 42.385906, 42.384225, 42.383430, 42.384378, 42.384867,
            42.385792, 42.385977, 42.388551, 42.389151};
    private final double[] longs = {-71.221603, -71.220267, -71.219907, -71.218929, -71.218706,
            -71.220437, -71.219702, -71.220817, -71.218255, -71.221798, -71.222086, -71.222724,
            -71.222425, -71.223959, -71.224290, -71.223143, -71.221705, -71.221941, -71.221379,
            -71.220538, -71.219700, -71.224239, -71.223746, -71.223675, -71.224386, -71.222658,
            -71.223156, -71.223196, -71.214280, -71.213620, -71.213823, -71.214432, -71.216558,
            -71.222795, -71.224819, -71.224587, -71.224040, -71.220456, -71.221010, -71.221140,
            -71.221244, -71.222797};

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
        ArrayAdapter<String> buildAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, buildings);

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
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(42.386934, -71.221603), zoom));

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
        double[] acadLats = {42.388884, 42.387965, 42.387902, 42.387154, 42.387232, 42.387276,
                42.388070};
        double[] acadLongs = {-71.220267, -71.219907, -71.218929, -71.218706, -71.220437,
                -71.219702, -71.220817};
        String[] acadTitles = {"LaCava Center", "Bentley Library", "Morison Hall",
                "Adamian Academic Center", "Smith Academic Technology Center", "Lindsay Hall",
                "Jennison Hall"};
        String[] acadSnips = {"Conference Center, McCallum Graduate School of Business, " +
                "Admissions, Career Services, Lower Cafe and Eatery, Bank of America ATM",
                "IT Help Desk, Writing Center, ESOL Center, RSM Art Gallery, Einstein Bros, " +
                        "Meeting Rooms, Study Spaces", "Wilder Pavilion, Service Learning Center, " +
                        "Center for Marketing Technology, Academic Departments",
                "Cronin Office of International Education, CLIC Lab, Eco-Fi Stat Learning Center, " +
                        "Valente Center, Center for Business Ethics, Classrooms, Academic Departments",
                "Classrooms, Trading Room, CIS Sandbox, User Experience Center",
                "Koumantzelis Auditorium, ACELAB, Media and Culture Lab, Lecture Halls",
                "Academic Services, Mathematics Learning Center, Classrooms, Labs"};
        double[] resLats = {42.385203, 42.386229, 42.386298, 42.386370, 42.386786, 42.386240,
                42.386640, 42.387735, 42.387736, 42.387429, 42.387455, 42.386007, 42.386160,
                42.385155, 42.385010, 42.385123, 42.384802, 42.384616, 42.384988, 42.384159,
                42.394864, 42.394642, 42.394081, 42.394285};
        double[] resLongs = {-71.218255, -71.221798, -71.222086, -71.222724, -71.222425, -71.223959,
                -71.224290, -71.223143, -71.221705, -71.221941, -71.221379, -71.220538, -71.219700,
                -71.224239, -71.223746, -71.223675, -71.224386, -71.222658, -71.223156, -71.223196,
                -71.214280, -71.213620, -71.213823, -71.214432};
        String[] resTitles = {"Stratton House", "Boylston A", "Boylston B", "Rhodes Hall",
                "Collins Hall", "Kresge Hall", "Forest Hall", "Miller Hall", "Falcone North",
                "Falcone West", "Falcone East", "Slade Hall", "Tree Dorms", "Orchard North",
                "Castle Hall", "Cape House", "Orchard South", "Copley South", "Copley North",
                "Fenway Hall", "North A", "North B", "North C", "North D"};
        String[] resSnips = {"", "", "","Health Center", "Mail Stop, Bookstore, Falcon Mart, " +
                        "Bank of America ATM", "", "", "Arts Community, " +
                        " Brave: The Social Justice Community, Herstory: Women Leaders of Today",
                "Honors Upper-class Experience", "", "", "Blueprint Community, " +
                "Honors First-Year Experience, Thrive Community", "Cedar|Maple|Oak|Spruce|Elm, " +
                "The Blue Line", "Global Living Community", "", "", "", "", "",
                "Emerging Leaders Community, SkyBox", "", "", "", ""};
        double[] otherLats = {42.385187, 42.385906, 42.384225, 42.383430, 42.384378, 42.384867,
                42.385792, 42.385977, 42.388551, 42.389151};
        double[] otherLongs = {-71.216558, -71.222795, -71.224819, -71.224587, -71.224040,
                -71.220456, -71.221010, -71.221140, -71.221244, -71.222797};
        String[] otherTitles = {"Harrington House", "Student Center", "Lewis Hall", "Dana Center",
                "Dovecote", "Arena", "University Police", "Counseling Center",
                "Rauch Administration Center", "President's House"};
        String[] otherSnips = {"Procurement and Campus Services", "921 Dining Hall, Harry's Pub, " +
                "Dunkin Donuts, The Bubble, Living Room, Study Spaces, Santander ATM", "",
                "Fitness/Weight Room, Pool, Dance Room, Curritos, The Nest",
                "Facilities Management", "The Cube", "", "", "Student Financial Services, " +
                "Cashier, Registrar",""};

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
        for (int i=0; i<otherLats.length; i++) {
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
