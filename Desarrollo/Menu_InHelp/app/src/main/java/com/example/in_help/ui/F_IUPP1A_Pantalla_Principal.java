package com.example.in_help.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.in_help.R;


    public class F_IUPP1A_Pantalla_Principal extends Fragment {


    Spinner spinnerPalcas;
    View vista;



    public F_IUPP1A_Pantalla_Principal() {
        // Required empty public constructor

    }







    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        
        // Inflate the layout for this fragment

        vista = inflater.inflate(R.layout.fragment_f__iupp1_a__pantalla__principal, container, false);

        spinnerPalcas = vista.findViewById(R.id.spinnerplacas00);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.Placas,android.R.layout.simple_spinner_dropdown_item);
        spinnerPalcas.setAdapter(adapter);

        return vista;

    }




}
