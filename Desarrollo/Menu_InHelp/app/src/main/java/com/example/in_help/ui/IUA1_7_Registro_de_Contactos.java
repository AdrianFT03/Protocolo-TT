package com.example.in_help.ui;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.in_help.R;

public class IUA1_7_Registro_de_Contactos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iua1_7__registro_de__contactos);

        setupActionBar();
    }

    public void GoRegistroFinalizado1(View view){
        Intent GoRegistroFinalizado1 = new Intent(this, IUPP1A_Pantalla_Principal.class);
        startActivity(GoRegistroFinalizado1);
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
