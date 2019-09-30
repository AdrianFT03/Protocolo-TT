package com.example.in_help;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Switch_Config_Notificaciones extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch__config__notificaciones);


        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            if (bundle.getString("some") != null){
                Toast.makeText(getApplicationContext(), "Configuracion de Notificaciones",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
}
