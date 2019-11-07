package com.example.in_help.ui;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.in_help.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IUGN1_Gestionar_Notificaciones extends AppCompatActivity {


    ArrayList<Datos_IUGN1> Lista;
    ListView listaDatos;
    Integer tipo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setupActionBar();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iugn1__gestionar__notificaciones);

        listaDatos = (ListView) findViewById(R.id.IUGN1listview);
        ObtenerNotificaciones(1);

    }

    public void ObtenerNotificaciones(Integer id_usuario){
        APIServer server = Cliente.getAPIServer();

        DatosNotificacion_IUGN1 datosNotificacion_iugn1 = new DatosNotificacion_IUGN1();

        Call<List<DatosNotificacion_IUGN1>> respuesta = server.ObtenerNotifiaciones(id_usuario);

        respuesta.enqueue(new Callback<List<DatosNotificacion_IUGN1>>() {
            @Override
            public void onResponse(Call<List<DatosNotificacion_IUGN1>> call, Response<List<DatosNotificacion_IUGN1>> response) {

                Lista = new ArrayList<Datos_IUGN1>();
                List<DatosNotificacion_IUGN1> listaDatosNotificacionIUNG1 = response.body();
                listaDatosNotificacionIUNG1.size();

                for (DatosNotificacion_IUGN1 datosBod : listaDatosNotificacionIUNG1){

                    if(datosBod.getId_tipo() == 1){
                        tipo = R.mipmap.vehiculov;
                        ;
                    }else if(datosBod.getId_tipo() == 2){
                        tipo = R.mipmap.vehiculoa;
                    }else if(datosBod.getId_tipo() ==3){
                        tipo = R.mipmap.vehiculor;
                    }

                    Lista.add(new Datos_IUGN1(datosBod.getId_notifiacion(),tipo,datosBod.getNu_placas(),datosBod.getFh_notificacion(),R.mipmap.sharp_more_horiz_black_480,datosBod.getLongitud(),datosBod.getLatitud()));

                }
                Adaptador_IUGN1 adaptador_iugn1 = new Adaptador_IUGN1(getApplicationContext(),Lista);

                listaDatos.setAdapter(adaptador_iugn1);
            }


            @Override
            public void onFailure(Call<List<DatosNotificacion_IUGN1>> call, Throwable t) {

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
}
