package com.example.in_help.ui;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.in_help.R;

public class IUA1_5B_Finalizar_Registro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iua1_5_b__finalizar__registro);

        setupActionBar();
    }

    public void GoRegistroFinalizado(View view){
        Intent GoRegistroFinalizado = new Intent(this, IUA1_6_Registro_Finalizado.class);
        startActivity(GoRegistroFinalizado);
    }

    public void GoRegistroContactos(View view){
        Intent GoRegistroContactos = new Intent(this, IUA1_7_Registro_de_Contactos.class);
        startActivity(GoRegistroContactos);
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
