package com.example.in_help.ui;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.in_help.R;


public class F_IUGC1_Gestionar_Contactos extends Fragment {

    ImageButton registrados,registrar;
    View vista;


    public F_IUGC1_Gestionar_Contactos() {
        // Required empty public constructor

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_f__iugc1__gestionar__contactos, container, false);

        registrados = vista.findViewById(R.id.imageButtonContacosre);
        registrar = vista.findViewById(R.id.imageButtonContacosAgr);

        registrados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactosRegistrados();
            }
        });

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                REgistrarContacto();
            }
        });


        return vista;
    }


    public void ContactosRegistrados(){
        Intent GoFinalizarRegistro = new Intent(getContext(), IUGC1_1_Contactos_Registrados.class);
        startActivity(GoFinalizarRegistro);
    }
    public void REgistrarContacto(){
        Intent GoFinalizarRegistro = new Intent(getContext(), IUGC1_3_Registrar_Contacto.class);
        startActivity(GoFinalizarRegistro);
    }




}
