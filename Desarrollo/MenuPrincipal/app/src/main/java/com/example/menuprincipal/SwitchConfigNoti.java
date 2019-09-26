package com.example.menuprincipal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SwitchConfigNoti extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_config_noti);
    }
    //Metodo Para ir Configuracion de Notificaciones

    public void PanelAutorizacion(View view){
        Intent Pauto = new Intent(this,PanelAutorizado.class);
        startActivity(Pauto);
    }
}
