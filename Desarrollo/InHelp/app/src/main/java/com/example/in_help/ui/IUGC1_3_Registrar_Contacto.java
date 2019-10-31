package com.example.in_help.ui;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.in_help.R;

import retrofit2.Call;
import retrofit2.Callback;

public class IUGC1_3_Registrar_Contacto extends AppCompatActivity {


    public Spinner spinnerParentezco;

    public Integer info=1;

    public RadioGroup radioGroup;
    public RadioButton radioButtonSiInfo;
    public RadioButton radioButtonNoInfo;
    public RadioButton radioButtonF;
    public RadioButton radioButtonA;
    public RadioButton radioButtonE;


    public TextView textViewtipo;
    public TextView textViewnombre;
    public TextView textViewprimerapp;
    public TextView textViewsegapp;
    public TextView textViewparentezco;
    public TextView textViewnumero;
    public TextView textViewmasinfotex;
    public TextView textViewnoPoliza;
    public TextView textViewfechavigenci;
    public TextView textViewdia;
    public TextView textViewmes;
    public TextView textViewanio;

    public EditText editTextNombre;
    public EditText editTextPrimerAp;
    public EditText editTextSegundAp;
    public EditText editTextNumero;
    public EditText editTextPoliza;
    public EditText editTextDia;
    public EditText editTextMes;
    public EditText editTextAnio;

    public Button button;

