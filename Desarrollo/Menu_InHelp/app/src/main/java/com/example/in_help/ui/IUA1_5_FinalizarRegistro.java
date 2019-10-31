package com.example.in_help.ui;

import android.content.Intent;
import android.support.v7.app.ActionBar;
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

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;

public class IUA1_5_FinalizarRegistro extends AppCompatActivity {

    private static final String TAG = "Log";
    private Spinner spinner;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iua1_5__finalizar_registro);

        spinner = (Spinner) findViewById(R.id.TipoSangre);

        String [] tiposangre = {"A+","O+","B+","AB","A-","O-","B-","AB-"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_selectable_list_item, tiposangre);
        spinner.setAdapter(adapter);

        setupActionBar();
        seguridad = (RadioGroup) findViewById(R.id.radioGroup);
        siSeguri = (RadioButton) findViewById(R.id.radioButton);
        noSeguri = (RadioButton)  findViewById(R.id.radioButton2);

        tipoSangre = (RadioGroup) findViewById(R.id.radioGroup2);
        siSangre = (RadioButton) findViewById(R.id.radioButton4);
        noSangre = (RadioButton) findViewById(R.id.radioButton9);

        tipenfermedad = (RadioGroup) findViewById(R.id.radioGroup3);
        siEnferme = (RadioButton) findViewById(R.id.radioButton6);
        noEnferme = (RadioButton) findViewById(R.id.radioButton7);


        NoSeguri = (TextView) findViewById(R.id.textView47);
        grupoS = (TextView) findViewById(R.id.textView29);
        enfermedad = (TextView) findViewById(R.id.textView30);
        NoSeguriEditTex = (EditText) findViewById(R.id.editText15);
        enfermedadEditTex = (EditText) findViewById(R.id.editText12);

        Button Siguiente = (Button) findViewById(R.id.button9);
        TextView Omitir = (TextView) findViewById(R.id.textView31);

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
                spinner.setVisibility(View.VISIBLE);
            }
        });

        noSangre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grupoS.setVisibility(View.GONE);
                spinner.setVisibility(View.GONE);
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

        Siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String sangre = spinner.getSelectedItem().toString();
                info = 1;
                if(siSeguri.isChecked()){
                    if(NoSeguriEditTex.length()==0){
                        NoSeguriEditTex.setError("Campo obligatorio");
                        info = 0;
                    }else if(NoSeguriEditTex.length() > 0 && NoSeguriEditTex.length() < 11){
                        NoSeguriEditTex.setError("El No. de seguridad debe tener 11 caracteres");
                        info = 0;
                    }
                }else if(noSeguri.isChecked()){
                }else info = 2;

                if(siSangre.isChecked()){
                    if(sangre.equals("A+")){
                        intSangre=1;
                    }else if(sangre.equals("O+")){
                        intSangre=2;
                    }
                    else if(sangre.equals("B+")){
                        intSangre=3;
                    }
                    else if(sangre.equals("AB+")){
                        intSangre=4;
                    }
                    else if(sangre.equals("A-")){
                        intSangre=5;
                    }
                    else if(sangre.equals("O-")){
                        intSangre=6;
                    }
                    else if(sangre.equals("B-")){
                        intSangre=7;
                    }
                    else if(sangre.equals("AB-")){
                        intSangre=8;
                    }

                }else if(noSangre.isChecked()){
                    intSangre=9;
                }else info = 2;

                if(siEnferme.isChecked()){
                    if(enfermedadEditTex.length()==0){
                        enfermedadEditTex.setError("Campo obligatorio");
                        info = 0;
                    }else if(enfermedadEditTex.length()>0 && enfermedadEditTex.length()<3){
                        enfermedadEditTex.setError("La enfermedad debe tener al menos 3 caracteres");
                        info = 0;
                    }
                }else if(noEnferme.isChecked()){

                }else info = 2;

                if(info == 1){

                    if(siSeguri.isChecked()){
                        guardarInfoMédica(null,1,5,NoSeguriEditTex.getText().toString());
                    }else if(noSeguri.isChecked()){

                    }
                    if(siSangre.isChecked()){

                    }else if(noSangre.isChecked()){

                    }
                    if (siEnferme.isChecked()){
                        guardarEnfermedad(null,1,3,enfermedadEditTex.getText().toString());
                    }else if(noEnferme.isChecked()){

                    }
                    GoFinalizarRegistroA();

                }else if(info == 2){
                    Toast.makeText(IUA1_5_FinalizarRegistro.this, "Error : Debe seleccionar una opción", Toast.LENGTH_SHORT).show();
                }

            }
        });



        Omitir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                GoFinalizarRegistroA();

            }
        });

    }


    public void guardarInfoMédica(Integer id_seguridad, Integer id_persona, Integer id_tipo_seguridad,String tx_seguridad){
        APIServer service = Cliente.getAPIServer();

        SeguridadRequest seguridadRequest = new SeguridadRequest(id_seguridad,id_persona,id_tipo_seguridad,tx_seguridad);

        Call<Response> call = (Call<Response>) service.crearSeguridad(seguridadRequest);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                response.body().getSuccesfull();
                Log.d(TAG, "onResponse: "+response.body().getSuccesfull());
                //Toast.makeText(IUA1_5_FinalizarRegistro.this, "Información médica registrada", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t);
                Toast.makeText(IUA1_5_FinalizarRegistro.this, "Error info medica", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void guardarEnfermedad(Integer id_enfermedad, Integer id_persona, Integer id_tipo, String text){
        APIServer service = Cliente.getAPIServer();

        EnfermedadRequest enfermedadRequest = new EnfermedadRequest(id_enfermedad,id_persona,id_tipo,text);

        Call<Response> call = (Call<Response>) service.crearEnfermedad(enfermedadRequest);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                response.body().getSuccesfull();
                Log.d(TAG, "onResponse: "+response.body().getSuccesfull());
                //Toast.makeText(IUA1_5_FinalizarRegistro.this, "Enfermedad registrada", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t);
                Toast.makeText(IUA1_5_FinalizarRegistro.this, "Error", Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void GoFinalizarRegistroA(){
        Intent GoFinalizarRegistroA = new Intent(this, IUA1_5A_Finalizar_Registro.class);
        startActivity(GoFinalizarRegistroA);
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
