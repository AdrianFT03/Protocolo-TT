package com.example.in_help.ui;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.in_help.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IUDP1_Datos_Personales extends AppCompatActivity {

    private static final String TAG = "Log";
    public TextView nombre;
    public TextView primer;
    public TextView segundo;
    public TextView fecha;
    public TextView sexo;
    public String Stringnombre;
    public String Stringprimer;
    public String Stringsegundo;
    public String Stringfecha;
    public String Stringsexo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iudp1__datos__personales);
        setupActionBar();
    nombre = (TextView) findViewById(R.id.textView69);
    primer = (TextView) findViewById(R.id.textView70);
    segundo = (TextView) findViewById(R.id.textView71);
    fecha = (TextView) findViewById(R.id.textView72);
    sexo = (TextView) findViewById(R.id.textView73);


    Obteninfo(1);
    }


    public void Obteninfo(Integer id_persona){
        APIServer service = Cliente.getAPIServer();

        final PersonaRequest personaRequest = new PersonaRequest();
        Call<List<PersonaRequest>> respuesta = service.ObtenerinfoPersona(id_persona);

        respuesta.enqueue(new Callback<List<PersonaRequest>>() {
            @Override
            public void onResponse(Call<List<PersonaRequest>> call, Response<List<PersonaRequest>> response) {

                //Toast.makeText(IUDP1_Datos_Personales.this, "Bien", Toast.LENGTH_SHORT).show();
                List<PersonaRequest> listPost = response.body();
                Stringnombre="";
                Stringprimer="";
                Stringsegundo="";
                Stringfecha="";
                Stringsexo="";
                for(PersonaRequest personaRequest: listPost){
                    Stringnombre += personaRequest.getTx_nombre();
                    Stringprimer += personaRequest.getTx_primer_ap();
                    Stringsegundo += personaRequest.getTx_seg_ap();
                    Stringfecha += personaRequest.getFh_nacimiento();
                    Stringsexo += personaRequest.getId_sexo();
                }
                nombre.setText(Stringnombre);
                primer.setText(Stringprimer);
                segundo.setText(Stringsegundo);
                fecha.setText(Stringfecha.substring(0,10));
                if(Stringsexo.equals("1")){
                    sexo.setText("Masculino");
                }else if(Stringsexo.equals("2")){
                    sexo.setText("Femenino");
                }else if(Stringsexo.equals("3")){
                    sexo.setText("Otro");
                }else if(Stringsexo.equals("4")){
                    sexo.setText("Indefinido");
                }


            }

            @Override
            public void onFailure(Call<List<PersonaRequest>> call, Throwable t) {
                Toast.makeText(IUDP1_Datos_Personales.this, "Mal", Toast.LENGTH_SHORT).show();
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
