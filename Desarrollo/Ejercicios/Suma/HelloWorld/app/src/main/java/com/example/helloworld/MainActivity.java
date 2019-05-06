package com.example.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText et1;
    private EditText et2;
    private TextView tv1;


    @Override
    protected void onCreate(Bundle savedInstanceState) { // crear activity cuando se inicia la app
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "OnCreate", Toast.LENGTH_SHORT).show();
        // La actividad está creada

        et1 = (EditText)findViewById(R.id.id_num1);
        et2 = (EditText)findViewById(R.id.id_num2);
        tv1 = (TextView)findViewById(R.id.txt_resultado);

    }

    public void Suma(View view){

        String v1 = et1.getText().toString();
        String v2 = et2.getText().toString();

        int num1 = Integer.parseInt(v1);
        int num2 = Integer.parseInt(v2);

        int suma = num1 + num2;

        String resu = String.valueOf(suma);

        tv1.setText(resu);

    }


}
