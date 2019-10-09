package com.example.in_help.ui;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.in_help.R;

import retrofit2.Call;
import retrofit2.Callback;

public class IUGV1_2_Registrar_Vehiculo extends AppCompatActivity {
    private static final String TAG = "Log";
    public Integer info=1;


    public EditText editTextPlacas;
    public EditText editTextNoPoliza;
    public EditText editTextFhPoliza;

    public RadioGroup Detalles;
    public RadioButton SiDetalles;
    public RadioButton NoDetalles;

    public RadioGroup Aseguradora;
    public RadioButton SiAseg;
    public RadioButton NoAseg;

    public TextView txt_Placas;
    public TextView txt_Detalles;
    public TextView txt_Aseg;
    public TextView txt_NoPoliza;
    public TextView txt_Fhpoliza;

    public Button Btn_Listo;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iugv1_2__registrar__vehiculo);

        setupActionBar();

        editTextPlacas = (EditText) findViewById(R.id.editText13);
        editTextNoPoliza = (EditText) findViewById(R.id.editText8);
        editTextFhPoliza = (EditText) findViewById(R.id.editText9);

        Detalles = (RadioGroup) findViewById(R.id.radioGroup4);
        SiDetalles = (RadioButton) findViewById(R.id.radioButton8);
        NoDetalles = (RadioButton) findViewById(R.id.radioButton3);

        Aseguradora = (RadioGroup) findViewById(R.id.radioGroup5);
        SiAseg = (RadioButton) findViewById(R.id.radioButton10);
        NoAseg = (RadioButton) findViewById(R.id.radioButton11);

        txt_Placas = (TextView) findViewById(R.id.textView53);
        txt_Detalles = (TextView) findViewById(R.id.textView35);
        txt_Aseg = (TextView) findViewById(R.id.textView36);
        txt_NoPoliza = (TextView) findViewById(R.id.textView37);
        txt_Fhpoliza = (TextView) findViewById(R.id.textView38);

        Btn_Listo = (Button) findViewById(R.id.button10);

        SiDetalles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_Aseg.setVisibility(View.VISIBLE);
                Aseguradora.setVisibility(View.VISIBLE);
            }
        });

        NoDetalles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               txt_Aseg.setVisibility(View.GONE);
                Aseguradora.setVisibility(View.GONE);
                editTextFhPoliza.setVisibility(View.GONE);
                editTextNoPoliza.setVisibility(View.GONE);
                txt_NoPoliza.setVisibility(View.GONE);
                txt_Fhpoliza.setVisibility(View.GONE);
            }
        });

        SiAseg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_NoPoliza.setVisibility(View.VISIBLE);
               editTextNoPoliza.setVisibility(View.VISIBLE);
                txt_Fhpoliza.setVisibility(View.VISIBLE);
                editTextFhPoliza.setVisibility(View.VISIBLE);
            }
        });

        NoAseg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_NoPoliza.setVisibility(View.GONE);
                editTextNoPoliza.setVisibility(View.GONE);
                txt_Fhpoliza.setVisibility(View.GONE);
                editTextFhPoliza.setVisibility(View.GONE);
            }
        });

        Btn_Listo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int info=1;
                if (editTextPlacas.length() == 0){
                    editTextPlacas.setError("Campo Obligatorio");
                }else  if (){

            }
        });



    }
/* Metodos de insercion*/

    public void InsertaPlacas(){
        APIServer service = Cliente.getAPIServer();
        CrearVehiculoRequest insertaPlacas = new CrearVehiculoRequest(null,editTextPlacas.getText().toString(),null,null);
        Call<Response> call=(Call<Response>) service.crearVehiculo(insertaPlacas);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                response.body().getSuccesfull();
                Log.d(TAG, "onResponse: "+response.body().getSuccesfull());
                Toast.makeText(IUGV1_2_Registrar_Vehiculo.this, "Placas Registradas", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Toast.makeText(IUGV1_2_Registrar_Vehiculo.this, "Algo Salio Mal", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void GoRegistroVehiculos(View view){
        Intent GoRegistroVehiculos = new Intent(this, IUPP1A_Pantalla_Principal.class);
        startActivity(GoRegistroVehiculos);
        Toast t = Toast.makeText(this,"Vehículo registrado exitosamente", Toast.LENGTH_SHORT);
        t.show();
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
