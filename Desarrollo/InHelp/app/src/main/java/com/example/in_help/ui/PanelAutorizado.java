package com.example.menuprincipal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PanelAutorizado extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel_autorizado);
    }

    //Metodo Para ir Menú Principal

    public void MenuPrincipal(View view){
        Intent MenPr  = new Intent(this,MainActivity.class);
        startActivity(MenPr);
    }
}
