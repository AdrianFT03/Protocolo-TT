package com.example.in_help;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.in_help.R;
import com.example.in_help.ui.Bluetooth_MainActivity;
import com.example.in_help.ui.F_IUGC1_Gestionar_Contactos;
import com.example.in_help.ui.F_IUGV1_Gestionar_Vehiculos;
import com.example.in_help.ui.F_IUPP1A_Pantalla_Principal;
import com.example.in_help.ui.Fragmento1;
import com.example.in_help.ui.Fragmento2;
import com.example.in_help.ui.Fragmento3;
import com.example.in_help.ui.IUA1_5_FinalizarRegistro;
import com.example.in_help.ui.IUDM1_Datos_Medicos;
import com.example.in_help.ui.IUDP1_Datos_Personales;
import com.example.in_help.ui.IUDP2_Datos_de_la_Cuenta;
import com.example.in_help.ui.IUGN1_Gestionar_Notificaciones;
import com.example.in_help.ui.IUGN5_Configurar_Notificaciones;
import com.example.in_help.ui.IUGN6A_Configurar_Notificacion;
import com.example.in_help.ui.IUGN6_Configurar_Notificaciones;

public class Prueba_MenuLateral2 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private BottomNavigationView menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prueba__menu_lateral2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layoutPrueba);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


        setupActionBar();



        CargarFragmentos(new F_IUPP1A_Pantalla_Principal());





        menu = (BottomNavigationView)findViewById(R.id.bottomNavigationView);

        menu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {


                if (menuItem.getItemId()== R.id.MenuPC){
                    CargarFragmentos(new F_IUGC1_Gestionar_Contactos());
                }else if(menuItem.getItemId()== R.id.MenuPP){
                    CargarFragmentos(new F_IUPP1A_Pantalla_Principal());



                }else if(menuItem.getItemId()== R.id.MenuPV){

                    CargarFragmentos(new F_IUGV1_Gestionar_Vehiculos());
                }

                return true;

            }
        });

    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layoutPrueba);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.prueba__menu_lateral2, menu);
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
            GoBT();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            GestionarNotifica();
        } else if (id == R.id.nav_gallery) {
            ConfiguraNotifica();
        } else if (id == R.id.nav_slideshow) {
            DatosPersonales();
        } else if (id == R.id.nav_tools) {
            DatosCuenta();
        } else if (id == R.id.nav_share) {
            DatosMedicos();

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layoutPrueba);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void CargarFragmentos (Fragment fragmento){



        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.ContenedorFragmentos,fragmento).commit();

    }
    private void setupActionBar(){
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null)
        {
            //actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("");
        }
    }

    public void GestionarNotifica(){
        Intent GoFinalizarRegistro = new Intent(this, IUGN1_Gestionar_Notificaciones.class);
        startActivity(GoFinalizarRegistro);
    }
    public void ConfiguraNotifica(){
        Intent GoFinalizarRegistro = new Intent(this, IUGN5_Configurar_Notificaciones.class);
        startActivity(GoFinalizarRegistro);
    }
    public void DatosPersonales(){
        Intent GoFinalizarRegistro = new Intent(this, IUDP1_Datos_Personales.class);
        startActivity(GoFinalizarRegistro);
    }
    public void DatosCuenta(){
        Intent GoFinalizarRegistro = new Intent(this, IUDP2_Datos_de_la_Cuenta.class);
        startActivity(GoFinalizarRegistro);
    }

    public void DatosMedicos(){
        Intent GoFinalizarRegistro = new Intent(this, IUDM1_Datos_Medicos.class);
        startActivity(GoFinalizarRegistro);
    }
    public void GoBT(){
        Intent GoBT = new Intent(this, Bluetooth_MainActivity.class);
        startActivity(GoBT);
    }
}
