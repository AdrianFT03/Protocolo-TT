package com.example.in_help.ui;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.in_help.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment implements OnMapReadyCallback {

    private View viewroot;
    private GoogleMap gMap;
    private MapView mapView;
    private Float longitud;
    private Float latitud;

    public MapFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        viewroot = inflater.inflate(R.layout.fragment_map,container,false);
        return viewroot;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            longitud = getArguments().getFloat("longitud");
            latitud = getArguments().getFloat("latitud");

        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mapView = (MapView) viewroot.findViewById(R.id.map);
        if(mapView != null){
            mapView.onCreate(savedInstanceState);
            mapView.onResume();
            mapView.getMapAsync(this);
        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        gMap = googleMap;
        LatLng sydney = new LatLng( latitud,longitud);
        gMap.addMarker(new MarkerOptions().position(sydney).title("Choque").draggable(false)); // si es arrastable

        CameraPosition camera= new CameraPosition.Builder()
                .target(sydney)
                .zoom(15)
                .build();
        gMap.animateCamera(CameraUpdateFactory.newCameraPosition(camera));
    }
}
