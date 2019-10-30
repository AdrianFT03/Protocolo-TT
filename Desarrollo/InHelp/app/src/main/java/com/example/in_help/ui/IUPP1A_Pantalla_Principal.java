package com.example.in_help.ui;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.in_help.R;

import static com.example.in_help.R.id.MenuPP;

public class IUPP1A_Pantalla_Principal extends AppCompatActivity implements F_IUPP1A_Pantalla_Principal.OnFragmentInteractionListener, F_IUGC1_Gestionar_Contactos.OnFragmentInteractionListener,F_IUGV1_Gestionar_Vehiculos.OnFragmentInteractionListener {

    private BottomNavigationView menu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iupp1_a__pantalla__principal);

        setupActionBar();

        F_IUPP1A_Pantalla_Principal FragmentoPP = new F_IUPP1A_Pantalla_Principal();
        getSupportFragmentManager().beginTransaction().add(R.id.ContyenedorPP, FragmentoPP);


        FragmentTransaction transition1 = getSupportFragmentManager().beginTransaction(); //Es para que se muestre PP con algo
        transition1.replace(R.id.ContyenedorPP, FragmentoPP);
        transition1.commit();


        menu = (BottomNavigationView)findViewById(R.id.bottomNavigationView);

        menu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {


                if (menuItem.getItemId()==R.id.MenuPC){
                    F_IUGC1_Gestionar_Contactos FragmentoGC = new F_IUGC1_Gestionar_Contactos();
                    FragmentTransaction transition0 = getSupportFragmentManager().beginTransaction();
                    transition0.replace(R.id.ContyenedorPP, FragmentoGC);
                    transition0.addToBackStack(null);
                    transition0.commit();
                }else if(menuItem.getItemId()==R.id.MenuPP){
                    F_IUPP1A_Pantalla_Principal FragmentoPP = new F_IUPP1A_Pantalla_Principal();
                    FragmentTransaction transition1 = getSupportFragmentManager().beginTransaction();
                    transition1.replace(R.id.ContyenedorPP, FragmentoPP);
                    transition1.addToBackStack(null);
                    transition1.commit();
                }else if(menuItem.getItemId()==R.id.MenuPV){
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


    private void setupActionBar(){
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null)
        {
            //actionBar.setDisplayHomeAsUpEnabled(true);

            actionBar.setTitle("");
        }
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
