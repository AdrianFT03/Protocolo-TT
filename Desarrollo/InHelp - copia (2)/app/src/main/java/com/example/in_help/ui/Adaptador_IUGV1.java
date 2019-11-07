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

public class Adaptador_IUGV1 extends BaseAdapter {

    public Adaptador_IUGV1(Context contexto, List<Datos_IUGV1> listaVehiculos) {
        this.contexto = contexto;
        ListaVehiculos = listaVehiculos;
    }

    Context contexto;
    List<Datos_IUGV1> ListaVehiculos;

    @Override
    public int getCount() {
        return ListaVehiculos.size();// Retorna cantidad de elementos de la lista
    }

    @Override
    public Object getItem(int i) {
        return ListaVehiculos.get(i); // Retorna la posicion de la lista indicada
    }

    @Override
    public long getItemId(int i) {
        return ListaVehiculos.get(i).getId(); // Retorna el Id de la posicion que esta indicando
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) { // se ejecuta en cada secuencia en la que se quiera cargar el contenido de cada ito de listview

        View vista = view;
        LayoutInflater inflater = LayoutInflater.from(contexto);// variable de contexto . se crea el contexto
        view = inflater.inflate(R.layout.itemlistview_iugv1_1_vehiculos_registrados, null); //inyecta en el listview

        ImageView imagen = (ImageView) view.findViewById(R.id.IUGV1_imageView1);

        TextView textView = (TextView) view.findViewById(R.id.IUGV1_textView);
        ImageButton imagen2 = (ImageButton) view.findViewById(R.id.IUGV1_imagebutton1);

        textView.setText(ListaVehiculos.get(i).getPlacas().toString()); // se carega info
        imagen.setImageResource(ListaVehiculos.get(i).getImagen2());

        imagen2.setImageResource(ListaVehiculos.get(i).getImagen3());

        imagen2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context, "Id_vehiculo:"+getItemId(i), Toast.LENGTH_SHORT).show();

                Datos_IUGN5 objeto = (Datos_IUGN5) getItem(i);

                Intent next = new Intent(view.getContext(), IUGN6_Configurar_Notificaciones.class);
                next.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                next.putExtra("DatosAnt", (Serializable) objeto);
                view.getContext().startActivity(next);
            }

        });

        return view;
    }
}
