package com.example.in_help.ui;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Switch;
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
    Switch switchinfocoche,switchinfoersonal,switchinfomedica,switchinfodispo;
    ArrayList<DatosPermiso_IGN6A> Lista;
    Integer coche;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setupActionBar();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iugn6_a__configurar__notificacion);

        final Datos_IUNG6 objeto = (Datos_IUNG6) getIntent().getExtras().getSerializable("DatosIUGN6");

        //Toast.makeText(this, "id_configuracion: "+ objeto.getId(), Toast.LENGTH_SHORT).show();

        infoCoche = (ImageButton) findViewById(R.id.IUGN6AimageButton);
        infoPersonal = (ImageButton) findViewById(R.id.IUGN6AimageButton1);
        infoMEdica = (ImageButton) findViewById(R.id.IUGN6AimageButton2);
        infoDispositivo = (ImageButton) findViewById(R.id.IUGN6AimageButton3);

        switchinfocoche = (Switch) findViewById(R.id.IUGN6Aswitch1);
        switchinfoersonal = (Switch) findViewById(R.id.IUGN6Aswitch2);
        switchinfomedica = (Switch) findViewById(R.id.IUGN6Aswitch3);
        switchinfodispo = (Switch) findViewById(R.id.IUGN6Aswitch4);


        ObtenerPermisos(objeto.getId());

        switchinfocoche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(switchinfocoche.isChecked() == true){// esta en verde
                    infoCoche.setBackgroundColor(0xFF85C76F);
                    ActualizaPermiso(1,1,objeto.getId());
                }else if(switchinfocoche.isChecked() == false){//esta en rojo
                    infoCoche.setBackgroundColor(0xFFAA2D46);
                    ActualizaPermiso(2,1,objeto.getId());
                }
            }
        });
        switchinfoersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(switchinfoersonal.isChecked() == true){// esta en verde
                    infoPersonal.setBackgroundColor(0xFF85C76F);
                    ActualizaPermiso(1,2,objeto.getId());
                }else if(switchinfoersonal.isChecked() == false){//esta en rojo
                    infoPersonal.setBackgroundColor(0xFFAA2D46);
                    ActualizaPermiso(2,2,objeto.getId());
                }
            }
        });
        switchinfomedica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(switchinfomedica.isChecked() == true){// esta en verde
                    infoMEdica.setBackgroundColor(0xFF85C76F);
                    ActualizaPermiso(1,3,objeto.getId());
                }else if(switchinfomedica.isChecked() == false){//esta en rojo
                    infoMEdica.setBackgroundColor(0xFFAA2D46);
                    ActualizaPermiso(2,3,objeto.getId());
                }
            }
        });

        switchinfodispo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(switchinfodispo.isChecked() == true){// esta en verde
                    infoDispositivo.setBackgroundColor(0xFF85C76F);
                    ActualizaPermiso(1,4,objeto.getId());

                }else if(switchinfodispo.isChecked() == false){//esta en rojo
                    infoDispositivo.setBackgroundColor(0xFFAA2D46);
                    ActualizaPermiso(2,4,objeto.getId());
                }
            }
        });


    }

    public void ActualizaPermiso(Integer id_estado, Integer id_permiso, Integer id_configuracion){
        APIServer service= Cliente.getAPIServer();

        DatosActualiza_IUGN6A datosActualiza_iugn6 = new DatosActualiza_IUGN6A();


        Call<com.example.in_help.ui.Response> call = (Call<com.example.in_help.ui.Response>) service.UpdatePermiso(id_estado,id_permiso,id_configuracion);

        call.enqueue(new Callback<com.example.in_help.ui.Response>() {
            @Override
            public void onResponse(Call<com.example.in_help.ui.Response> call, Response<com.example.in_help.ui.Response> response) {

            }

            @Override
            public void onFailure(Call<com.example.in_help.ui.Response> call, Throwable t) {

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
                    switchinfocoche.setChecked(true);

                }else if(Lista.get(0).getId_estado() == 2){
                    // Estado Rojo Coche
                    infoCoche.setBackgroundColor(0xFFAA2D46);
                    switchinfocoche.setChecked(false);
                }

                if(Lista.get(1).getId_estado() == 1){
                    //Estado Verde Persona
                    infoPersonal.setBackgroundColor(0xFF85C76F);
                    switchinfoersonal.setChecked(true);
                }else if(Lista.get(1).getId_estado() ==2){
                    //Estado Rojo Personal
                    infoPersonal.setBackgroundColor(0xFFAA2D46);
                    switchinfoersonal.setChecked(false);
                }

                if(Lista.get(2).getId_estado() == 1){
                    //Estado Verde Médica
                    infoMEdica.setBackgroundColor(0xFF85C76F);
                    switchinfomedica.setChecked(true);
                }else if(Lista.get(2).getId_estado() == 2){
                    //Estado Rojo Médica
                    infoMEdica.setBackgroundColor(0xFFAA2D46);
                    switchinfomedica.setChecked(false);
                }

                if(Lista.get(3).getId_estado() == 1){
                    //Estado Verde Dispositivo
                    infoDispositivo.setBackgroundColor(0xFF85C76F);
                    switchinfodispo.setChecked(true);
                }else if(Lista.get(3).getId_estado() == 2){
                    //Estado Rojo Dispositivo
                    infoDispositivo.setBackgroundColor(0xFFAA2D46);
                    switchinfodispo.setChecked(false);
                }





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
