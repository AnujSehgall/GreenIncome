package com.anuj.greenincome;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import static com.anuj.greenincome.R.id.map;

public class Recycler extends AppCompatActivity implements OnMapReadyCallback, NavigationView.OnNavigationItemSelectedListener{

    private GoogleMap mMap;
    public int i;
    private TrackGPS gps;
    double longitude, newLat, newLng, lat_url,lng_url,lat,lng;
    double latitude;
    private static String TAG = Carpool.class.getSimpleName();
    private ProgressDialog pDialog;
    public LatLng newlatlng,latLng,yellatlng;
    public ImageView et_dest;
    public String destination;
    public EditText dest;
    public Marker j1,j2,j3,i1,i2,i3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (ContextCompat.checkSelfPermission(Recycler.this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(Recycler.this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION)) {
            } else {
                ActivityCompat.requestPermissions(Recycler.this,
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},i);
            }
        }
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(map);
        mapFragment.getMapAsync(this);

        //yellatlng= "13.119167, 77.635861"
        lat =13.119167; lng= 77.635861;
        yellatlng= new LatLng(lat, lng);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);

        gps = new TrackGPS(Recycler.this);


        if(gps.canGetLocation()){


            longitude = gps.getLongitude();
            latitude = gps .getLatitude();

            // Toast.makeText(getApplicationContext(),"Longitude:"+Double.toString(longitude)+"\nLatitude:"+Double.toString(latitude),Toast.LENGTH_SHORT).show();
        }
        else
        {

            gps.showSettingsAlert();
        }
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        /*SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    */



        et_dest = (ImageView) findViewById(R.id.des_btn);
        et_dest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftKeyboard();
                //Toast.makeText(getApplicationContext(), destination,Toast.LENGTH_SHORT).show();


                    j1 = mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(13.119821, 77.632499))
                            .infoWindowAnchor(0.5f, 0.5f)
                            .title("Mehta Recyclers")
                            .snippet("3 km away")
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
                    ///mMap.setInfoWindowAdapter(new MyInfoWindowAdapter());


                    mMap.animateCamera(CameraUpdateFactory.zoomTo(13), 2000, null);


                    j3 = mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(13.105748, 77.634098))
                            .anchor(0.5f, 0.5f)
                            .title("Aavishkar Scrap dealers")
                            .snippet("2.2 km away")
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
                    i1 = mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(13.123873, 77.641645))
                            .anchor(0.5f, 0.5f)
                            .title("Ravi Scraps")
                            .snippet("3.1 km away")
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));

                    i3 = mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(13.112254, 77.605357))
                            .anchor(0.5f, 0.5f)
                            .title("Green Scrappers")
                            .snippet("3.6 km away")
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));


            }
        });}
        //newlatlng = new LatLng(lat_url, lng_url);


    private void hideSoftKeyboard(){
        if(getCurrentFocus()!=null && getCurrentFocus() instanceof EditText){
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(et_dest.getWindowToken(), 0);
        }
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

        Geocoder gc = new Geocoder(Recycler.this);

        List<Address> list = null;
        try {
            list = gc.getFromLocation(latitude,longitude,1);
            newLat = latitude;
            newLng = longitude;
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        android.location.Address add =   list.get(0);
        String addressLine1 = add.getAddressLine(1);
        String addressLine2 = add.getAddressLine(2);
        latLng= new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(latLng).title(addressLine1).snippet(addressLine2)).setVisible(true);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 22));

        // Zoom in, animating the camera.
        mMap.animateCamera(CameraUpdateFactory.zoomTo(16), 2000, null);
    }

    private void addOldMark(double lat, double lng) {
        LatLng ll = new LatLng(lat, lng);

        Geocoder gc = new Geocoder(Recycler.this);

        List<android.location.Address> list = null;

        //LatLng latLng = marker.getPosition();

        try {
            list = gc.getFromLocation(lat_url, lng_url,1);

        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        android.location.Address add =   list.get(0);
        String addressLine1 = add.getAddressLine(0);
        String addressLine2 = add.getAddressLine(2);
        mMap.addMarker(new MarkerOptions().position(ll).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)).title("Child's location ").snippet(addressLine1));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ll, 22));

        // Zoom in, animating the camera.
        mMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);


        float[] results = new float[1];
        Location.distanceBetween(latitude, longitude, lat_url, lng_url, results);
        float distance = results[0];

        double dist = (double) distance/1000;
        DecimalFormat f = new DecimalFormat("##.00");

        Toast.makeText(getApplicationContext(), "Distance to Travel is: "+f.format(dist)+" km",Toast.LENGTH_LONG).show();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
