package com.example.in_help.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.in_help.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class IUGV1_3_Editar_Vehiculo extends AppCompatActivity {


    private static final String TAG = "Log";
    public Integer info=1;
    public String content;
    public String fecha;


    public EditText editTextPlacas;
    public EditText editTextNoPoliza;
    public EditText editTextFhPoliza;
    public EditText editDia;
    public EditText editMes;
    public EditText editAnio;

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
    public TextView txt_Dia;
    public TextView txt_Mes;
    public TextView txt_Anio;
    public TextView txt_Fhpoliza;

    public Button Btn_Listo;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iugv1_2__editar__vehiculo_ald);

        // setupActionBar();

       // final Datos_IUGV1 objeto = (Datos_IUGV1) getIntent().getExtras().getSerializable("DatosAnt");
        //String id_carro = Integer.toString(objeto.getId());
        //Toast.makeText(this, "Id_vehiculo:"+objeto.getId(), Toast.LENGTH_SHORT).show();

        editTextPlacas = (EditText) findViewById(R.id.editText13);
        editTextNoPoliza = (EditText) findViewById(R.id.editText8);
        editDia = (EditText) findViewById(R.id.editText9);
        editMes = (EditText) findViewById(R.id.editTextMes);
        editAnio = (EditText) findViewById(R.id.editTextAnio);

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
        txt_Dia = (TextView) findViewById(R.id.TextDia);
        txt_Mes = (TextView) findViewById(R.id.TextMes);
        txt_Anio = (TextView) findViewById(R.id.TextAnio);
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
                editDia.setVisibility(View.GONE);
                editMes.setVisibility(View.GONE);
                editAnio.setVisibility(View.GONE);
                txt_Dia.setVisibility(View.GONE);
                txt_Mes.setVisibility(View.GONE);
                txt_Anio.setVisibility(View.GONE);
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
                editDia.setVisibility(View.VISIBLE);
                editMes.setVisibility(View.VISIBLE);
                editAnio.setVisibility(View.VISIBLE);
                txt_Dia.setVisibility(View.VISIBLE);
                txt_Mes.setVisibility(View.VISIBLE);
                txt_Anio.setVisibility(View.VISIBLE);



            }
        });

        NoAseg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_NoPoliza.setVisibility(View.GONE);
                editTextNoPoliza.setVisibility(View.GONE);
                txt_Fhpoliza.setVisibility(View.GONE);
                editDia.setVisibility(View.GONE);
                editMes.setVisibility(View.GONE);
                editAnio.setVisibility(View.GONE);
                txt_Dia.setVisibility(View.GONE);
                txt_Mes.setVisibility(View.GONE);
                txt_Anio.setVisibility(View.GONE);
            }
        });


        Btn_Listo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder fecha2 = new StringBuilder();
                int info=1;
                fecha2.append(editAnio.getText());
                fecha2.append("-");
                fecha2.append(editMes.getText());
                fecha2.append("-");
                fecha2.append(editDia.getText());
                fecha = fecha2.toString();


                if(editTextNoPoliza.length() == 0){
                    editTextNoPoliza.setError("Campo obligatorio");
                    info = 0;
                }else if(editTextNoPoliza.length() > 0 && editTextNoPoliza.length()<17){
                    editTextNoPoliza.setError("El numero de poliza debe tener al menos 16 caracteres");
                    info = 0;
                }

                if(editDia.length() == 0){
                    editDia.setError("Campo obligatorio");
                    info = 0;
                }else if(editDia.length() > 0 && editDia.length()<2){
                    editDia.setError("El día debe tener 2 caracteres");
                    info = 0;
                }else if(editDia.getText().toString().equals("00")){
                    editDia.setError("Error en formato");
                    info = 0;
                }else if(editDia.length() > 0){
                    Integer intDia = Integer.valueOf(editDia.getText().toString());
                    if(intDia > 31){
                        editDia.setError("Día inválido");
                        info = 0;
                    }
                }

                if(editMes.length() == 0){
                    editMes.setError("Campo obligatorio");
                    info = 0;
                }else if(editMes.length() > 0 && editMes.length()<2){
                    editMes.setError("El mes debe tener 2 caracteres");
                    info = 0;
                }else if(editMes.getText().toString().equals("00")){
                    editMes.setError("Error en formato");
                    info = 0;
                }else if(editMes.length() > 0){
                    Integer intMes = Integer.valueOf(editMes.getText().toString());
                    if(intMes > 12){
                        editMes.setError("Mes inválido");
                        info = 0;
                    }
                }

                if(editAnio.length() == 0){
                    editAnio.setError("Campo obligatorio");
                    info = 0;
                }else if(editAnio.length() > 0 && editAnio.length()<2){
                    editAnio.setError("El año debe tener 4 caracteres");
                    info = 0;
                }else if(editAnio.getText().toString().equals("0000")){
                    editAnio.setError("Error en formato");
                    info = 0;
                }else if(editAnio.length() > 0){
                    Integer intAnio = Integer.valueOf(editAnio.getText().toString());
                    if(intAnio < 2018){
                        editAnio.setError("Año inválido");
                        info = 0;
                    }
                }
                if (info==1){
                    InsertaPlacas();
                    registrarPoliza();
                    GoRegVeh();
                    //ObtenerId_Seguro(editTextNoPoliza.getText().toString(),fecha);
                    //ActualizaIntermediaVehiculos(1,ObtenerId_Seguro(););

                }
            }

        });



    }
    /* Metodo Obtencion Placas*/

    public void ObtenerVehiculos3(Integer id_usuario,Integer id_vehiculo){
        APIServer service = Cliente.getAPIServer();

        final DatosVehiculo_IUGV1 datosVehiculo_iugv1 = new DatosVehiculo_IUGV1();
        Call<List<DatosVehiculo_IUGV1>> respuesta = service.ObtenerVehiculos3(id_usuario,id_vehiculo);
        respuesta.enqueue(new Callback<List<DatosVehiculo_IUGV1>>() {
            @Override
            public void onResponse(Call<List<DatosVehiculo_IUGV1>> call, retrofit2.Response<List<DatosVehiculo_IUGV1>> response) {
                //Toast.makeText(IUDM1_Datos_Medicos.this, "BIEN", Toast.LENGTH_SHORT).show();
                List<DatosVehiculo_IUGV1> listPost = response.body();
                content = "";
                for(DatosVehiculo_IUGV1 datosVehiculo_iugv1: listPost){
                    content += datosVehiculo_iugv1.getNu_placas();

                }
                editTextPlacas.setText(content);

                Log.d(TAG, "Respuesta: "+content);
            }

            @Override
            public void onFailure(Call<List<DatosVehiculo_IUGV1>> call, Throwable t) {
                //Toast.makeText(IUDM1_Datos_Medicos.this, "MAL", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void ObtenerPoliza(Integer id_usuario,Integer id_vehiculo){
        APIServer service = Cliente.getAPIServer();

        final DatosPoliza_IUGV1 datosPoliza_iugv1 = new DatosPoliza_IUGV1();
        Call<List<DatosPoliza_IUGV1>> respuesta = service.ObtenerPoliza(id_usuario,id_vehiculo);
        respuesta.enqueue(new Callback<List<DatosPoliza_IUGV1>>() {
            @Override
            public void onResponse(Call<List<DatosPoliza_IUGV1>> call, retrofit2.Response<List<DatosPoliza_IUGV1>> response) {
                //Toast.makeText(IUDM1_Datos_Medicos.this, "BIEN", Toast.LENGTH_SHORT).show();
                List<DatosPoliza_IUGV1> listPost = response.body();
                content = "";
                for(DatosPoliza_IUGV1 datosPoliza_iugv1: listPost){
                    content += datosPoliza_iugv1.getNu_poliza();

                }
                editTextNoPoliza.setText(content);

                Log.d(TAG, "Respuesta: "+content);
            }

            @Override
            public void onFailure(Call<List<DatosPoliza_IUGV1>> call, Throwable t) {
                //Toast.makeText(IUDM1_Datos_Medicos.this, "MAL", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void ObtenerId_Seguro(String nu_poliza, String fh_vigencia){
        APIServer service = Cliente.getAPIServer();

        final ObtenIdSeguroRequest obtenIdSeguroRequest = new ObtenIdSeguroRequest();
        Call<List<ObtenIdSeguroRequest>> respuesta = service.ObtenerIdSeguro(nu_poliza,fh_vigencia);
        respuesta.enqueue(new Callback<List<ObtenIdSeguroRequest>>() {
            @Override
            public void onResponse(Call<List<ObtenIdSeguroRequest>> call, retrofit2.Response<List<ObtenIdSeguroRequest>> response) {
                //Toast.makeText(IUDM1_Datos_Medicos.this, "BIEN", Toast.LENGTH_SHORT).show();
                List<ObtenIdSeguroRequest> listPost = response.body();
                content = "";
                for(ObtenIdSeguroRequest obtenIdSeguroRequest1: listPost){
                    content += obtenIdSeguroRequest.getId_seguro();

                }
               // Toast.makeText(IUGV1_3_Editar_Vehiculo.this, "Id_Seguro"+obtenIdSeguroRequest.getId_seguro(), Toast.LENGTH_SHORT).show();

                Log.d(TAG, "Respuesta: "+content);
            }

            @Override
            public void onFailure(Call<List<ObtenIdSeguroRequest>> call, Throwable t) {
                //Toast.makeText(IUDM1_Datos_Medicos.this, "MAL", Toast.LENGTH_SHORT).show();
            }
        });

    }



    /* Metodos de insercion*/

    public void InsertaPlacas(){
        APIServer service = Cliente.getAPIServer();
        CrearVehiculoRequest insertaPlacas = new CrearVehiculoRequest(null,1,editTextPlacas.getText().toString(),null,null);
        Call<Response> call=(Call<Response>) service.crearVehiculo(insertaPlacas);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {

                //Toast.makeText(IUGV1_2_Registrar_Vehiculo.this, "Placas Registradas", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                //Toast.makeText(IUGV1_2_Registrar_Vehiculo.this, "Algo Salio Mal", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void registrarPoliza(){

        APIServer service = Cliente.getAPIServer();

        RegistrarPolizaRequest poliza = new RegistrarPolizaRequest(editTextNoPoliza.getText().toString(),fecha);


        Call<Response> call = (Call<Response>) service.registrarPoliza(poliza);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
               // Toast.makeText(IUGV1_2_Registrar_Vehiculo.this, "Bien", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                //Toast.makeText(IUGV1_2_Registrar_Vehiculo.this, "Mal"+t, Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void ActualizaIntermediaVehiculos(Integer id_vehiculo_seguro, Integer id_seguro, Integer id_vehiculo){
        APIServer service = Cliente.getAPIServer();
        final IntermediaVehiculosRequest actualizaIntermediaVehiculos = new IntermediaVehiculosRequest();
        Call<Response> call=(Call<Response>) service.actualizarIntermediaVehiculos(actualizaIntermediaVehiculos);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                response.body().getSuccesfull();
                Log.d(TAG, "onResponse: "+response.body().getSuccesfull());
               // Toast.makeText(IUGV1_2_Registrar_Vehiculo.this, "Intermedia Actualizada", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                //Toast.makeText(IUGV1_2_Registrar_Vehiculo.this, "Algo Salio Mal", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void GoRegVeh(){
        Intent GoRegVeh = new Intent(this, Prueba_de_Listview.class);
        startActivity(GoRegVeh);
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