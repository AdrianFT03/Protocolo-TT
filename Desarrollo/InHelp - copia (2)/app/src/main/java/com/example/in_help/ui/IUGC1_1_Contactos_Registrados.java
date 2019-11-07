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

public class IUGC1_1_Contactos_Registrados extends AppCompatActivity {

    ListView listaDatos;
    ArrayList<Datos_IUGC1> Lista;
    Integer tipo=0;
    String nombre;
    String primer;
    String segundo;
    String nombreC;
    Integer sexo,parentesco;
    String pri,seg,pol,fec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setupActionBar();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iugc1_1__contactos__registrados);

        listaDatos = (ListView) findViewById(R.id.IUGC1_1listview);

        ObtenerContactos(1);

    }

    public void ObtenerContactos(Integer id_usuaio){

        final APIServer service = Cliente.getAPIServer();
        final DatosContacto_IUGC1_1 datosContacto_iugc1_1 = new DatosContacto_IUGC1_1();
        Call<List<DatosContacto_IUGC1_1>> respuesta = service.ObtenerContactosU(id_usuaio);

        respuesta.enqueue(new Callback<List<DatosContacto_IUGC1_1>>() {
            @Override
            public void onResponse(Call<List<DatosContacto_IUGC1_1>> call, Response<List<DatosContacto_IUGC1_1>> response) {

                Lista = new ArrayList<Datos_IUGC1>();

                List<DatosContacto_IUGC1_1> listaDatosContactos = response.body();

                listaDatosContactos.size();


                for (DatosContacto_IUGC1_1 datosBod : listaDatosContactos){

                    if(datosBod.getId_sexo() == null){
                        sexo = 4;
                    }else sexo = datosBod.getId_sexo();
                    if(datosBod.getId_parentesco() == null){
                        parentesco = 4;
                    }else parentesco = datosBod.getId_parentesco();

                    if(datosBod.getNu_poliza() == null){
                        pol = "";
                    }else pol = datosBod.getNu_poliza();
                    if(datosBod.getFh_vigencia() == null){
                        fec = "";
                    }else fec = datosBod.getFh_vigencia();


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


                    Lista.add(new Datos_IUGC1(datosBod.getId_contacto(),
                            sexo,
                            parentesco,
                            datosBod.getId_tipo(),
                            datosBod.getId_usuario(),
                            datosBod.getTx_nombre(),
                            datosBod.getTx_primer_ap(),
                            datosBod.getTx_seg_ap(),
                            datosBod.getNu_tel(),
                            pol,
                            fec,
                            tipo,
                            R.mipmap.baseline_edit_black_24,
                            R.mipmap.baseline_delete_black_24,
                            nombreC
                            ));


                }
                Adaptador_IUGC1 adaptador_iugc1 = new Adaptador_IUGC1(getApplicationContext(),Lista);

                listaDatos.setAdapter(adaptador_iugc1);
            }

            @Override
            public void onFailure(Call<List<DatosContacto_IUGC1_1>> call, Throwable t) {

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
