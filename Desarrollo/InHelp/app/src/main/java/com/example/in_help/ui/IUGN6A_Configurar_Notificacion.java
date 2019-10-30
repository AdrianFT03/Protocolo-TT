package com.example.in_help.ui;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.in_help.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IUGN6A_Configurar_Notificacion extends AppCompatActivity {

    private static final String TAG ="Log" ;
    ImageButton infoCoche;
    ImageButton infoPersonal;
    ImageButton infoMEdica;
    ImageButton infoDispositivo;
    ImageButton constVerde;
    ImageButton constRojo;
    ArrayList<DatosPermiso_IGN6A> Lista;
    Integer coche;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setupActionBar();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iugn6_a__configurar__notificacion);

        constVerde.setBackgroundColor(0xFF85C76F);
        constRojo.setBackgroundColor(0xFFAA2D46);

        Datos_IUNG6 objeto = (Datos_IUNG6) getIntent().getExtras().getSerializable("DatosIUGN6");

        Toast.makeText(this, "id_configuracion: "+ objeto.getId(), Toast.LENGTH_SHORT).show();

        infoCoche = (ImageButton) findViewById(R.id.IUGN6AimageButton);
        infoPersonal = (ImageButton) findViewById(R.id.IUGN6AimageButton1);
        infoMEdica = (ImageButton) findViewById(R.id.IUGN6AimageButton2);
        infoDispositivo = (ImageButton) findViewById(R.id.IUGN6AimageButton3);

        ObtenerPermisos(objeto.getId());

        

        infoCoche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(infoCoche.getBackground() == constVerde.getBackground()){// es verde?
                    infoCoche.setBackgroundColor(0xFFAA2D46);
                }else if (infoCoche.getBackground() == constRojo.getBackground()){
                    infoCoche.setBackgroundColor(0xFF85C76F);
                }
            }
        });





    }

    public void ObtenerPermisos(Integer id_confi){

        APIServer service = Cliente.getAPIServer();

        final DatosPermiso_IGN6A datosPermiso_ign6A = new DatosPermiso_IGN6A();
        Call<List<DatosPermiso_IGN6A>> respuesta = service.ObtenerPermisos(id_confi);

        respuesta.enqueue(new Callback<List<DatosPermiso_IGN6A>>() {
            @Override
            public void onResponse(Call<List<DatosPermiso_IGN6A>> call, Response<List<DatosPermiso_IGN6A>> response) {

                              Lista = new ArrayList<DatosPermiso_IGN6A>();

                List<DatosPermiso_IGN6A> listaDatosVehiculoIUGN6A = response.body();
                listaDatosVehiculoIUGN6A.size();

                for (DatosPermiso_IGN6A datosBod : listaDatosVehiculoIUGN6A){

                    Lista.add(new DatosPermiso_IGN6A(datosBod.getId_estado(),datosBod.getId_permiso()));
                }

               Log.d(TAG, "Lista Permisos: "+Lista);

                if(Lista.get(0).getId_estado() == 1){
                    // Estado Verde Coche
                    infoCoche.setBackgroundColor(0xFF85C76F);
                    coche = 1;

                }else if(Lista.get(0).getId_estado() == 2){
                    // Estado Rojo Coche
                    infoCoche.setBackgroundColor(0xFFAA2D46);
                    coche = 2;
                }

                if(Lista.get(1).getId_estado() ==1){
                    //Estado Verde Persona
                    infoPersonal.setBackgroundColor(0xFF85C76F);
                }else if(Lista.get(1).getId_estado() ==2){
                    //Estado Rojo Personal
                    infoPersonal.setBackgroundColor(0xFFAA2D46);
                }

                if(Lista.get(2).getId_estado() == 1){
                    //Estado Verde Médica
                    infoMEdica.setBackgroundColor(0xFF85C76F);
                }else if(Lista.get(2).getId_estado() == 2){
                    //Estado Rojo Médica
                    infoMEdica.setBackgroundColor(0xFFAA2D46);
                }

                if(Lista.get(3).getId_estado() == 1){
                    //Estado Verde Dispositivo
                    infoDispositivo.setBackgroundColor(0xFF85C76F);
                }else if(Lista.get(3).getId_estado() == 2){
                    //Estado Rojo Dispositivo
                    infoDispositivo.setBackgroundColor(0xFFAA2D46);
                }

                infoCoche.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(coche == 1){
                            infoCoche.setBackgroundColor(0xFFAA2D46);
                        }else if(coche ==2){
                            infoCoche.setBackgroundColor(0xFF85C76F);
                        }
                    }
                });



            }

            @Override
            public void onFailure(Call<List<DatosPermiso_IGN6A>> call, Throwable t) {

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
