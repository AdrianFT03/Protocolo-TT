package com.example.in_help.ui;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.in_help.R;


    public class F_IUPP1A_Pantalla_Principal extends Fragment {


<<<<<<< HEAD
=======
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

>>>>>>> 05c38bf140e58ee858c3177835aab57cbd1ba4d3
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

        spinnerPalcas = vista.findViewById(R.id.spinnerplacas);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.Placas,android.R.layout.simple_spinner_dropdown_item);
        spinnerPalcas.setAdapter(adapter);


        return vista;

    }



<<<<<<< HEAD

=======




    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
>>>>>>> 05c38bf140e58ee858c3177835aab57cbd1ba4d3
}
