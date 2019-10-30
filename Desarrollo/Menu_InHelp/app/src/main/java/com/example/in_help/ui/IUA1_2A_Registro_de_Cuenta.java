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
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.in_help.R;

import retrofit2.Call;
import retrofit2.Callback;

public class IUA1_2A_Registro_de_Cuenta extends AppCompatActivity {

    private static final String TAG ="Log" ;
    private Spinner spinner1;
    public EditText nombre;
    public EditText primerAp;
    public EditText segundoAp;
    public EditText dia;
    public EditText mes;
    public EditText anio;
    public String fecha;
    public String sexo;
    public Integer intSexo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActionBar();


        setContentView(R.layout.activity_iua1_2_a__registro_de__cuenta);

        spinner1 = (Spinner) findViewById(R.id.spinnerSexo);
        final Button Listo = (Button) findViewById(R.id.button6);
        nombre = (EditText) findViewById(R.id.editText14);
        primerAp = (EditText) findViewById(R.id.editText17);
        segundoAp = (EditText) findViewById(R.id.editText18);
        dia = (EditText) findViewById(R.id.editText20);
        mes = (EditText) findViewById(R.id.editText21);
        anio = (EditText) findViewById(R.id.editText22);



        final String [] sexos = {"Hombre","Mujer","Otro"};
        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_selectable_list_item, sexos);
        spinner1.setAdapter(adapter);

        Listo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder fecha1 = new StringBuilder();
                int info = 1;

                sexo = spinner1.getSelectedItem().toString();

                fecha1.append(anio.getText().toString());
                fecha1.append("-");
                fecha1.append(mes.getText().toString());
                fecha1.append("-");
                fecha1.append(dia.getText().toString());
                fecha = fecha1.toString();


                if(nombre.length() == 0){
                    nombre.setError("Campo obligatorio");
                    info = 0;
                }else if(nombre.length() > 0 && nombre.length()<3){
                    nombre.setError("El nombre debe tener al menos 3 caracteres");
                    info = 0;
                }

                if(primerAp.length() == 0){
                    primerAp.setError("Campo obligatorio");
                    info = 0;
                }else if(primerAp.length() > 0 && primerAp.length()<3){
                    primerAp.setError("El apellido debe tener al menos 3 caracteres");
                    info = 0;
                }

                if(segundoAp.length() > 0 && segundoAp.length()<3){
                    segundoAp.setError("El apellido debe tener al menos 3 caracteres");
                    info = 0;
                }

                if(dia.length() == 0){
                    dia.setError("Campo obligatorio");
                    info = 0;
                }else if(dia.length() > 0 && dia.length()<2){
                    dia.setError("El día debe tener 2 caracteres");
                    info = 0;
                }else if(dia.getText().toString().equals("00")){
                    dia.setError("Error en formato");
                    info = 0;
                }else if(dia.length() > 0){
                    Integer intDia = Integer.valueOf(dia.getText().toString());
                    if(intDia > 31){
                        dia.setError("Día inválido");
                        info = 0;
                    }
                }

                if(mes.length() == 0){
                    mes.setError("Campo obligatorio");
                    info = 0;
                }else if(mes.length() > 0 && mes.length()<2){
                    mes.setError("El mes debe tener 2 caracteres");
                    info = 0;
                }else if(mes.getText().toString().equals("00")){
                    mes.setError("Error en formato");
                    info = 0;
                }else if(mes.length() > 0){
                    Integer intMes = Integer.valueOf(mes.getText().toString());
                    if(intMes > 12){
                        mes.setError("Mes inválido");
                        info = 0;
                    }
                }

                if(anio.length() == 0){
                    anio.setError("Campo obligatorio");
                    info = 0;
                }else if(anio.length() > 0 && anio.length()<2){
                    anio.setError("El año debe tener 4 caracteres");
                    info = 0;
                }else if(anio.getText().toString().equals("0000")){
                    anio.setError("Error en formato");
                    info = 0;
                }else if(anio.length() > 0){
                    Integer intAnio = Integer.valueOf(anio.getText().toString());
                    if(intAnio > 2019){
                        anio.setError("Año inválido");
                        info = 0;
                    }
                }

                if(sexo.equals("Hombre")){
                     intSexo = 1;
                }else if(sexo.equals("Mujer")){
                     intSexo = 2;
                }else if(sexo.equals("Otro")){
                     intSexo = 3;
                }

                if(info == 1){
                    registrarUsuario();
                    GoFinalizarRegistro();
                }

            }
        });
    }


    public void registrarUsuario(){

        APIServer service = Cliente.getAPIServer();

        PersonRequest person = new PersonRequest(null,intSexo,9,nombre.getText().toString(),primerAp.getText().toString(),segundoAp.getText().toString(),fecha);


        Call<Response> call = (Call<Response>) service.crearPersona(person);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                //Toast.makeText(IUA1_2A_Registro_de_Cuenta.this, "Bien", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Toast.makeText(IUA1_2A_Registro_de_Cuenta.this, "Mal"+t, Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void GoFinalizarRegistro(){
        Intent GoFinalizarRegistro = new Intent(this, IUA1_5_FinalizarRegistro.class);
        startActivity(GoFinalizarRegistro);
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
