package com.example.in_help.ui;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.in_help.R;

public class IUA1_2_Registro_de_Cuenta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iua1_2__registro_de__cuenta);
        setupActionBar();
    }

    public void GoRegistroA(View view){
        Intent GoRegistroA = new Intent(this, IUA1_2A_Registro_de_Cuenta.class);
        startActivity(GoRegistroA);
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
