package com.example.menuprincipal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ConfigurarNot extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurar_not);
    }
    //Metodo Para ir Configuracion de Notificaciones

    public void SwitchNumeros(View view){
        Intent SwNum = new Intent(this,SwitchConfigNoti.class);
        startActivity(SwNum);
    }
}
