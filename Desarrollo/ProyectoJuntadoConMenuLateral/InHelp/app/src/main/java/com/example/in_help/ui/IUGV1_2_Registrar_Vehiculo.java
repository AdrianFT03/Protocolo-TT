package com.example.in_help.ui;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.in_help.R;

public class IUGV1_2_Registrar_Vehiculo extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iugv1_2__registrar__vehiculo);

        setupActionBar();
    }

    public void GoRegistroVehiculos(View view){
        Intent GoRegistroVehiculos = new Intent(this, IUPP1A_Pantalla_Principal.class);
        startActivity(GoRegistroVehiculos);
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
