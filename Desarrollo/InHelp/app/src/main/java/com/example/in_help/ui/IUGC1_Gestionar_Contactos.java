package com.example.in_help.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.in_help.R;

public class IUGC1_Gestionar_Contactos extends AppCompatActivity {
    private BottomNavigationView MenuP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iugc1__gestionar__contactos);

        MenuP = (BottomNavigationView) findViewById(R.id.MenuP);


        MenuP.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {


                if(item.getItemId() == R.id.MenuPP){
                    //Toast.makeText(IUPP1A_Pantalla_Principal.this, "Menu2", Toast.LENGTH_SHORT).show();
                    GoHome();
                }
                if(item.getItemId() == R.id.MenuPC){
                    //Toast.makeText(IUPP1A_Pantalla_Principal.this, "Menu1", Toast.LENGTH_SHORT).show();
                    GoContactos();

                }
                if(item.getItemId() == R.id.MenuPV){
                    //Toast.makeText(IUPP1A_Pantalla_Principal.this, "Menu3", Toast.LENGTH_SHORT).show();
                    //GoVehiculos1();
                }

            }
        });
    }

    private void GoContactos() {
        Intent GoContactos = new Intent(this, IUGC1_Gestionar_Contactos.class);
        startActivity(GoContactos);
    }

    private void GoHome() {
        Intent GoHome = new Intent(this, IUPP1A_Pantalla_Principal.class);
        startActivity(GoHome);
    }

    private void GoVehiculos1() {
        Intent GoVehiculos1 = new Intent(this, IUGV1_Gestionar_Vehiculos.class);
        startActivity(GoVehiculos1);
    }
}
