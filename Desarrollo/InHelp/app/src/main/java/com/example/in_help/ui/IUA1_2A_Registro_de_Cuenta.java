package com.example.in_help.ui;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.in_help.R;

public class IUA1_2A_Registro_de_Cuenta extends AppCompatActivity {

    private Spinner spinner1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iua1_2_a__registro_de__cuenta);

        spinner1 = (Spinner) findViewById(R.id.spinnerSexo);

        String [] sexos = {"Hombre","Mujer","Otro"};
        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_selectable_list_item, sexos);
        spinner1 .setAdapter(adapter);

        setupActionBar();
    }

    public void GoFinalizarRegistro(View vieaw){
        Intent GoFinalizarRegistro = new Intent(this, IUA1_5_FinalizarRegistro.class);
        startActivity(GoFinalizarRegistro);
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
