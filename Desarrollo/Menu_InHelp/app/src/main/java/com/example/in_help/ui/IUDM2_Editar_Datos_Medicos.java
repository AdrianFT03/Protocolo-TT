package com.example.in_help.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.in_help.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IUDM2_Editar_Datos_Medicos extends AppCompatActivity {

    private static final String TAG = "Log";
    private Spinner spinner;
    public String content;

    public Integer intSangre;
    public Integer info=1;

    public RadioGroup seguridad;
    public RadioButton siSeguri;
    public RadioButton noSeguri;

    public RadioGroup tipoSangre;
    public RadioButton siSangre;
    public RadioButton noSangre;

    public RadioGroup tipenfermedad;
    public RadioButton siEnferme;
    public RadioButton noEnferme;

    public TextView NoSeguri;
    public TextView grupoS;
    public TextView enfermedad;
    public EditText NoSeguriEditTex;
    public EditText enfermedadEditTex;

    public Spinner spinnerprueba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iudm2_editar_datos_medicos);


        spinnerprueba = (Spinner) findViewById(R.id.TipoSangre1);

        String [] tiposangre1 = {"A+","O+","B+","AB","A-","O-","B-","AB-"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_selectable_list_item, tiposangre1);
        spinnerprueba.setAdapter(adapter);

        //setupActionBar();
        seguridad = (RadioGroup) findViewById(R.id.radioGroup100);
        siSeguri = (RadioButton) findViewById(R.id.radioButton101);
        noSeguri = (RadioButton)  findViewById(R.id.radioButton102);

        tipoSangre = (RadioGroup) findViewById(R.id.radioGroup103);
        siSangre = (RadioButton) findViewById(R.id.radioButton104);
        noSangre = (RadioButton) findViewById(R.id.radioButton105);

        tipenfermedad = (RadioGroup) findViewById(R.id.radioGroup106);
        siEnferme = (RadioButton) findViewById(R.id.radioButton107);
        noEnferme = (RadioButton) findViewById(R.id.radioButton108);


        NoSeguri = (TextView) findViewById(R.id.textView471);
        grupoS = (TextView) findViewById(R.id.textView291);
        enfermedad = (TextView) findViewById(R.id.textView301);
        NoSeguriEditTex = (EditText) findViewById(R.id.editText151);
        enfermedadEditTex = (EditText) findViewById(R.id.editText121);

        Button Listo = (Button) findViewById(R.id.button91);


        siSeguri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NoSeguri.setVisibility(View.VISIBLE);
                NoSeguriEditTex.setVisibility(View.VISIBLE);
            }
        });

        noSeguri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NoSeguri.setVisibility(View.GONE);
                NoSeguriEditTex.setVisibility(View.GONE);
            }
        });

        siSangre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grupoS.setVisibility(View.VISIBLE);
                spinnerprueba.setVisibility(View.VISIBLE);
            }
        });

        noSangre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grupoS.setVisibility(View.GONE);
                spinnerprueba.setVisibility(View.GONE);
            }
        });

        siEnferme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enfermedad.setVisibility(View.VISIBLE);
                enfermedadEditTex.setVisibility(View.VISIBLE);
            }
        });

        noEnferme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enfermedad.setVisibility(View.GONE);
                enfermedadEditTex.setVisibility(View.GONE);
            }
        });

        Listo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String sangre = spinnerprueba.getSelectedItem().toString();
                info = 1;
                if(siSeguri.isChecked()){
                    NoSeguriEditTex.setHint(content);
                    ObtenerNumeroSeguiradSocial(1);
                    }else if(NoSeguriEditTex.length() > 0 && NoSeguriEditTex.length() < 11){
                        NoSeguriEditTex.setError("El No. de seguridad debe tener 11 caracteres");
                        info = 0;
                }else if(noSeguri.isChecked()){
                }else info = 2;

                if(siSangre.isChecked()){
                    if(sangre.equals("A+")){
                        intSangre=1;
                        EditaTipoSangre(intSangre,1);
                    }else if(sangre.equals("O+")){
                        intSangre=2;
                        EditaTipoSangre(intSangre,1);
                    }
                    else if(sangre.equals("B+")){
                        intSangre=3;
                        EditaTipoSangre(intSangre,1);
                    }
                    else if(sangre.equals("AB+")){
                        intSangre=4;
                        EditaTipoSangre(intSangre,1);
                    }
                    else if(sangre.equals("A-")){
                        intSangre=5;
                        EditaTipoSangre(intSangre,1);
                    }
                    else if(sangre.equals("O-")){
                        intSangre=6;
                        EditaTipoSangre(intSangre,1);
                    }
                    else if(sangre.equals("B-")){
                        intSangre=7;
                        EditaTipoSangre(intSangre,1);
                    }
                    else if(sangre.equals("AB-")){
                        intSangre=8;
                        EditaTipoSangre(intSangre,1);
                    }

                }else if(noSangre.isChecked()){
                    intSangre=9;
                    EditaTipoSangre(intSangre,1);
                }else info = 2;

                if(siEnferme.isChecked()){
                    if(enfermedadEditTex.length()==0){
                        ObtenerEnfermedadCronica(1);
                    }else if(enfermedadEditTex.length()>0 && enfermedadEditTex.length()<3){
                        enfermedadEditTex.setError("La enfermedad debe tener al menos 3 caracteres");
                        info = 0;
                    }
                }else if(noEnferme.isChecked()){

                }else info = 2;

                if(info == 1){

                    if(siSeguri.isChecked()){
                        EditaNSS(NoSeguriEditTex.getText().toString(),1);
                    }else if(noSeguri.isChecked()){
                        EditaNSS("",1);

                    }
                    if(siSangre.isChecked()){


                    }else if(noSangre.isChecked()){

                    }
                    if (siEnferme.isChecked()){
                        EditaEnferCro(enfermedadEditTex.getText().toString(),1);

                    }else if(noEnferme.isChecked()){
                        EditaEnferCro("",1);

                    }
                    Toast.makeText(IUDM2_Editar_Datos_Medicos.this, "Datos Actualizados", Toast.LENGTH_SHORT).show();
                    GoDatosMedicos();

                }else if(info == 2){
                    Toast.makeText(IUDM2_Editar_Datos_Medicos.this, "Ocurrio un error", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    /*Obtener Datos*/

    public void ObtenerEnfermedadCronica(Integer id_usuario) {
        APIServer service = Cliente.getAPIServer();

        final EnfermedadCronicaRequest enfermedadCronicaRequest = new EnfermedadCronicaRequest();
        Call<List<EnfermedadCronicaRequest>> respuesta = service.ObtenerEnfermedadCronica(id_usuario);
        respuesta.enqueue(new Callback<List<EnfermedadCronicaRequest>>() {
            @Override
            public void onResponse(Call<List<EnfermedadCronicaRequest>> call, Response<List<EnfermedadCronicaRequest>> response) {
                //Toast.makeText(IUDM2_Editar_Datos_Medicos.this, "Bien", Toast.LENGTH_SHORT).show();
                List<EnfermedadCronicaRequest> listPost = response.body();
                content = "";
                for (EnfermedadCronicaRequest enfermedadCronicaRequest : listPost) {
                    content += enfermedadCronicaRequest.getTx_nombre();

                }
                enfermedadEditTex.setText(content);

                Log.d(TAG, "Respuesta: " + content);
            }

            @Override
            public void onFailure(Call<List<EnfermedadCronicaRequest>> call, Throwable t) {
                Toast.makeText(IUDM2_Editar_Datos_Medicos.this, "MAl", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void ObtenerNumeroSeguiradSocial(Integer id_usuario) {
        APIServer service = Cliente.getAPIServer();

        final NSSRequest nssRequest = new NSSRequest();
        Call<List<NSSRequest>> respuesta = service.ObtenerNSS(id_usuario);
        respuesta.enqueue(new Callback<List<NSSRequest>>() {
            @Override
            public void onResponse(Call<List<NSSRequest>> call, Response<List<NSSRequest>> response) {
                //Toast.makeText(IUDM2_Editar_Datos_Medicos.this, "Bien", Toast.LENGTH_SHORT).show();
                List<NSSRequest> listPost = response.body();
                content = "";
                for (NSSRequest nssRequest : listPost) {
                    content += nssRequest.getTx_id();

                }
                NoSeguriEditTex.setHint(content);

                Log.d(TAG, "Respuesta: " + content);
            }

            @Override
            public void onFailure(Call<List<NSSRequest>> call, Throwable t) {
                Toast.makeText(IUDM2_Editar_Datos_Medicos.this, "Mal", Toast.LENGTH_SHORT).show();
            }
        });

    }

   /* Actualizar Datos*/

    public void EditaNSS(String tx_id , Integer id_persona){
        APIServer service = Cliente.getAPIServer();
        EditarDatosMedicosRequest EditarNSS = new EditarDatosMedicosRequest();
        Call<com.example.in_help.ui.Response> call = (Call<com.example.in_help.ui.Response>) service.UpdateNSS(tx_id,id_persona);
        call.enqueue(new Callback<com.example.in_help.ui.Response>() {
            @Override
            public void onResponse(Call<com.example.in_help.ui.Response> call, Response<com.example.in_help.ui.Response> response) {
                //Toast.makeText(IUDM2_Editar_Datos_Medicos.this, "Bien", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<com.example.in_help.ui.Response> call, Throwable t) {
                Toast.makeText(IUDM2_Editar_Datos_Medicos.this, "Mal", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void EditaTipoSangre(Integer id_tipo_sangre, Integer id_persona){
        APIServer service = Cliente.getAPIServer();
        EditarTipoDeSangreRequest EditarTipoSangre = new EditarTipoDeSangreRequest();
        Call<com.example.in_help.ui.Response> call = (Call<com.example.in_help.ui.Response>) service.UpdateTipoSangre(id_tipo_sangre,id_persona);
        call.enqueue(new Callback<com.example.in_help.ui.Response>() {
            @Override
            public void onResponse(Call<com.example.in_help.ui.Response> call, Response<com.example.in_help.ui.Response> response) {
                //Toast.makeText(IUDM2_Editar_Datos_Medicos.this, "Bien", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<com.example.in_help.ui.Response> call, Throwable t) {
                Toast.makeText(IUDM2_Editar_Datos_Medicos.this, "Mal", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void EditaEnferCro(String tx_nombre, Integer id_persona){
        APIServer service = Cliente.getAPIServer();
        EnfermedadCronicaRequest EditarEnferCro = new EnfermedadCronicaRequest();
        Call<com.example.in_help.ui.Response> call = (Call<com.example.in_help.ui.Response>) service.UpdateEnfermedadCronica(tx_nombre,id_persona);
        call.enqueue(new Callback<com.example.in_help.ui.Response>() {
            @Override
            public void onResponse(Call<com.example.in_help.ui.Response> call, Response<com.example.in_help.ui.Response> response) {
                //Toast.makeText(IUDM2_Editar_Datos_Medicos.this, "Bien", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<com.example.in_help.ui.Response> call, Throwable t) {
                Toast.makeText(IUDM2_Editar_Datos_Medicos.this, "Mal", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void GoDatosMedicos(){
        Intent GoDatosMedicos = new Intent(this, IUDM1_Datos_Medicos.class);
        startActivity(GoDatosMedicos);
    }



}
