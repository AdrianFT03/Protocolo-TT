package com.example.in_help.ui;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.in_help.Prueba_MenuLateral2;
import com.example.in_help.R;

public class IUA1_5B_Finalizar_Registro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iua1_5_b__finalizar__registro);

        setupActionBar();
    }



    public void GoRegistroContactos(View view){
        Intent GoRegistroContactos = new Intent(this, Prueba_MenuLateral2.class);
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
