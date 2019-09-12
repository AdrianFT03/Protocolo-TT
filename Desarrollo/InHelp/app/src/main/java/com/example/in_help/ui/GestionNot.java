package com.example.menuprincipal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class GestionNot extends AppCompatActivity {

    private TextView Noti1,Noti2,Noti3,Noti4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_not);

        Noti1 = (TextView)findViewById(R.id.Not1);
        Noti2 = (TextView) findViewById(R.id.Not2);
        Noti3 = (TextView) findViewById(R.id.Not3);
        Noti4 = (TextView) findViewById(R.id.Not4);


    }


    //Metodo Para ir Menú Principal

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
