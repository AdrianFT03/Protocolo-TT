package com.example.in_help.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.in_help.R;

public class Configurar_Notificaciones_Contactos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurar__notificaciones__contactos);


        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            if (bundle.getString("some") != null){
                Toast.makeText(getApplicationContext(), "Configuracion de contactos",
                        Toast.LENGTH_SHORT).show();
            }
        }


    }

    public void InfoGralNot(View view){
        Intent InforNotif = new Intent(this,Informacion_Notificacion_Personal.class);
        startActivity(InforNotif);
    }
}
