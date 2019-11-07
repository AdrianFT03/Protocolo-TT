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

public class Adaptador_IUGC1 extends BaseAdapter {

    Context context;
    List<Datos_IUGC1> ListaObjetos;

    public Adaptador_IUGC1(Context context, List<Datos_IUGC1> listaObjetos) {
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
        return ListaObjetos.get(i).getId_contacto(); //retornando el Id de la posicion (id id viene de Datos_iugn)
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {

        View vista = view;
        LayoutInflater inflater = LayoutInflater.from(context);// variable de contexto . se crea el contexto
        view = inflater.inflate(R.layout.itemlist_iugn_iugc1_contactos_registrados,null); //inyecta en el listview

        ImageView imagen1 = (ImageView) view.findViewById(R.id.IUGC1LimageView);
        ImageButton imagen2 = (ImageButton) view.findViewById(R.id.IUGC1LimageButton1);
        ImageButton imagen3 = (ImageButton) view.findViewById(R.id.IUGC1LimageButton2);
        TextView textView = (TextView) view.findViewById(R.id.IUGC1LtextView);

        textView.setText(ListaObjetos.get(position).getTx_nombre().toString()); // se carega info
        imagen1.setImageResource(ListaObjetos.get(position).getImagen1());
        imagen2.setImageResource(ListaObjetos.get(position).getImagen2());
        imagen3.setImageResource(ListaObjetos.get(position).getImagen3());

        imagen2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context, "Id_vehiculo:"+getItemId(i), Toast.LENGTH_SHORT).show();

                Datos_IUGC1 objeto = (Datos_IUGC1) getItem(position);

                Intent next =new Intent(view.getContext(), IUGC1_2_Editar_Contacto.class);
                next.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                next.putExtra("DatosIUGC1",(Serializable)objeto);
                view.getContext().startActivity(next);
            }

        });


        return view;

    }
}
