package com.example.menuprincipal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //Metodo Para ir GestionNotificacione

            public void GestionNoti(View view){
            Intent GesNot = new Intent(this,GestionNot.class);
            startActivity(GesNot);
                                              }

    //Metodo Para ir Configuracion de Notificaciones

    public void ConfigurarNotificacion(View view){
        Intent ConfigNoti = new Intent(this,ConfigurarNot.class);
        startActivity(ConfigNoti);
    }

    //Metodo Para ir GestionConta

    public void GestContact(View view){
        Intent GestCon  = new Intent(this,Registrar_Contactos.class);
        startActivity(GestCon);
    }
}

