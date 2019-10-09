package com.example.in_help.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.in_help.R;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class Adaptador_IUGN6 extends BaseAdapter {

    Context context;
    List<Datos_IUNG6> ListaObjetos;

    public Adaptador_IUGN6(Context context, List<Datos_IUNG6> listaObjetos){
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

        View vista;
        LayoutInflater inflater = LayoutInflater.from(context);

        view = inflater.inflate(R.layout.itemlistview_iugn6_configurar_notificaciones,null);

        final Switch activardesactivar = (Switch) view.findViewById(R.id.IUGN6LSwitch);
        ImageView imagentipo = (ImageView) view.findViewById(R.id.IUGN6LimageView);
        TextView nombre = (TextView) view.findViewById(R.id.IUGN6LtextView1);
        ImageButton gestionar = (ImageButton) view.findViewById(R.id.IUGN6LimageButton);

        activardesactivar.setChecked(ListaObjetos.get(i).getId_switch());
        imagentipo.setImageResource(ListaObjetos.get(i).getId_imagen1());
        nombre.setText(ListaObjetos.get(i).getNombre().toString());
        gestionar.setImageResource(ListaObjetos.get(i).getId_imagen2());

        gestionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context, "Id_vehiculo:"+getItemId(i), Toast.LENGTH_SHORT).show();

                Datos_IUNG6 objeto = (Datos_IUNG6) getItem(i);

                Intent next =new Intent(view.getContext(), IUGN6A_Configurar_Notificacion.class);
                next.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                next.putExtra("DatosIUGN6",(Serializable)objeto);
                view.getContext().startActivity(next);
            }
        });
        activardesactivar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Datos_IUNG6 objeto = (Datos_IUNG6) getItem(i);
                if(activardesactivar.isChecked()  == false){
                    Actualiza(2,objeto.getId());
                    //Toast.makeText(context, "Acualiza a 2", Toast.LENGTH_SHORT).show();
                }
                else if(activardesactivar.isChecked() == true){
                    Actualiza(1,objeto.getId());
                    //Toast.makeText(context, "Acualiza a 1", Toast.LENGTH_SHORT).show();
                }
            }
        });



        return view;
    }

    public void Actualiza(Integer id_estado, Integer id_confi){
        APIServer service = Cliente.getAPIServer();

        DatosActualiza_IUGN6 actualiza = new DatosActualiza_IUGN6();

        Call<Response> call = (Call<Response>) service.UpdateStatusEnvio(id_estado,id_confi);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                //Toast.makeText(context, "Actualizado", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Toast.makeText(context, "Ocurrio Algo... Intenta de nuevo", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
