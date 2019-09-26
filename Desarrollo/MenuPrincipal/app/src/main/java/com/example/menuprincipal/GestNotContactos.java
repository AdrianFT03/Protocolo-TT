package com.example.menuprincipal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class GestNotContactos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gest_not_contactos);
    }



    public void MenuPrincipal(View view){
        Intent MenPr  = new Intent(this,MainActivity.class);
        startActivity(MenPr);
    }

    //Metodo Para ir Gestion Notificaciones Personales

    public void GestionNoti(View view){
        Intent GesNot = new Intent(this,GestionNot.class);
        startActivity(GesNot);
    }

    //Metodo Para ir Gestion Notificaciones Contactos



    public void GestionNotiCont(View view){
        Intent GesNotCont = new Intent(this,GestNotContactos.class);
        startActivity(GesNotCont);
    }

    //Metodo Para ir Informacion de Notificacion



    public void InfoNot(View view){
        Intent InforNotif = new Intent(this,InfoNoti.class);
        startActivity(InforNotif);
    }
}
