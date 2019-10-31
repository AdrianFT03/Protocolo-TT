package com.example.in_help.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.in_help.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IUGV1_1_Vehiculos_Registrados extends AppCompatActivity {

    public ListView ListaVehiculos ;
    ArrayList<Datos_IUGV1> Lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iugv1_1__vehiculos__registrados);

        ListaVehiculos = (ListView) findViewById(R.id.IUGV1_listView);
        Lista = new ArrayList<Datos_IUGV1>();
        ObtenerVehiculos2(1);
    }

public void ObtenerVehiculos2(Integer id_usuario){

        APIServer service = Cliente.getAPIServer();

        final DatosVehiculo_IUGV1 datosVehiculo_iugv1 = new DatosVehiculo_IUGV1();

        Call<List<DatosVehiculo_IUGV1>> respuesta = service.ObtenerVehiculos2(id_usuario);

        respuesta.enqueue(new Callback<List<DatosVehiculo_IUGV1>>() {
            @Override
            public void onResponse(Call<List<DatosVehiculo_IUGV1>> call, Response<List<DatosVehiculo_IUGV1>> response) {
                if(!response.isSuccessful()){
                    //Toast.makeText(IUGN5_Configurar_Notificaciones.this, "Chido", Toast.LENGTH_SHORT).show();
                    //Pass.setText("Codigo:"+response.code()
                    // ;
                }


                List<DatosVehiculo_IUGV1> listaDatosVehiculoIUGV1 = response.body();
               listaDatosVehiculoIUGV1.size();
                for (DatosVehiculo_IUGV1 datosBod : listaDatosVehiculoIUGV1){

                    Lista.add(new Datos_IUGV1(datosBod.getId_vehiculo(),datosBod.getNu_placas(), R.mipmap.coche, R.mipmap.outline_build_black_48dp, R.mipmap.baseline_clear_black_48dp));

                }
                Prueba_Adaptador_IUGN5 prueba_adaptador_iugn5 = new Prueba_Adaptador_IUGN5(getApplicationContext(),Lista);

                ListaVehiculos.setAdapter(prueba_adaptador_iugn5);



            }

            @Override
            public void onFailure(Call<List<DatosVehiculo_IUGV1>> call, Throwable t) {

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
