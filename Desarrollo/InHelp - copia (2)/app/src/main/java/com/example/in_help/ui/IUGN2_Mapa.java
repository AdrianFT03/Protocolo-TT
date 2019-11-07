package com.example.in_help.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.in_help.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class IUGN2_Mapa extends FragmentActivity implements OnMapReadyCallback ,LocationListener{

    private GoogleMap mMap;
    private Location currentLocation;
    private LocationManager locationManager;
    private Marker marker;
    private double Lo1,La1;
    private double km,hrs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iugn2__mapa);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        locationManager = (LocationManager) getBaseContext().getSystemService(LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(false);

        //locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 0, this);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 0, this);


    }

    @Override
    public void onLocationChanged(Location location) {
        //Toast.makeText(this, "OK: "+location.getProvider(), Toast.LENGTH_SHORT).show();
        Lo1 = location.getLongitude();
        La1 = location.getLatitude();
        km=0;
        if (marker == null) {
            marker = mMap.addMarker(new MarkerOptions().position(new LatLng(location.getLatitude(),location.getLongitude())));
        }else {
            marker.setPosition(new LatLng(location.getLatitude(), location.getLongitude()));
            //Toast.makeText(this, "Latitud : " + La1 + "\n" +"Longitud : " + Lo1 , Toast.LENGTH_SHORT).show();

            Location location1 = new Location(locationManager.GPS_PROVIDER);
            location.distanceTo(location1);
            location1.getLatitude();
            location1.getLongitude();

            km = location.distanceTo(location1);
            //km /= 1000;

            Toast.makeText(this, "Distancia aprox : " +km+ "km.", Toast.LENGTH_SHORT).show();
        }



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
