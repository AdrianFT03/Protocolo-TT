package com.example.in_help.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.in_help.R;

public class F_Notificaciones extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_notificaciones, container, false);

        Button btnContactos = (Button) view.findViewById(R.id.Btn_Contactos);
        btnContactos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getActivity(), Configurar_Notificaciones_Contactos.class);
                in.putExtra("some","some data");
                startActivity(in);
            }
        });
        return view;
    }


}
