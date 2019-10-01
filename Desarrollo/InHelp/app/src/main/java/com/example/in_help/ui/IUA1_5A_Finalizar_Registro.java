package com.example.in_help.ui;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.in_help.R;

import retrofit2.Call;
import retrofit2.Callback;

public class IUA1_5A_Finalizar_Registro extends AppCompatActivity {

    private static final String TAG = "Log";
    private Spinner spinner2;
    private String perfil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iua1_5_a__finalizar__registro);

        spinner2 = (Spinner) findViewById(R.id.spinnerChofAc);
        Button Siguiente = (Button) findViewById(R.id.button10);

        String [] tipousuario = {"Chofer","Acompañante","Ambos","Indefinido"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_selectable_list_item, tipousuario);
        spinner2.setAdapter(adapter);

        setupActionBar();

        Siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                perfil = spinner2.getSelectedItem().toString();
                Integer intPerfil=0;

                if(perfil.equals("Chofer")){
                    intPerfil = 1;

                }else if(perfil.equals("Acompañante")){
                    intPerfil = 2;

                }else if(perfil.equals("Ambos")){
                    intPerfil = 3;

                }else if(perfil.equals("Indefinido")){
                    intPerfil = 4;
                }

                guardarCuenta(null,1,intPerfil,"2019-01-01","2019-01-01");
                GoFinalizarRegistroB();
            }
        });
    }

    public void guardarCuenta(Integer id_cuenta,Integer id_usuario,Integer id_perfil,String fh_inicio, String fh_termino){
        APIServer service = Cliente.getAPIServer();

        CuentaRequest cuentaRequest = new CuentaRequest(id_cuenta,id_usuario,id_perfil,fh_inicio,fh_termino);

        Call<Response> call = (Call<Response>) service.crearCuenta(cuentaRequest);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                response.body().getSuccesfull();
                Log.d(TAG, "onResponse: "+response.body().getSuccesfull());
                //Toast.makeText(IUA1_5A_Finalizar_Registro.this, "Cuenta registrada", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t);
                Toast.makeText(IUA1_5A_Finalizar_Registro.this, "Error cuenta", Toast.LENGTH_SHORT).show();
            }
        });
    }





    public void GoFinalizarRegistroB(){
        Intent GoFinalizarRegistroB = new Intent(this, IUA1_5B_Finalizar_Registro.class);
        startActivity(GoFinalizarRegistroB);
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
