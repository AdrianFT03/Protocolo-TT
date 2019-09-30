package com.example.in_help.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.in_help.Configurar_Notificaciones_Contactos;
import com.example.in_help.R;
import com.example.in_help.Switch_Config_Notificaciones;


public class Config_not_Fragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_configurar_notificaciones, container, false);

        ImageButton btnConfigNot1 = (ImageButton) view.findViewById(R.id.Config_Not_1);
        btnConfigNot1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getActivity(), Switch_Config_Notificaciones.class);
                in.putExtra("some","some data");
                startActivity(in);
            }
        });
        ImageButton btnConfigNot2 = (ImageButton) view.findViewById(R.id.Config_Not_2);
        btnConfigNot2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getActivity(), Switch_Config_Notificaciones.class);
                in.putExtra("some","some data");
                startActivity(in);
            }
        });

        ImageButton btnConfigNot3 = (ImageButton) view.findViewById(R.id.config_Not_3);
        btnConfigNot3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getActivity(), Switch_Config_Notificaciones.class);
                in.putExtra("some","some data");
                startActivity(in);
            }
        });

        ImageButton btnConfigNot4 = (ImageButton) view.findViewById(R.id.config_Not_4);
        btnConfigNot4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getActivity(), Switch_Config_Notificaciones.class);
                in.putExtra("some","some data");
                startActivity(in);
            }
        });
        return view;
    }
}