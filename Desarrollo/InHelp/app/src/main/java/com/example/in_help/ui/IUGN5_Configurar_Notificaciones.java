package com.example.in_help.ui;

import android.app.AlertDialog;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.in_help.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class IUGN5_Configurar_Notificaciones extends AppCompatActivity {


    public ListView listaDatos;
    ArrayList<Datos_IUGN5> Lista;
    Button confNot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setupActionBar();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iugn5__configurar__notificaciones);

        listaDatos = (ListView) findViewById(R.id.IUGN5_listView);


        ObtenerVehiculos(1);


    }

    public void ObtenerVehiculos(Integer id_usuario){

        APIServer service = Cliente.getAPIServer();

        final DatosVehiculo_IUGN5 datosVehiculoIUGN5 = new DatosVehiculo_IUGN5();

        Call<List<DatosVehiculo_IUGN5>> respuesta = service.ObtenerVehiculos(id_usuario);

        respuesta.enqueue(new Callback<List<DatosVehiculo_IUGN5>>() {
            @Override
            public void onResponse(Call<List<DatosVehiculo_IUGN5>> call, Response<List<DatosVehiculo_IUGN5>> response) {
                if(!response.isSuccessful()){
                    //Toast.makeText(IUGN5_Configurar_Notificaciones.this, "Chido", Toast.LENGTH_SHORT).show();
                    //Pass.setText("Codigo:"+response.code()
                    // ;
                }

                Lista = new ArrayList<Datos_IUGN5>();
                List<DatosVehiculo_IUGN5> listaDatosVehiculoIUGN5 = response.body();
                listaDatosVehiculoIUGN5.size();
                for (DatosVehiculo_IUGN5 datosBod : listaDatosVehiculoIUGN5){

                    Lista.add(new Datos_IUGN5(datosBod.getId_vehiculo(),datosBod.getNu_placas(),R.mipmap.coche,R.mipmap.sharp_more_horiz_black_480));

                }
                Adaptador_IUGN5 adaptador_iugn5 = new Adaptador_IUGN5(getApplicationContext(),Lista);

                listaDatos.setAdapter(adaptador_iugn5);



            }

            @Override
            public void onFailure(Call<List<DatosVehiculo_IUGN5>> call, Throwable t) {

            }
        });
    }

    private void setupActionBar(){
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("");
        }
    }


    public  void GoInicio(){
        Intent GoInicio = new Intent(this, IUPP1A_Pantalla_Principal.class);
        startActivity(GoInicio);
    }



}
