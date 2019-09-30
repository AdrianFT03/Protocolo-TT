package com.example.in_help.ui;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.in_help.R;

public class IUA1_5_FinalizarRegistro extends AppCompatActivity {

    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iua1_5__finalizar_registro);

        spinner = (Spinner) findViewById(R.id.TipoSangre);

        String [] tiposangre = {"A+","A-","B+","B-","O+","O-"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_selectable_list_item, tiposangre);
        spinner.setAdapter(adapter);

        setupActionBar();
    }

    public void GoFinalizarRegistroA(View view){
        Intent GoFinalizarRegistroA = new Intent(this, IUA1_5A_Finalizar_Registro.class);
        startActivity(GoFinalizarRegistroA);
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
