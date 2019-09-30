package com.example.in_help.ui;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.in_help.R;

public class IUA1_5A_Finalizar_Registro extends AppCompatActivity {

    private Spinner spinner2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iua1_5_a__finalizar__registro);

        spinner2 = (Spinner) findViewById(R.id.spinnerChofAc);

        String [] tipousuario = {"Chofer","Acompañante"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_selectable_list_item, tipousuario);
        spinner2.setAdapter(adapter);

        setupActionBar();
    }

    public void GoFinalizarRegistroB(View view){
        Intent GoFinalizarRegistroB = new Intent(this, IUA1_5B_Finalizar_Registro.class);
        startActivity(GoFinalizarRegistroB);
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
