package com.example.in_help.ui;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.in_help.R;

public class IUGV1_3_Editar_Vehiculo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iugv1_3__editar__vehiculo);
        setupActionBar();
    }

    private void setupActionBar(){
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null)
        {
            //actionBar.setDisplayHomeAsUpEnabled(true);

            actionBar.setTitle("");
        }
    }

    public void GoVehiculosReg(View view){
        Intent GoEditar = new Intent(this, IUGCV1_1_Vehiculos_Registrados.class);
        startActivity(GoEditar);
    }
}
