package com.example.in_help.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.in_help.R;

import java.io.Serializable;
import java.util.List;

public class Prueba_Adaptador_IUGN5 extends BaseAdapter {


    Context context;
    List<Datos_IUGV1> ListaObjetos;

    public Prueba_Adaptador_IUGN5(Context context, List<Datos_IUGV1> listaObjetos) {
        this.context = context;
        ListaObjetos = listaObjetos;
    }

    @Override
    public int getCount() { // retornar la catidad de elementos de la lista
        return ListaObjetos.size();
    }

    @Override
    public Object getItem(int i) { //retornar el objeto de una posicion
        return ListaObjetos.get(i); // i es la posicion
    }

    @Override
    public long getItemId(int i) { //
        return ListaObjetos.get(i).getId(); //retornando el Id de la posicion (id id viene de Datos_iugn)
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) { // se ejecuta en cada secuencia en la que se quiera cargar el contenido de cada ito de listview

        View vista = view;
        LayoutInflater inflater = LayoutInflater.from(context);// variable de contexto . se crea el contexto
        view = inflater.inflate(R.layout.itemlistview_iugv1_1_vehiculos_registrados,null); //inyecta en el listview

        ImageView imagen1 = (ImageView) view.findViewById(R.id.IUGV1_imageView1);
        ImageButton imagen2 = (ImageButton) view.findViewById(R.id.IUGV1_imagebutton1);
        ImageButton imagen3 = (ImageButton) view.findViewById(R.id.IUGV1_imagebutton2);
        TextView textView = (TextView) view.findViewById(R.id.IUGV1_textView);

        textView.setText(ListaObjetos.get(i).getPlacas().toString()); // se carega info
        imagen1.setImageResource(ListaObjetos.get(i).getImagen1());
        imagen2.setImageResource(ListaObjetos.get(i).getImagen2());
        imagen3.setImageResource(ListaObjetos.get(i).getImagen3());

        imagen2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context, "Id_vehiculo:"+getItemId(i), Toast.LENGTH_SHORT).show();

                Datos_IUGV1 objeto = (Datos_IUGV1) getItem(i);

                Intent next =new Intent(view.getContext(), IUGV1_2_Registrar_Vehiculo.class);
                next.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                next.putExtra("DatosAnt",(Serializable)objeto);
                view.getContext().startActivity(next);
            }

        });

        return view;
    }





}


//https://www.youtube.com/watch?v=90G20Z1n7ag  --> de aqui lo saque
