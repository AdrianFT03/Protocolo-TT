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

public class Adaptador_IUGN1 extends BaseAdapter {

    Context context;
    List<Datos_IUGN1> ListaObjetos;

    public Adaptador_IUGN1(Context context, List<Datos_IUGN1> listaObjetos) {
        this.context = context;
        ListaObjetos = listaObjetos;
    }

    @Override
    public int getCount() {
        return ListaObjetos.size();
    }

    @Override
    public Object getItem(int i) {
        return ListaObjetos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return ListaObjetos.get(i).getId();
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        View vista = view;
        LayoutInflater inflater = LayoutInflater.from(context);// variable de contexto . se crea el contexto
        view = inflater.inflate(R.layout.itemlistview_iugn1_gestion_de_notificaciones,null); //inyecta en el listview

        ImageView imagen1 = (ImageView) view.findViewById(R.id.IUGN1LimageView);
        ImageButton imagen2 = (ImageButton) view.findViewById(R.id.IUGN1LimageButton);
        TextView textView1 = (TextView) view.findViewById(R.id.IUGN1LtextView1);
        TextView textView2 = (TextView) view.findViewById(R.id.IUGN1LtextView2);

        textView1.setText(ListaObjetos.get(i).getPlacas().toString());
        textView2.setText(ListaObjetos.get(i).getFh_notificacion().toString());
        imagen1.setImageResource(ListaObjetos.get(i).getImagen1());
        imagen2.setImageResource(ListaObjetos.get(i).getImagen2());

        imagen2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Datos_IUGN1 objeto = (Datos_IUGN1) getItem(i);

                Intent next = new Intent(view.getContext(), IUGN2_Informacion_de_Notificacion.class);
                next.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                next.putExtra("DatosIUGN1",(Serializable)objeto);
                view.getContext().startActivity(next);
            }
        });

        return view;
    }
}
