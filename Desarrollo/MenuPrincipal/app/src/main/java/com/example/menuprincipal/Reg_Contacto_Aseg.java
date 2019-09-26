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

import java.util.HashMap;
import java.util.Map;

public class Reg_Contacto_Aseg extends AppCompatActivity {
    public EditText Seg_Nom , Seg_Pol , Seg_Num , Seg_FechVig;
    public Button btn_GuarSeg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg__contacto__aseg);

        Seg_Nom = (EditText)findViewById(R.id.A_Nombre);
        Seg_Num = (EditText)findViewById(R.id.A_num);
        Seg_Pol = (EditText)findViewById(R.id.A_No_Pol);
        Seg_FechVig = (EditText)findViewById(R.id.A_Fech_Vig);
        btn_GuarSeg = (Button)findViewById(R.id.A_guardar);

        btn_GuarSeg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ejecutarServicio("http://10.40.4.231:8888/InHelp/Insertar_Contactos.php");
            }
        });
    }

    //Metodo Para ir Registrar Contacto de emergencia

    public void Reg_Eme(View view){
        Intent Re_Eme = new Intent(this,Registrar_Contactos.class);
        startActivity(Re_Eme);
    }
    //Metodo Para ir Registrar Aseguradora

    public void Reg_Aseg(View view){
        Intent Re_Aseg = new Intent(this,Reg_Contacto_Aseg.class);
        startActivity(Re_Aseg);
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
                parametro.put("tx_nombre",Seg_Nom.getText().toString());
                parametro.put("nu_tel",Seg_Num.getText().toString());
                parametro.put("nu_Pol",Seg_Pol.getText().toString());
                parametro.put("Fech_Vig_Pol",Seg_FechVig.getText().toString());




                return parametro;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
