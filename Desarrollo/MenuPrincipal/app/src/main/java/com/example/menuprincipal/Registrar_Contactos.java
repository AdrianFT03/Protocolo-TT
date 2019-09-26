package com.example.menuprincipal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class Registrar_Contactos extends AppCompatActivity {


    private EditText Em_Nom , Em_Num;
    private Button Em_Guar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar__contactos);

        Em_Nom = (EditText)findViewById(R.id.E_Nombre);
        Em_Num = (EditText)findViewById(R.id.E_num);
        Em_Guar = (Button)findViewById(R.id.E_guardar);

        Em_Guar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ejecutarServicio("http://10.40.4.231:8888/InHelp/Insertar_Contactos.php");
            }
        });
    }
    //Metodo Para ir Registar Contacto de Emergencia

    public void Reg_Eme(View view){
        Intent Re_Eme = new Intent(this,Registrar_Contactos.class);
        startActivity(Re_Eme);
    }

    //Metodo Para ir Registrar Aseguradora

    public void Reg_Aseg(View view){
        Intent Re_Aseg = new Intent(this,Reg_Contacto_Aseg.class);
        startActivity(Re_Aseg);
    }

    public void Reg_Per(View view){
        Intent Re_Per = new Intent(this,Reg_Contacto_Pers.class);
        startActivity(Re_Per);
    }


    //Metodo para Ejecutar WS
    private void ejecutarServicio(String URL){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Se ha Guardado el contacto", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametro=new HashMap<String, String>();
                parametro.put("tx_nombre",Em_Nom.getText().toString());
                parametro.put("nu_tel",Em_Num.getText().toString());




                return parametro;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