    public String fecha;
    String sexo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iugc1_3__registrar__contacto);
        setupActionBar();

        spinnerParentezco = (Spinner) findViewById(R.id.IUGC1_3spinnerParen);

        radioGroup = (RadioGroup) findViewById(R.id.IUGC1_3radioGroup);
        radioButtonSiInfo = (RadioButton) findViewById(R.id.IUGC1_3radioButtonSi);
        radioButtonNoInfo = (RadioButton) findViewById(R.id.IUGC1_3radioButtonNo);
        radioButtonA = (RadioButton) findViewById(R.id.IUGC1_3radioButtonA);
        radioButtonE = (RadioButton) findViewById(R.id.IUGC1_3radioButtonE);
        radioButtonF = (RadioButton) findViewById(R.id.IUGC1_3radioButtonF);

        textViewtipo = (TextView) findViewById(R.id.IUGC1_3textViewTipo);
        textViewnombre = (TextView) findViewById(R.id.IUGC1_3textViewNombre);
        textViewprimerapp = (TextView) findViewById(R.id.IUGC1_3textViewPA);
        textViewsegapp = (TextView) findViewById(R.id.IUGC1_3textViewSA);
        textViewparentezco = (TextView) findViewById(R.id.IUGC1_3textViewParente);
        textViewnumero = (TextView) findViewById(R.id.IUGC1_3textViewTelefono);
        textViewmasinfotex = (TextView) findViewById(R.id.IUGC1_3textViewMasDe);
        textViewnoPoliza = (TextView) findViewById(R.id.IUGC1_3textViewNoPol);
        textViewfechavigenci = (TextView) findViewById(R.id.IUGC1_3textViewFechaVig);
        textViewdia = (TextView) findViewById(R.id.IUGC1_3textViewDia);
        textViewmes = (TextView) findViewById(R.id.IUGC1_3textViewMes);
        textViewanio = (TextView) findViewById(R.id.IUGC1_3textViewaño);

        editTextNombre = (EditText) findViewById(R.id.IUGC1_3editTextNombre);
        editTextPrimerAp = (EditText) findViewById(R.id.IUGC1_3EditTextPA);
        editTextSegundAp = (EditText) findViewById(R.id.IUGC1_3EditTextSA);
        editTextNumero = (EditText) findViewById(R.id.IUGC1_3EditTexTelefono);
        editTextPoliza = (EditText) findViewById(R.id.IUGC1_3editTextPoli);
        editTextDia = (EditText) findViewById(R.id.IUGC1_3editTextDia);
        editTextMes = (EditText) findViewById(R.id.IUGC1_3editTextMes);
        editTextAnio = (EditText) findViewById(R.id.IUGC1_3editTextaño);

        button = (Button) findViewById(R.id.IUGC1_3button);

        String [] parenezco = {"Hermano","Padre","Madre","Otro"};
        ArrayAdapter<String> adapterParentezco = new ArrayAdapter<String>(this, android.R.layout.simple_selectable_list_item, parenezco);
        spinnerParentezco.setAdapter(adapterParentezco);

            radioGroup.setVisibility(View.GONE) ;
            radioButtonSiInfo.setVisibility(View.GONE) ;
            radioButtonNoInfo.setVisibility(View.GONE) ;

            textViewnombre.setVisibility(View.GONE) ;
            textViewprimerapp.setVisibility(View.GONE) ;
            textViewsegapp.setVisibility(View.GONE) ;
            textViewparentezco.setVisibility(View.GONE) ;
            textViewnumero.setVisibility(View.GONE) ;
            textViewmasinfotex.setVisibility(View.GONE) ;
            textViewnoPoliza.setVisibility(View.GONE) ;
            textViewfechavigenci.setVisibility(View.GONE);
            textViewdia.setVisibility(View.GONE) ;
            textViewmes.setVisibility(View.GONE) ;
            textViewanio.setVisibility(View.GONE) ;

            editTextNombre.setVisibility(View.GONE) ;
            editTextPrimerAp.setVisibility(View.GONE) ;
            editTextSegundAp.setVisibility(View.GONE) ;
            editTextNumero.setVisibility(View.GONE) ;
            editTextPoliza.setVisibility(View.GONE) ;
            editTextDia.setVisibility(View.GONE) ;
            editTextMes.setVisibility(View.GONE) ;
            editTextAnio.setVisibility(View.GONE) ;

            spinnerParentezco.setVisibility(View.GONE);

            button.setVisibility(View.GONE);

            radioButtonA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    radioGroup.setVisibility(View.VISIBLE) ;
                    radioButtonSiInfo.setVisibility(View.VISIBLE) ;
                    radioButtonSiInfo.isChecked();
                    radioButtonNoInfo.setVisibility(View.VISIBLE) ;

                    textViewnombre.setVisibility(View.VISIBLE) ;
                    textViewprimerapp.setVisibility(View.GONE) ;
                    textViewsegapp.setVisibility(View.GONE) ;
                    textViewparentezco.setVisibility(View.GONE) ;
                    textViewnumero.setVisibility(View.VISIBLE) ;
                    textViewmasinfotex.setVisibility(View.VISIBLE) ;
                    textViewnoPoliza.setVisibility(View.VISIBLE) ;
                    textViewfechavigenci.setVisibility(View.VISIBLE);
                    textViewdia.setVisibility(View.VISIBLE) ;
                    textViewmes.setVisibility(View.VISIBLE) ;
                    textViewanio.setVisibility(View.VISIBLE) ;

                    editTextNombre.setVisibility(View.VISIBLE) ;
                    editTextPrimerAp.setVisibility(View.GONE) ;
                    editTextSegundAp.setVisibility(View.GONE) ;
                    editTextNumero.setVisibility(View.VISIBLE) ;
                    editTextPoliza.setVisibility(View.VISIBLE) ;
                    editTextDia.setVisibility(View.VISIBLE) ;
                    editTextMes.setVisibility(View.VISIBLE) ;
                    editTextAnio.setVisibility(View.VISIBLE) ;

                    spinnerParentezco.setVisibility(View.GONE);

                    button.setVisibility(View.VISIBLE);

                    editTextNombre.setText("");
                    editTextPrimerAp.setText("");
                    editTextSegundAp.setText("");
                    editTextNumero.setText("");
                    editTextPoliza.setText("");
                    editTextDia.setText("");
                    editTextMes.setText("");
                    editTextAnio.setText("");
                }
            });
            radioButtonE.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    radioGroup.setVisibility(View.GONE) ;
                    radioButtonSiInfo.setVisibility(View.GONE) ;
                    radioButtonSiInfo.isChecked();
                    radioButtonNoInfo.setVisibility(View.GONE) ;

                    textViewnombre.setVisibility(View.VISIBLE) ;
                    textViewprimerapp.setVisibility(View.GONE) ;
                    textViewsegapp.setVisibility(View.GONE) ;
                    textViewparentezco.setVisibility(View.GONE) ;
                    textViewnumero.setVisibility(View.VISIBLE) ;
                    textViewmasinfotex.setVisibility(View.GONE) ;
                    textViewnoPoliza.setVisibility(View.GONE) ;
                    textViewfechavigenci.setVisibility(View.GONE);
                    textViewdia.setVisibility(View.GONE) ;
                    textViewmes.setVisibility(View.GONE) ;
                    textViewanio.setVisibility(View.GONE) ;

                    editTextNombre.setVisibility(View.VISIBLE) ;
                    editTextPrimerAp.setVisibility(View.GONE) ;
                    editTextSegundAp.setVisibility(View.GONE) ;
                    editTextNumero.setVisibility(View.VISIBLE) ;
                    editTextPoliza.setVisibility(View.GONE) ;
                    editTextDia.setVisibility(View.GONE) ;
                    editTextMes.setVisibility(View.GONE) ;
                    editTextAnio.setVisibility(View.GONE) ;

                    spinnerParentezco.setVisibility(View.GONE);

                    button.setVisibility(View.VISIBLE);

                    editTextNombre.setText("");
                    editTextPrimerAp.setText("");
                    editTextSegundAp.setText("");
                    editTextNumero.setText("");
                    editTextPoliza.setText("");
                    editTextDia.setText("");
                    editTextMes.setText("");
                    editTextAnio.setText("");
                }
            });
            radioButtonF.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    radioGroup.setVisibility(View.GONE) ;
                    radioButtonSiInfo.setVisibility(View.GONE) ;
                    radioButtonSiInfo.isChecked();
                    radioButtonNoInfo.setVisibility(View.GONE) ;

                    textViewnombre.setVisibility(View.VISIBLE) ;
                    textViewprimerapp.setVisibility(View.VISIBLE) ;
                    textViewsegapp.setVisibility(View.VISIBLE) ;
                    textViewparentezco.setVisibility(View.VISIBLE) ;
                    textViewnumero.setVisibility(View.VISIBLE) ;
                    textViewmasinfotex.setVisibility(View.GONE) ;
                    textViewnoPoliza.setVisibility(View.GONE) ;
                    textViewfechavigenci.setVisibility(View.GONE);
                    textViewdia.setVisibility(View.GONE) ;
                    textViewmes.setVisibility(View.GONE) ;
                    textViewanio.setVisibility(View.GONE) ;

                    editTextNombre.setVisibility(View.VISIBLE) ;
                    editTextPrimerAp.setVisibility(View.VISIBLE) ;
                    editTextSegundAp.setVisibility(View.VISIBLE) ;
                    editTextNumero.setVisibility(View.VISIBLE) ;
                    editTextPoliza.setVisibility(View.GONE) ;
                    editTextDia.setVisibility(View.GONE) ;
                    editTextMes.setVisibility(View.GONE) ;
                    editTextAnio.setVisibility(View.GONE) ;

                    spinnerParentezco.setVisibility(View.VISIBLE);

                    button.setVisibility(View.VISIBLE);

                    editTextNombre.setText("");
                    editTextPrimerAp.setText("");
                    editTextSegundAp.setText("");
                    editTextNumero.setText("");
                    editTextPoliza.setText("");
                    editTextDia.setText("");
                    editTextMes.setText("");
                    editTextAnio.setText("");
                }
            });

            radioButtonSiInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    textViewnoPoliza.setVisibility(View.VISIBLE) ;
                    textViewfechavigenci.setVisibility(View.VISIBLE);
                    textViewdia.setVisibility(View.VISIBLE) ;
                    textViewmes.setVisibility(View.VISIBLE) ;
                    textViewanio.setVisibility(View.VISIBLE);
                    editTextPoliza.setVisibility(View.VISIBLE) ;
                    editTextDia.setVisibility(View.VISIBLE) ;
                    editTextMes.setVisibility(View.VISIBLE) ;
                    editTextAnio.setVisibility(View.VISIBLE) ;
                }
            });

            radioButtonNoInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    textViewnoPoliza.setVisibility(View.GONE) ;
                    textViewfechavigenci.setVisibility(View.GONE);
                    textViewdia.setVisibility(View.GONE) ;
                    textViewmes.setVisibility(View.GONE) ;
                    textViewanio.setVisibility(View.GONE);
                    editTextPoliza.setVisibility(View.GONE) ;
                    editTextDia.setVisibility(View.GONE) ;
                    editTextMes.setVisibility(View.GONE) ;
                    editTextAnio.setVisibility(View.GONE) ;
                }
            });

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    StringBuilder fecha1 = new StringBuilder();

                    fecha1.append(editTextAnio.getText().toString());
                    fecha1.append("-");
                    fecha1.append(editTextMes.getText().toString());
                    fecha1.append("-");
                    fecha1.append(editTextDia.getText().toString());
                    fecha = fecha1.toString();

                    sexo = spinnerParentezco.getSelectedItem().toString();

                    info = 1;
                    if(radioButtonA.isChecked()){ // Aseguradora

                        if(editTextNombre.length()==0){
                            editTextNombre.setError("Campo obligatorio");
                            info = 0;
                        }else if(editTextNombre.length() > 0 && editTextNombre.length() < 3) {
                            editTextNombre.setError("El nombre debe tener almenos 3 caracteres");
                            info = 0;
                        }

                        if(editTextNumero.length() == 0){
                            editTextNumero.setError("Campo obligatorio");
                            info = 0;
                        }else if(editTextNumero.length() > 0 && editTextNumero.length()<10){
                            editTextNumero.setError("El teléfono debe tener 10 dígitos");
                            info = 0;
                        }

                        if(radioButtonSiInfo.isChecked()){
                            if(editTextPoliza.length() == 0){
                                editTextPoliza.setError("Campo obligatorio");
                                info = 0;
                            }
                            if(editTextDia.length() == 0){
                                editTextDia.setError("Campo obligatorio");
                                info = 0;
                            }else if(editTextDia.length() > 0 && editTextDia.length()<2){
                                editTextDia.setError("El día debe tener 2 caracteres");
                                info = 0;
                            }else if(editTextDia.getText().toString().equals("00")){
                                editTextDia.setError("Error en formato");
                                info = 0;
                            }else if(editTextDia.length() > 0){
                                Integer intDia = Integer.valueOf(editTextDia.getText().toString());
                                if(intDia > 31){
                                    editTextDia.setError("Día inválido");
                                    info = 0;
                                }
                            }

                            if(editTextMes.length() == 0){
                                editTextMes.setError("Campo obligatorio");
                                info = 0;
                            }else if(editTextMes.length() > 0 && editTextMes.length()<2){
                                editTextMes.setError("El mes debe tener 2 caracteres");
                                info = 0;
                            }else if(editTextMes.getText().toString().equals("00")){
                                editTextMes.setError("Error en formato");
                                info = 0;
                            }else if(editTextMes.length() > 0){
                                Integer intMes = Integer.valueOf(editTextMes.getText().toString());
                                if(intMes > 12){
                                    editTextMes.setError("Mes inválido");
                                    info = 0;
                                }
                            }

                            if(editTextAnio.length() == 0){
                                editTextAnio.setError("Campo obligatorio");
                                info = 0;
                            }else if(editTextAnio.length() > 0 && editTextAnio.length()<2){
                                editTextAnio.setError("El año debe tener 4 caracteres");
                                info = 0;
                            }else if(editTextAnio.getText().toString().equals("0000")){
                                editTextAnio.setError("Error en formato");
                                info = 0;
                            }
                        }else if(!radioButtonSiInfo.isChecked()){

                            editTextPoliza.setText("");
                            editTextDia.setText("");
                            editTextMes.setText("");
                            editTextAnio.setText("");
                        }

                        if(info == 1){
                            if(radioButtonSiInfo.isChecked()){
                                RegistrarContacto(null,null,null,2,1,editTextNombre.getText().toString(),null,null,editTextNumero.getText().toString(),editTextPoliza.getText().toString(),fecha);//Aseguradora

                            }
                            if(radioButtonNoInfo.isChecked()){
                                RegistrarContacto(null,null,null,2,1,editTextNombre.getText().toString(),null,null,editTextNumero.getText().toString(),null,null);//Aseguradora

                            }
                        }else if(info == 0){
                            Toast.makeText(IUGC1_3_Registrar_Contacto.this, "Información incorrecta", Toast.LENGTH_SHORT).show();
                        }

                        Toast.makeText(IUGC1_3_Registrar_Contacto.this, "Validacion A", Toast.LENGTH_SHORT).show();



                    }else if(radioButtonE.isChecked()){ // EMERGENICA

                        info = 1;

                        if(editTextNombre.length()==0){
                            editTextNombre.setError("Campo obligatorio");
                            info = 0;
                        }else if(editTextNombre.length() > 0 && editTextNombre.length() < 3) {
                            editTextNombre.setError("El nombre debe tener almenos 3 caracteres");
                            info = 0;
                        }
                        if(editTextNumero.length() == 0){
                            editTextNumero.setError("Campo obligatorio");
                            info = 0;
                        }

                        if (info == 1){
                            RegistrarContacto(null,null,null,3,1,editTextNombre.getText().toString(),null,null,editTextNumero.getText().toString(),null,null);//Aseguradora

                        }else if(info == 0){
                            Toast.makeText(IUGC1_3_Registrar_Contacto.this, "Información Incorrecta", Toast.LENGTH_SHORT).show();
                        }

                        //Toast.makeText(IUGC1_3_Registrar_Contacto.this, "Validacion E", Toast.LENGTH_SHORT).show();

                    }else if(radioButtonF.isChecked()){ //FAMILIAR

                        if(editTextNombre.length()==0){
                            editTextNombre.setError("Campo obligatorio");
                            info = 0;
                        }else if(editTextNombre.length() > 0 && editTextNombre.length() < 3) {
                            editTextNombre.setError("El nombre debe tener almenos 3 caracteres");
                            info = 0;
                        }
                        if(editTextPrimerAp.length() == 0){
                            editTextPrimerAp.setError("Campo obligatorio");
                            info = 0;
                        }else if(editTextPrimerAp.length() > 0 && editTextPrimerAp.length()<3){
                            editTextPrimerAp.setError("El apellido debe tener al menos 3 caracteres");
                            info = 0;
                        }
                        if(editTextSegundAp.length() > 0 && editTextSegundAp.length()<3){
                            editTextSegundAp.setError("El apellido debe tener al menos 3 caracteres");
                            info = 0;
                        }

                        if(editTextNumero.length() == 0){
                            editTextNumero.setError("Campo obligatorio");
                            info = 0;
                        }else if(editTextNumero.length() > 0 && editTextNumero.length()<10){
                            editTextNumero.setError("El teléfono debe tener 10 dígitos");
                            info = 0;
                        }

                        if (info == 1){
                            if(editTextSegundAp.length() == 0){
                                if(sexo.equals("Hermano")){
                                    RegistrarContacto(null,null,1,1,1,editTextNombre.getText().toString(),editTextPrimerAp.getText().toString(),null,editTextNumero.getText().toString(),null,null);//Aseguradora

                                }else if(sexo.equals("Padre")){
                                    RegistrarContacto(null,null,2,1,1,editTextNombre.getText().toString(),editTextPrimerAp.getText().toString(),null,editTextNumero.getText().toString(),null,null);//Aseguradora

                                }else if(sexo.equals("Madre")){
                                    RegistrarContacto(null,null,3,1,1,editTextNombre.getText().toString(),editTextPrimerAp.getText().toString(),null,editTextNumero.getText().toString(),null,null);//Aseguradora

                                }else if(sexo.equals("Otro")){
                                    RegistrarContacto(null,null,4,1,1,editTextNombre.getText().toString(),editTextPrimerAp.getText().toString(),null,editTextNumero.getText().toString(),null,null);//Aseguradora

                                }

                            }else {
                                if(sexo.equals("Hermano")){
                                    RegistrarContacto(null,null,1,1,1,editTextNombre.getText().toString(),editTextPrimerAp.getText().toString(),null,editTextNumero.getText().toString(),null,null);//Aseguradora

                                }else if(sexo.equals("Padre")){
                                    RegistrarContacto(null,null,2,1,1,editTextNombre.getText().toString(),editTextPrimerAp.getText().toString(),null,editTextNumero.getText().toString(),null,null);//Aseguradora

                                }else if(sexo.equals("Madre")){
                                    RegistrarContacto(null,null,3,1,1,editTextNombre.getText().toString(),editTextPrimerAp.getText().toString(),null,editTextNumero.getText().toString(),null,null);//Aseguradora

                                }else if(sexo.equals("Otro")){
                                    RegistrarContacto(null,null,4,1,1,editTextNombre.getText().toString(),editTextPrimerAp.getText().toString(),null,editTextNumero.getText().toString(),null,null);//Aseguradora

                                }
                            }

                        }else if(info == 0){
                            Toast.makeText(IUGC1_3_Registrar_Contacto.this, "Información Incorrecta", Toast.LENGTH_SHORT).show();
                        }


                        //Toast.makeText(IUGC1_3_Registrar_Contacto.this, "Validacion F", Toast.LENGTH_SHORT).show();
                    }

                }
            });


    }


    public void RegistrarContacto(Integer id_contacto,Integer id_sexo,Integer id_parentesco,Integer id_tipo, Integer id_usuario,String tx_nombre,String tx_primer_ap, String tx_seg_ap,String nu_tel, String nu_poliza,String fh_vigencia){
        APIServer service = Cliente.getAPIServer();

        DatosContacto_IUGC1_3 contacto = new DatosContacto_IUGC1_3(id_contacto,id_sexo,id_parentesco,id_tipo,id_usuario,tx_nombre,tx_primer_ap,tx_seg_ap,nu_tel,nu_poliza,fh_vigencia);

        Call<Response> call = (Call<Response>) service.crearContacto(contacto);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                Toast.makeText(IUGC1_3_Registrar_Contacto.this, "Contacto registrado", Toast.LENGTH_SHORT).show();
                Goinicio();
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Toast.makeText(IUGC1_3_Registrar_Contacto.this, "Algo paso", Toast.LENGTH_SHORT).show();
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

    public  void Goinicio(){
        Intent GoInicio = new Intent(this, IUPP1A_Pantalla_Principal.class);
        startActivity(GoInicio);
    }

}


