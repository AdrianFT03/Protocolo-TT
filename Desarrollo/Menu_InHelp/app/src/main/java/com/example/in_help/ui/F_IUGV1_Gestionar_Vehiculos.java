package com.example.in_help.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.in_help.R;


public class F_IUGV1_Gestionar_Vehiculos extends Fragment {


    ImageButton  registro, registrados;
    View vista;



    public F_IUGV1_Gestionar_Vehiculos() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        vista = inflater.inflate(R.layout.fragment_f__iugv1__gestionar__vehiculos, container, false);

        registro = vista.findViewById(R.id.imageButtonAddVehiculo);

        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoRegistroVehiculo(v);
            }
        });

        registrados = vista.findViewById(R.id.imageButtonVehiculosReg);

        registrados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoVehiculosRegistrados(v);
            }
        });

        return vista;
    }





   

    public void GoRegistroVehiculo(View view){
        Intent GoRegistroVehiculo = new Intent(getContext(), IUGV1_2_Registrar_Vehiculo.class);
        startActivity(GoRegistroVehiculo);
    }

    public void GoVehiculosRegistrados(View view){
        Intent GoVehiculosRegistrados = new Intent(getContext(), IUGCV1_1_Vehiculos_Registrados.class);
        startActivity(GoVehiculosRegistrados);
    }


}
