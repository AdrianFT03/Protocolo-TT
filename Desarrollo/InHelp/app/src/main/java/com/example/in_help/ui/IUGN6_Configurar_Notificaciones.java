package com.example.in_help.ui;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.in_help.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IUGN6_Configurar_Notificaciones extends AppCompatActivity {

    TextView placas;
    ListView listaDatos;
    ArrayList<Datos_IUNG6> Lista;
    Boolean estado=true;
    Integer tipo=0;
    String nombre;
    String primer;
    String segundo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iugn6__configurar__notificaciones);
        setupActionBar();
        placas = (TextView) findViewById(R.id.IUGN6textView);

        Datos_IUGN5 objeto = (Datos_IUGN5) getIntent().getExtras().getSerializable("DatosAnt");

        listaDatos = (ListView) findViewById(R.id.IUGN6_listview);

        String id_coche = Integer.toString(objeto.getId());

        placas.setText(objeto.getPlacas());

        ObtenerContactos(1,objeto.getId());



    }


    public void ObtenerContactos(Integer id_usuario, Integer id_vehiculo){

        final APIServer service = Cliente.getAPIServer();
        final DatosContactos_IUGN6 datosContactos_iugn6 = new DatosContactos_IUGN6();
        Call<List<DatosContactos_IUGN6>> respuesta = service.ObtenerContactos(id_usuario,id_vehiculo);

        respuesta.enqueue(new Callback<List<DatosContactos_IUGN6>>() {
            @Override
            public void onResponse(Call<List<DatosContactos_IUGN6>> call, Response<List<DatosContactos_IUGN6>> response) {

                Lista = new ArrayList<Datos_IUNG6>();

                List<DatosContactos_IUGN6> listaDatosContactosIUGN6 = response.body();

                listaDatosContactosIUGN6.size();

                for (DatosContactos_IUGN6 datosBod : listaDatosContactosIUGN6){

                    if(datosBod.getId_estado() == 1){
                        estado = true;
                    }else estado = false;

                    if(datosBod.getId_tipo() == 1){//familiar
                        tipo = R.mipmap.group;
                    }else if(datosBod.getId_tipo() == 2){//Aseguradora
                        tipo = R.mipmap.baseline_business_center_black_48;
                    }else if(datosBod.getId_tipo() == 3){// emergencia
                        tipo = R.mipmap.baseline_local_hospital_black_48;
                    }

                    if(datosBod.getTx_nombre() == null){
                        nombre = "";
                    }else nombre = datosBod.getTx_nombre().toString();

                    if (datosBod.getTx_primer_ap() == null){
                        primer ="";
                    }else primer = datosBod.getTx_primer_ap().toString();
                    if (datosBod.getTx_seg_ap() == null){
                        segundo="";
                    }else segundo = datosBod.getTx_seg_ap().toString();


                    Lista.add(new Datos_IUNG6(datosBod.getId_configuracion(),estado,tipo,nombre+" "+primer+" "+segundo,R.mipmap.sharp_more_horiz_black_480));


                }
                Adaptador_IUGN6 adaptador_iugn6 = new Adaptador_IUGN6(getApplicationContext(),Lista);

                listaDatos.setAdapter(adaptador_iugn6);




            }

            @Override
            public void onFailure(Call<List<DatosContactos_IUGN6>> call, Throwable t) {

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
