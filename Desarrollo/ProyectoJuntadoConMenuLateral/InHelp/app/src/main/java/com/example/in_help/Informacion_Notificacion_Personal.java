package com.example.in_help;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Informacion_Notificacion_Personal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion__notificacion__personal);
    }

    public void InfoGralNot(View view){
        Intent InforNotif = new Intent(this,Informacion_General_Notificacion.class);
        startActivity(InforNotif);
    }

}
