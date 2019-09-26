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

public class Reg_Contacto_Pers extends AppCompatActivity {
    public EditText Fam_Nom, Fam_Parent , Fam_NumTel;
    public Button btn_GuarFam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg__contacto__pers);

        Fam_Nom = (EditText)findViewById(R.id.P_Nombre);
        Fam_NumTel = (EditText)findViewById(R.id.P_num);
        Fam_Parent = (EditText)findViewById(R.id.P_Parentezco);

        btn_GuarFam.setOnClickListener(new View.OnClickListener() {
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

    //Metodo Para ir a  Registrar Familiar
    public void Reg_Per(View view){
        Intent Re_Per = new Intent(this,Reg_Contacto_Pers.class);
        startActivity(Re_Per);
    }

    //Metodo para Ejecutar WS
    private void ejecutarServicio(String URL) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Se ha Guardado el contacto", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametro = new HashMap<String, String>();
                parametro.put("tx_nombre", Fam_Nom.getText().toString());
                parametro.put("nu_tel", Fam_NumTel.getText().toString());
                parametro.put("Tip_Parent", Fam_Parent.getText().toString());


                return parametro;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
