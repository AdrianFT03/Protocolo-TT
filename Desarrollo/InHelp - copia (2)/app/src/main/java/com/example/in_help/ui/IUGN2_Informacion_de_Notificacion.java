package com.example.in_help.ui;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.in_help.R;

public class IUGN2_Informacion_de_Notificacion extends AppCompatActivity {
    TextView placas,tipoevento,fechanotif,ubica;
    ImageView coche;
    Fragment fragment ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setupActionBar();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iugn2__informacion_de__notificacion);

        final Datos_IUGN1 objeto = (Datos_IUGN1) getIntent().getExtras().getSerializable("DatosIUGN1");

        coche = (ImageView) findViewById(R.id.IUGN2imageView);

        placas = (TextView) findViewById(R.id.IUGN2textView0);
        tipoevento = (TextView) findViewById(R.id.IUGN2textView);
        fechanotif = (TextView) findViewById(R.id.IUGN2textView3);
        ubica = (TextView) findViewById(R.id.IUGN2textView4);

        coche.setImageResource(objeto.getImagen1());
        placas.setText(objeto.getPlacas());

        if(objeto.getImagen1() == R.mipmap.vehiculoa){
            tipoevento.setText("Medio");
        }else if(objeto.getImagen1() == R.mipmap.vehiculor){
            tipoevento.setText("Fuerte");
        }else if(objeto.getImagen1() == R.mipmap.vehiculov){
            tipoevento.setText("Moderado");
        }

        fechanotif.setText(objeto.getFh_notificacion());
        ubica.setText("Latitud: "+objeto.getLatitud()+"\n"+"Longitud: "+objeto.getLongitud());

        Bundle bundle = new Bundle();
        bundle.putFloat("latitud",objeto.getLatitud());
        bundle.putFloat("longitud",objeto.getLongitud());

        fragment = new MapFragment();
        fragment.setArguments(bundle);
        changefragment(fragment);


    }

    private void setupActionBar(){
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("");
        }
    }


    private void changefragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
    }
}
