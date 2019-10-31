package com.example.in_help.ui;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.in_help.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class IUPP1A_Pantalla_Principal
        extends
        AppCompatActivity
        //implements
        //F_IUPP1A_Pantalla_Principal.OnFragmentInteractionListener,
        //F_IUGC1_Gestionar_Contactos.OnFragmentInteractionListener,
        //F_IUGV1_Gestionar_Vehiculos.OnFragmentInteractionListener
        {

    private static final String TAG = "Log : ";
    private BottomNavigationView menu;


    private String content;
    private Integer id_bitacora;
    private Bundle bundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iupp1_a__pantalla__principal);



        setupActionBar();

         bundle = new Bundle();


        F_IUPP1A_Pantalla_Principal FragmentoPP = new F_IUPP1A_Pantalla_Principal();
        getSupportFragmentManager().beginTransaction().add(R.id.ContyenedorPP, FragmentoPP);


        FragmentTransaction transition1 = getSupportFragmentManager().beginTransaction(); //Es para que se muestre PP con algo
        transition1.replace(R.id.ContyenedorPP, FragmentoPP);
        transition1.commit();




        menu = (BottomNavigationView) findViewById(R.id.bottomNavigationView);


        ObtenerBitacora(1);
        Log.d(TAG, "id_bitacora: "+id_bitacora);

        // Hilos Prueba = new Hilos(this,progressbar);
        // Prueba.execute();


        menu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {


                if (menuItem.getItemId() == R.id.MenuPC) {
                    F_IUGC1_Gestionar_Contactos FragmentoGC = new F_IUGC1_Gestionar_Contactos();
                    FragmentTransaction transition0 = getSupportFragmentManager().beginTransaction();
                    transition0.replace(R.id.ContyenedorPP, FragmentoGC);
                    transition0.addToBackStack(null);
                    transition0.commit();
                } else if (menuItem.getItemId() == R.id.MenuPP) {
                    F_IUPP1A_Pantalla_Principal FragmentoPP = new F_IUPP1A_Pantalla_Principal();
                    FragmentTransaction transition1 = getSupportFragmentManager().beginTransaction();
                    transition1.replace(R.id.ContyenedorPP, FragmentoPP);
                    transition1.addToBackStack(null);
                    transition1.commit();
                } else if (menuItem.getItemId() == R.id.MenuPV) {
                    F_IUGV1_Gestionar_Vehiculos FragmentoGV = new F_IUGV1_Gestionar_Vehiculos();
                    FragmentTransaction transition2 = getSupportFragmentManager().beginTransaction();
                    transition2.replace(R.id.ContyenedorPP, FragmentoGV);
                    transition2.addToBackStack(null);
                    transition2.commit();
                }

                return true;
            }


        });


    }




    ///////////////////////////////////////////////////////

    private void ObtenerBitacora(Integer usuario) {
        APIServer service = Cliente.getAPIServer();

        final DatosBitacora_IUPP1A1 bitacora_iupp1Au = new DatosBitacora_IUPP1A1();

        Call<List<DatosBitacora_IUPP1A1>> call = service.ObtenerBitacoraU(usuario);

        call.enqueue(new Callback<List<DatosBitacora_IUPP1A1>>() {
            @Override
            public void onResponse(Call<List<DatosBitacora_IUPP1A1>> call, retrofit2.Response<List<DatosBitacora_IUPP1A1>> response) {

                Log.d(TAG, "onResponse: ");
                content =  "";
                List<DatosBitacora_IUPP1A1> listPost = response.body();
                listPost.size();
                for(DatosBitacora_IUPP1A1 BitacoraRequest: listPost){

                    content = BitacoraRequest.getId_bitacora().toString();

                }

                id_bitacora = Integer.parseInt(content);


            }

            @Override
            public void onFailure(Call<List<DatosBitacora_IUPP1A1>> call, Throwable t) {
                Log.d(TAG, "onFailure: ");

            }
        });

    }





    private void setupActionBar(){
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null)
        {
            //actionBar.setDisplayHomeAsUpEnabled(true);

            actionBar.setTitle("");
        }
    }



    ////////////////////////////


}
