package com.example.in_help.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.in_help.R;

public class IUGC1_2_Editar_Contacto extends AppCompatActivity {

    public Spinner spinnerParentezco;

    public Integer info=1;

    public RadioGroup radioGroup;
    public RadioButton radioButtonSiInfo;
    public RadioButton radioButtonNoInfo;
    public RadioButton radioButtonF;
    public RadioButton radioButtonA;
    public RadioButton radioButtonE;


    public TextView textViewtipo;
    public TextView textViewnombre;
    public TextView textViewprimerapp;
    public TextView textViewsegapp;
    public TextView textViewparentezco;
    public TextView textViewnumero;
    public TextView textViewmasinfotex;
    public TextView textViewnoPoliza;
    public TextView textViewfechavigenci;
    public TextView textViewdia;
    public TextView textViewmes;
    public TextView textViewanio;

    public EditText editTextNombre;
    public EditText editTextPrimerAp;
    public EditText editTextSegundAp;
    public EditText editTextNumero;
    public EditText editTextPoliza;
    public EditText editTextDia;
    public EditText editTextMes;
    public EditText editTextAnio;

    public ImageView imagen;

    public Button button;

    public String fecha;
    String sexo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iugc1_2__editar__contacto);

        Datos_IUGC1 objeto = (Datos_IUGC1) getIntent().getExtras().getSerializable("DatosIUGC1");

        spinnerParentezco = (Spinner) findViewById(R.id.IUGC12spinnerPare);

        radioButtonSiInfo = (RadioButton) findViewById(R.id.IUGC12radioButtonSí);
        radioButtonNoInfo = (RadioButton) findViewById(R.id.IUGC12radioButtonNo);


        imagen = (ImageView) findViewById(R.id.IUGC12imageView);

        textViewnombre = (TextView) findViewById(R.id.IUGC12textViewNombre);
        textViewprimerapp = (TextView) findViewById(R.id.IUGC12textViewPA);
        textViewsegapp = (TextView) findViewById(R.id.IUGC12textViewSA);
        textViewparentezco = (TextView) findViewById(R.id.IUGC12textViewPare);
        textViewnumero = (TextView) findViewById(R.id.IUGC12textViewNum);
        textViewmasinfotex = (TextView) findViewById(R.id.IUGC12textViewPreg);
        textViewnoPoliza = (TextView) findViewById(R.id.IUGC12textViewNoPoli);
        textViewfechavigenci = (TextView) findViewById(R.id.IUGC12textViewFech);
        textViewdia = (TextView) findViewById(R.id.IUGC12textViewDia);
        textViewmes = (TextView) findViewById(R.id.IUGC12textViewMes);
        textViewanio = (TextView) findViewById(R.id.IUGC12textViewAnio);

        editTextNombre = (EditText) findViewById(R.id.IUGC12editTextNombre);
        editTextPrimerAp = (EditText) findViewById(R.id.IUGC12editTextPA);
        editTextSegundAp = (EditText) findViewById(R.id.IUGC12editTextSA);
        editTextNumero = (EditText) findViewById(R.id.IUGC12editTextNum);
        editTextPoliza = (EditText) findViewById(R.id.IUGC12editTextNoPolo);
        editTextDia = (EditText) findViewById(R.id.IUGC12editTextDia);
        editTextMes = (EditText) findViewById(R.id.IUGC12editTextMes);
        editTextAnio = (EditText) findViewById(R.id.IUGC12editTextAnio);

        button = (Button) findViewById(R.id.IUGC12button);

        String [] parenezco = {"Hermano","Padre","Madre","Otro"};
        ArrayAdapter<String> adapterParentezco = new ArrayAdapter<String>(this, android.R.layout.simple_selectable_list_item, parenezco);
        spinnerParentezco.setAdapter(adapterParentezco);

        if(objeto.getId_tipo()==1){ // Familiar


            imagen.setImageResource(R.mipmap.group);
            radioButtonSiInfo.setVisibility(View.GONE) ;
            radioButtonSiInfo.isChecked();
            radioButtonNoInfo.setVisibility(View.GONE) ;

            textViewnombre.setVisibility(View.VISIBLE) ;
            textViewprimerapp.setVisibility(View.VISIBLE) ;
            textViewsegapp.setVisibility(View.VISIBLE) ;
            textViewparentezco.setVisibility(View.VISIBLE) ;
            textViewnumero.setVisibility(View.VISIBLE) ;
            textViewmasinfotex.setVisibility(View.GONE) ;
            textViewnoPoliza.setVisibility(View.GONE) ;
            textViewfechavigenci.setVisibility(View.GONE);
            textViewdia.setVisibility(View.GONE) ;
            textViewmes.setVisibility(View.GONE) ;
            textViewanio.setVisibility(View.GONE) ;

            editTextNombre.setVisibility(View.VISIBLE) ;
            editTextPrimerAp.setVisibility(View.VISIBLE) ;
            editTextSegundAp.setVisibility(View.VISIBLE) ;
            editTextNumero.setVisibility(View.VISIBLE) ;
            editTextPoliza.setVisibility(View.GONE) ;
            editTextDia.setVisibility(View.GONE) ;
            editTextMes.setVisibility(View.GONE) ;
            editTextAnio.setVisibility(View.GONE) ;

            spinnerParentezco.setVisibility(View.VISIBLE);

            button.setVisibility(View.VISIBLE);

            editTextNombre.setText(objeto.getTx_nombre());
            editTextPrimerAp.setText(objeto.getTx_primer_ap());
            editTextSegundAp.setText(objeto.getTx_seg_ap());
            editTextNumero.setText(objeto.getNu_tel());


        }else if (objeto.getId_tipo() ==2){ // Aseguradora

            imagen.setImageResource(R.mipmap.baseline_business_center_black_48);
            radioButtonSiInfo.setVisibility(View.VISIBLE) ;
            radioButtonSiInfo.isChecked();
            radioButtonNoInfo.setVisibility(View.VISIBLE) ;

            textViewnombre.setVisibility(View.VISIBLE) ;
            textViewprimerapp.setVisibility(View.GONE) ;
            textViewsegapp.setVisibility(View.GONE) ;
            textViewparentezco.setVisibility(View.GONE) ;
            textViewnumero.setVisibility(View.VISIBLE) ;
            textViewmasinfotex.setVisibility(View.VISIBLE) ;
            textViewnoPoliza.setVisibility(View.VISIBLE) ;
            textViewfechavigenci.setVisibility(View.VISIBLE);
            textViewdia.setVisibility(View.VISIBLE) ;
            textViewmes.setVisibility(View.VISIBLE) ;
            textViewanio.setVisibility(View.VISIBLE) ;

            editTextNombre.setVisibility(View.VISIBLE) ;
            editTextPrimerAp.setVisibility(View.GONE) ;
            editTextSegundAp.setVisibility(View.GONE) ;
            editTextNumero.setVisibility(View.VISIBLE) ;
            editTextPoliza.setVisibility(View.VISIBLE) ;
            editTextDia.setVisibility(View.VISIBLE) ;
            editTextMes.setVisibility(View.VISIBLE) ;
            editTextAnio.setVisibility(View.VISIBLE) ;

            spinnerParentezco.setVisibility(View.GONE);

            button.setVisibility(View.VISIBLE);

            editTextNombre.setText(objeto.getTx_nombre());
            editTextNumero.setText(objeto.getNu_tel());
            editTextPoliza.setText(objeto.getNu_poliza());
            editTextDia.setText(objeto.getFh_vigencia().substring(0,1));
            editTextMes.setText(objeto.getFh_vigencia().substring(3,4));
            editTextAnio.setText(objeto.getFh_vigencia().substring(6,9));

        }else if(objeto.getId_tipo() ==3){ //Emergencia

            imagen.setImageResource(R.mipmap.baseline_local_hospital_black_48);
            radioButtonSiInfo.setVisibility(View.GONE) ;
            radioButtonSiInfo.isChecked();
            radioButtonNoInfo.setVisibility(View.GONE) ;

            textViewnombre.setVisibility(View.VISIBLE) ;
            textViewprimerapp.setVisibility(View.GONE) ;
            textViewsegapp.setVisibility(View.GONE) ;
            textViewparentezco.setVisibility(View.GONE) ;
            textViewnumero.setVisibility(View.VISIBLE) ;
            textViewmasinfotex.setVisibility(View.GONE) ;
            textViewnoPoliza.setVisibility(View.GONE) ;
            textViewfechavigenci.setVisibility(View.GONE);
            textViewdia.setVisibility(View.GONE) ;
            textViewmes.setVisibility(View.GONE) ;
            textViewanio.setVisibility(View.GONE) ;

            editTextNombre.setVisibility(View.VISIBLE) ;
            editTextPrimerAp.setVisibility(View.GONE) ;
            editTextSegundAp.setVisibility(View.GONE) ;
            editTextNumero.setVisibility(View.VISIBLE) ;
            editTextPoliza.setVisibility(View.GONE) ;
            editTextDia.setVisibility(View.GONE) ;
            editTextMes.setVisibility(View.GONE) ;
            editTextAnio.setVisibility(View.GONE) ;

            spinnerParentezco.setVisibility(View.GONE);

            button.setVisibility(View.VISIBLE);

            editTextNombre.setText("");
            editTextPrimerAp.setText("");
            editTextSegundAp.setText("");
            editTextNumero.setText("");
            editTextPoliza.setText("");
            editTextDia.setText("");
            editTextMes.setText("");
            editTextAnio.setText("");

        }
    }
}
