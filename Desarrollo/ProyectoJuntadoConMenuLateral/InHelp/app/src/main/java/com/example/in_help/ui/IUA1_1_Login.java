package com.example.in_help.ui;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.in_help.R;

public class IUA1_1_Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iua1_1__login);
        setupActionBar();
    }

    public void GoContrasenia(View view){
        Intent GoContrasenia = new Intent(this, IUA1_3_Recuperar_Cuenta.class);
        startActivity(GoContrasenia);
    }

    public  void GoInicio(View view){
        Intent GoInicio = new Intent(this, IUPP1A_Pantalla_Principal.class);
        startActivity(GoInicio);
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
