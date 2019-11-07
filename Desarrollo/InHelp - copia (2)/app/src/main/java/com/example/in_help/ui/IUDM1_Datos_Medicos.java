package com.example.in_help.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.in_help.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IUDM1_Datos_Medicos extends AppCompatActivity {

    public TextView NSS,Tiposangre,Enfermedadcronica ,titulo;

    public String content;
    private static final String TAG = "Log";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iudm1__datos__medicos);

        setupActionBar();

        NSS = (TextView)findViewById(R.id.textView81);
        Tiposangre = (TextView)findViewById(R.id.textView83);
        Enfermedadcronica = (TextView)findViewById(R.id.textView85);
        titulo  = (TextView)findViewById(R.id.textView79);

        ObtenerNumeroSeguiradSocial(1);
        ObtenerEnfermedadCronica(1);
        ObtenerTipoDeSangre(1);

    }

    public void ObtenerNumeroSeguiradSocial(Integer id_usuario){
        APIServer service = Cliente.getAPIServer();

        final NSSRequest nssRequest = new NSSRequest();
        Call<List<NSSRequest>> respuesta = service.ObtenerNSS(id_usuario);
        respuesta.enqueue(new Callback<List<NSSRequest>>() {
            @Override
            public void onResponse(Call<List<NSSRequest>> call, Response<List<NSSRequest>> response) {
                Toast.makeText(IUDM1_Datos_Medicos.this, "BIEN", Toast.LENGTH_SHORT).show();
                List<NSSRequest> listPost = response.body();
                content = "";
                for(NSSRequest nssRequest: listPost){
                    content += nssRequest.getTx_id();

            }
                NSS.setText(content);

                Log.d(TAG, "Respuesta: "+content);
            }

            @Override
            public void onFailure(Call<List<NSSRequest>> call, Throwable t) {
                Toast.makeText(IUDM1_Datos_Medicos.this, "MAL", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void ObtenerTipoDeSangre(Integer id_usuario){
        APIServer service = Cliente.getAPIServer();

        final TipoSangreRequest tipoSangreRequest = new TipoSangreRequest();
        Call<List<TipoSangreRequest>> respuesta = service.ObtenerTipoDeSangre(id_usuario);
        respuesta.enqueue(new Callback<List<TipoSangreRequest>>() {
            @Override
            public void onResponse(Call<List<TipoSangreRequest>> call, Response<List<TipoSangreRequest>> response) {
                Toast.makeText(IUDM1_Datos_Medicos.this, "BIEN", Toast.LENGTH_SHORT).show();
                List<TipoSangreRequest> listPost = response.body();
                content = "";
                for(TipoSangreRequest tipoSangreRequest: listPost){
                    content += tipoSangreRequest.getTx_nombre();

                }
                Tiposangre.setText(content);

                Log.d(TAG, "Respuesta: "+content);
            }

            @Override
            public void onFailure(Call<List<TipoSangreRequest>> call, Throwable t) {
                Toast.makeText(IUDM1_Datos_Medicos.this, "MAL", Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void ObtenerEnfermedadCronica(Integer id_usuario){
        APIServer service = Cliente.getAPIServer();

        final EnfermedadCronicaRequest enfermedadCronicaRequest = new EnfermedadCronicaRequest();
        Call<List<EnfermedadCronicaRequest>> respuesta = service.ObtenerEnfermedadCronica(id_usuario);
        respuesta.enqueue(new Callback<List<EnfermedadCronicaRequest>>() {
            @Override
            public void onResponse(Call<List<EnfermedadCronicaRequest>> call, Response<List<EnfermedadCronicaRequest>> response) {
                //Toast.makeText(IUDM1_Datos_Medicos.this, "BIEN", Toast.LENGTH_SHORT).show();
                List<EnfermedadCronicaRequest> listPost = response.body();
                content = "";
                for(EnfermedadCronicaRequest enfermedadCronicaRequest: listPost){
                    content += enfermedadCronicaRequest.getTx_nombre();

                }
                Enfermedadcronica.setText(content);

                Log.d(TAG, "Respuesta: "+content);
            }

            @Override
            public void onFailure(Call<List<EnfermedadCronicaRequest>> call, Throwable t) {
                Toast.makeText(IUDM1_Datos_Medicos.this, "MAL", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void GoEditDatMedicos(View view){
        Intent GoEditDM = new Intent(this, IUDM2_Editar_Datos_Medicos.class);
        startActivity(GoEditDM);
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
