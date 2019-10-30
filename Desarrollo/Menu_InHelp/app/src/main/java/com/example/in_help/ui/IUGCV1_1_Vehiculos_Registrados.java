package com.example.in_help.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.in_help.R;

public class IUGCV1_1_Vehiculos_Registrados extends AppCompatActivity {

    ImageView imageEditar, imageVer, imageElimina;
    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iugcv1_1__vehiculos__registrados);
        setupActionBar();

        imageEditar = (ImageView)findViewById(R.id.buttonEditarVehi);
        imageVer = (ImageView)findViewById(R.id.imageView6);
        imageElimina = (ImageView)findViewById(R.id.imageView12);

        imageEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoEditar(v);
            }
        });

        final AlertDialog.Builder InfoVehiculo = new AlertDialog.Builder(this);
        InfoVehiculo.setTitle("Información");
        InfoVehiculo.setMessage("Corsa 329-YFA");
        InfoVehiculo.setCancelable(false);


        final AlertDialog.Builder EliminarVehiculo = new AlertDialog.Builder(this);
        EliminarVehiculo.setMessage("¿Quieres eliminarlo?");
        EliminarVehiculo.setCancelable(false);

        EliminarVehiculo.setPositiveButton("Si quiero", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                borrar();
            }
        });
        EliminarVehiculo.setNegativeButton("No quiero", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cancelar();
            }
        });



        InfoVehiculo.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cancelar();
            }
        });

        imageVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InfoVehiculo.show();
            }
        });

        imageElimina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EliminarVehiculo.show();
            }
        });



    }

    private void setupActionBar(){
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null)
        {
            //actionBar.setDisplayHomeAsUpEnabled(true);

            actionBar.setTitle("");
        }
    }

    public void GoEditar(View view){
        Intent GoEditar = new Intent(this, IUGV1_3_Editar_Vehiculo.class);
        startActivity(GoEditar);
    }

    public void cancelar(){
        finish();
    }

    public void borrar(){
        finish();
        Toast t = Toast.makeText(this,"Vehículo Eliminado", Toast.LENGTH_SHORT);
        t.show();
    }


}
