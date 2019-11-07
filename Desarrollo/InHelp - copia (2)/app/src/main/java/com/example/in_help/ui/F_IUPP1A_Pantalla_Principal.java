package com.example.in_help.ui;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.in_help.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.LOCATION_SERVICE;
import static android.content.Context.MODE_PRIVATE;
import static android.content.Context.SENSOR_SERVICE;
import static android.support.constraint.Constraints.TAG;


import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

    public class F_IUPP1A_Pantalla_Principal extends Fragment implements OnMapReadyCallback, SensorEventListener {


    ArrayList<DatosContactoNoti_F_IUPP1A> Lista;
    ArrayList<DatosConPermiso_F_IUPP1A> Lista1;
    ArrayList<Datos_F_IUPPA> Lista2;
    String mensaje;
    Spinner spinnerPalcas;
    View vista;

    //private F_IUGC1_Gestionar_Contactos.OnFragmentInteractionListener mListener;
    public String content;
    public Integer id_bitacora;
    public Integer id_vehiculo;
    public Double latitud;
    public Double longitud;

    private GoogleMap mMap;
    private LocationManager locationManager;
    private Location locationAnterior;
    private double km;
    private SupportMapFragment mapFragment;

    Integer id_sexo;
    Integer id_tipo_sangre;
    String tx_id;
    String tx_nombre;
    String tx_primer_ap;
    String tx_segundo_ap;
    String fh_nacimiento;
    String tx_enfermedad;


    public TextView x,y,z;
    public Sensor mAccelerometer;
    public Sensor sensor,giroscopio;
    public SensorManager sm,sm1;
    private boolean flag;
    private int tempo;


    public F_IUPP1A_Pantalla_Principal() {
        // Required empty public constructor

    }








    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {





        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_f__iupp1_a__pantalla__principal, container, false);

        spinnerPalcas = vista.findViewById(R.id.spinnerplacas);
        ImageButton Alerta = (ImageButton) vista.findViewById(R.id.imageButtonAlerta);

        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapViewFPP);
        mapFragment.getMapAsync(this);
        flag = false;

        llenarPlacas(1);


        Activity a = getActivity();
        if(a != null) a.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ObtenerInfoPersona(1);


        spinnerPalcas.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id)
            {
                if(spinnerPalcas.getSelectedItem().toString().equals("Selecciona")){

                }else {
                    ObtenerVehiculo(spinnerPalcas.getSelectedItem().toString(),1);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {    }
        });





        Alerta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(), "Alerta", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder alerta = new AlertDialog.Builder(getContext());
                alerta.setMessage("Se enviará una alerta a tus contactos")
                        .setCancelable(false)
                        .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                checkSMSStatePermission();



                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
                                String fecha = sdf.format(new Date());


                                Log.d(TAG, "id_bitacora: "+id_bitacora);
                                Log.d(TAG, "id_vehiculo: "+id_vehiculo);
                                Log.d(TAG, "fehca y hora: "+fecha);
                                Log.d(TAG, "latitud" +latitud);
                                Log.d(TAG, "latitud" +longitud);

                                CrearNotificacion(1,id_vehiculo,id_bitacora,fecha,latitud,longitud,1);

                                ObtenerNumeros(1,id_vehiculo);
                                Toast.makeText(getContext(), "Alerta enviada", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                if(spinnerPalcas.getSelectedItem().toString().equals("Selecciona")){
                    Toast.makeText(getContext(), "Selecciona un Vehículo", Toast.LENGTH_SHORT).show();
                    flag = false;
                }else {
                    AlertDialog titulo = alerta.create();
                    titulo.setTitle("Enviar Notificación de Choque");
                    titulo.show();
                    ObtenerBitacora(1);
                }



            }
        });


        return vista;

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        locationManager = (LocationManager) getContext().getSystemService(LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(false);



        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 0, new LocationListener() {
            @Override
            public void onLocationChanged(final Location location1) {

                latitud = location1.getLatitude();
                longitud = location1.getLongitude();



                if (locationAnterior == null){
                    locationAnterior = location1;
                }else {
                    km = ((locationAnterior.distanceTo(location1))*1)/1000;
                    km = (km/0.0005555556);
                    //Toast.makeText(getContext(), km + " Km/h", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onLocationChanged: " + km +"Km/h");

                    //AdminSQLiteOpenHelper adminSQLiteOpenHelper = new AdminSQLiteOpenHelper(getApplicationContext(),"velocidad",null,1);
                    //SQLiteDatabase db = adminSQLiteOpenHelper.getWritableDatabase();

                    //ContentValues registro = new ContentValues();

                    //registro.put("bitacora",id_bitacora);
                    //Date currentTime = Calendar.getInstance().getTime();
                    //registro.put("fecha", String.valueOf(currentTime));
                    //registro.put("velocidad",km);


                    //db.insert("Velocidad",null,registro);


                    //db.close();

                }
                locationAnterior = location1;
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }

        });

    }

    public void ObtenerInfoPersona(Integer id_persona){
        APIServer server =  Cliente.getAPIServer();
        Call<List<DatosPersona_F_IUPP1A>> call = server.ObtenerinfoPersonaPP(id_persona);

        call.enqueue(new Callback<List<DatosPersona_F_IUPP1A>>() {
            @Override
            public void onResponse(Call<List<DatosPersona_F_IUPP1A>> call, Response<List<DatosPersona_F_IUPP1A>> response) {
                 id_sexo=4;
                 id_tipo_sangre=9;
                 tx_id="Indefinido";
                 tx_nombre= "Indefinido";
                 tx_primer_ap="Indefinido";
                 tx_segundo_ap="Indefinido";
                 fh_nacimiento="Indefinido";
                 tx_enfermedad= "Indefinido";

                 List<DatosPersona_F_IUPP1A> listPost = response.body();
                 listPost.size();
                 for(DatosPersona_F_IUPP1A datosPersona : listPost){
                     if(datosPersona.getId_sexo()!=null){
                         id_sexo = datosPersona.getId_sexo();
                     }
                     if(datosPersona.getId_sexo()!=null){
                         id_tipo_sangre = datosPersona.getId_tipo_sangre();
                     }
                     if(datosPersona.getTx_id()!= null){
                         tx_id = datosPersona.getTx_id();
                     }
                     if(datosPersona.getTx_nombre()!= null){
                         tx_nombre = datosPersona.getTx_nombre();

                     }
                     if(datosPersona.getTx_primer_ap()!= null){
                         tx_primer_ap = datosPersona.getTx_primer_ap();

                     }
                     if(datosPersona.getTx_segundo_ap()!= null){
                         tx_segundo_ap = datosPersona.getTx_segundo_ap();

                     }
                     if(datosPersona.getFh_nacimiento()!= null){
                         fh_nacimiento = datosPersona.getFh_nacimiento().substring(0,10);

                     }
                     if(datosPersona.getTx_nombreE()!= null){
                         tx_enfermedad = datosPersona.getTx_nombreE();
                     }
                 }

            }

            @Override
            public void onFailure(Call<List<DatosPersona_F_IUPP1A>> call, Throwable t) {
                Log.d(TAG, "onFailure: No obtuvo datos persona");
            }
        });

    }

    private void CrearBitacora(Integer id_usuario, Integer id_vehiculo){
        APIServer service = Cliente.getAPIServer();

        DatosBitacora_IUPP1A bitacora_iupp1A = new DatosBitacora_IUPP1A(id_usuario,id_vehiculo);

        Call<com.example.in_help.ui.Response> call = (Call<com.example.in_help.ui.Response>) service.crearBitacora(bitacora_iupp1A);

        call.enqueue(new Callback<com.example.in_help.ui.Response>() {
            @Override
            public void onResponse(Call<com.example.in_help.ui.Response> call, retrofit2.Response<com.example.in_help.ui.Response> response) {
                Log.d(TAG, "Bitacora creada "+call);
            }

            @Override
            public void onFailure(Call<com.example.in_help.ui.Response> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });
    }

    private  void ObtenerVehiculo(String placas, Integer id_usuario){

        APIServer servicio = Cliente.getAPIServer();
        Call<List<DatosIdVehiculo_F_IUPPA>> call = servicio.ObtenerIdVehiculo(id_usuario,placas);

        call.enqueue(new Callback<List<DatosIdVehiculo_F_IUPPA>>() {
            @Override
            public void onResponse(Call<List<DatosIdVehiculo_F_IUPPA>> call, Response<List<DatosIdVehiculo_F_IUPPA>> response) {
                Log.d(TAG, "onResponse: ");
                id_vehiculo =  null;
                List<DatosIdVehiculo_F_IUPPA> listPost = response.body();
                listPost.size();
                for(DatosIdVehiculo_F_IUPPA datosIdVehiculo_f_iuppa: listPost){
                    id_vehiculo = datosIdVehiculo_f_iuppa.getId_vehiculo();
                }
                id_vehiculo = id_vehiculo;
                CrearBitacora(1,id_vehiculo);
                ObtenerInfoVehiculo(id_vehiculo);
            }

            @Override
            public void onFailure(Call<List<DatosIdVehiculo_F_IUPPA>> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });

    }

    private void ObtenerInfoVehiculo(Integer id_vehiculo) {


    }

    private void ObtenerBitacora(Integer usuario) {
        APIServer service = Cliente.getAPIServer();

        final DatosBitacora_IUPP1A1 bitacora_iupp1Au = new DatosBitacora_IUPP1A1();

        Call<List<DatosBitacora_IUPP1A1>> call = service.ObtenerBitacoraU(usuario);

        call.enqueue(new Callback<List<DatosBitacora_IUPP1A1>>() {
            @Override
            public void onResponse(Call<List<DatosBitacora_IUPP1A1>> call, retrofit2.Response<List<DatosBitacora_IUPP1A1>> response) {

                Log.d(TAG, "onResponse: ");
                content =  "";
                List<DatosBitacora_IUPP1A1> listPost = response.body();
                listPost.size();
                for(DatosBitacora_IUPP1A1 BitacoraRequest: listPost){

                    content = BitacoraRequest.getId_bitacora().toString();

                }

                id_bitacora = Integer.parseInt(content);


            }

            @Override
            public void onFailure(Call<List<DatosBitacora_IUPP1A1>> call, Throwable t) {
                Log.d(TAG, "onFailure: ");

            }
        });

    }

    public void CrearNotificacion(Integer id_usuario, Integer id_vehiculo, Integer id_bitacora,String fh_notificacion, Double latitud,Double longitud,Integer id_tipo) {

        APIServer service = Cliente.getAPIServer();

        DatosNotifica_F_IUPPA notificacion = new DatosNotifica_F_IUPPA(id_usuario,id_tipo,id_vehiculo,id_bitacora,fh_notificacion,latitud,longitud);


        Call<com.example.in_help.ui.Response> call = (Call<com.example.in_help.ui.Response>) service.crearNotificacion(notificacion);

        call.enqueue(new Callback<com.example.in_help.ui.Response>() {
            @Override
            public void onResponse(Call<com.example.in_help.ui.Response> call, Response<com.example.in_help.ui.Response> response) {
                Log.d(TAG, "Notificación creada con exito"+response.body().getSuccesfull());
            }

            @Override
            public void onFailure(Call<com.example.in_help.ui.Response> call, Throwable t) {
                Log.d(TAG, "Notificación NO creada con exito"+t.toString());
            }
        });


    }

    public void llenarPlacas(Integer id_usuario){
        APIServer service = Cliente.getAPIServer();

        DatosVehiculo_F_IUPPA datosVehiculo_f_iuppa = new DatosVehiculo_F_IUPPA();
        Call<List<DatosVehiculo_F_IUPPA>> respuesta = service.ObtenerVehiculosPP(id_usuario);

        respuesta.enqueue(new Callback<List<DatosVehiculo_F_IUPPA>>() {
            @Override
            public void onResponse(Call<List<DatosVehiculo_F_IUPPA>> call, Response<List<DatosVehiculo_F_IUPPA>> response) {
                Lista2 = new ArrayList<Datos_F_IUPPA>();
                List<DatosVehiculo_F_IUPPA> listaDatosPlacas = response.body();
                listaDatosPlacas.size();

                Lista2.add(new Datos_F_IUPPA("Selecciona"));
                for (DatosVehiculo_F_IUPPA datosBod : listaDatosPlacas){
                    Lista2.add(new Datos_F_IUPPA(datosBod.getNu_placas()));
                }

                ArrayAdapter<Datos_F_IUPPA> adapter = new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item,Lista2);

                spinnerPalcas.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<List<DatosVehiculo_F_IUPPA>> call, Throwable t) {

            }
        });
    }

    private void ObtenerNumeros(Integer id_usuario, Integer id_vehiculo){
        APIServer server = Cliente.getAPIServer();

        DatosContactoNoti_F_IUPP1A datosNotificacion_iugn1 = new DatosContactoNoti_F_IUPP1A();

        Call<List<DatosContactoNoti_F_IUPP1A>> respuesta = server.ObtenerNumerosContactos(id_usuario,id_vehiculo);

        respuesta.enqueue(new Callback<List<DatosContactoNoti_F_IUPP1A>>() {
            @Override
            public void onResponse(Call<List<DatosContactoNoti_F_IUPP1A>> call, Response<List<DatosContactoNoti_F_IUPP1A>> response) {
                Lista = new ArrayList<DatosContactoNoti_F_IUPP1A>();
                List<DatosContactoNoti_F_IUPP1A> listaDatosNumeros = response.body();
                listaDatosNumeros.size();


                for (DatosContactoNoti_F_IUPP1A datosBod : listaDatosNumeros){
                        Log.d(TAG, " Telefono" +listaDatosNumeros+" "+ datosBod.getNu_tel());
                        ObtenerPermisos(datosBod.getNu_tel(),datosBod.getId_configuracion());
                        }




            }

            @Override
            public void onFailure(Call<List<DatosContactoNoti_F_IUPP1A>> call, Throwable t) {
                Toast.makeText(getContext(), "Erroe al obtener contactos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void ObtenerPermisos(final String numero, Integer id_configuracion){
        APIServer server = Cliente.getAPIServer();
        DatosConPermiso_F_IUPP1A datosConPermiso_f_iupp1A = new DatosConPermiso_F_IUPP1A();

        Call<List<DatosConPermiso_F_IUPP1A>> respuesta = server.ObtenerPermiso(id_configuracion);

        respuesta.enqueue(new Callback<List<DatosConPermiso_F_IUPP1A>>() {
            @Override
            public void onResponse(Call<List<DatosConPermiso_F_IUPP1A>> call, Response<List<DatosConPermiso_F_IUPP1A>> response) {
                Lista1 = new ArrayList<DatosConPermiso_F_IUPP1A>();
                List<DatosConPermiso_F_IUPP1A> listPermisos = response.body();
                listPermisos.size();

                mensaje = "¡Ayuda! Acabo de tener un accidente automovilístico";
                enviarMensajes(numero,mensaje);
                for (DatosConPermiso_F_IUPP1A datos : listPermisos){

                    if(datos.getId_permiso() == 1 && datos.getId_estado()==1) {
                        mensaje = "";
                        mensaje +="Información coche:";
                        mensaje += "\n";
                        mensaje += "No. Placas:";
                        mensaje += spinnerPalcas.getSelectedItem().toString();
                        enviarMensajes(numero,mensaje);

                    }else if(datos.getId_permiso() == 1 && datos.getId_estado()!=1){
                        mensaje = "";
                        mensaje +="Información coche:";
                        mensaje +="\n";
                        mensaje += "No compartida";
                        enviarMensajes(numero,mensaje);

                    }

                    if(datos.getId_permiso() == 2 && datos.getId_estado()==1) {
                        mensaje = "";
                        mensaje +="Información personal:";
                        mensaje +="\n";
                        mensaje += "Nombre: ";
                        mensaje += tx_nombre;
                        mensaje +="\n";
                        mensaje += "PA:";
                        mensaje += tx_primer_ap;
                        mensaje +="\n";
                        mensaje += "SA:";
                        mensaje += tx_segundo_ap;
                        enviarMensajes(numero,mensaje);

                        mensaje ="";
                        mensaje += "Fecha Nacimiento: ";
                        mensaje += fh_nacimiento;
                        enviarMensajes(numero,mensaje);

                    }else if(datos.getId_permiso() == 2 && datos.getId_estado()!=1){
                        mensaje = "";
                        mensaje +="Información personal:";
                        mensaje +="\n";
                        mensaje += "No compartida";
                        enviarMensajes(numero,mensaje);
                    }

                    if(datos.getId_permiso() == 3 && datos.getId_estado()==1) {
                        String tipo="";
                        if(id_tipo_sangre.equals(1)){
                            tipo = "A+";
                        }else if(id_tipo_sangre.equals(1)){
                            tipo = "O+";
                        }else if(id_tipo_sangre.equals(2)){
                            tipo = "B+";
                        }else if(id_tipo_sangre.equals(3)){
                            tipo = "AB+";
                        }else if(id_tipo_sangre.equals(4)){
                            tipo = "A-";
                        }else if(id_tipo_sangre.equals(5)){
                            tipo = "O-";
                        }else if(id_tipo_sangre.equals(6)){
                            tipo = "A+";
                        }else if(id_tipo_sangre.equals(7)){
                            tipo = "B-";
                        }else if(id_tipo_sangre.equals(8)){
                            tipo = "AB-";
                        }else if(id_tipo_sangre.equals(9)){
                            tipo = "Indefinido";
                        }
                        mensaje = "";

                        mensaje += "Tipo Sangre: ";
                        mensaje += tipo;
                        mensaje += ", ";
                        mensaje += "SS: ";
                        mensaje += tx_id.toString();
                        mensaje += ", ";
                        mensaje += "Enfermedad: "+tx_enfermedad.toString();
                        enviarMensajes(numero,mensaje);

                    }else if(datos.getId_permiso() == 3 && datos.getId_estado()!=1){
                        mensaje = "";
                        mensaje += "Información médica: ";
                        mensaje += "\n";
                        mensaje += "No compartida";
                        enviarMensajes(numero,mensaje);

                    }

                    if(datos.getId_permiso() == 4 && datos.getId_estado()==1) {
                        mensaje ="";
                        mensaje += "Información dispositivo:";
                        enviarMensajes(numero,mensaje);
                        mensaje = "";
                        mensaje += "Latitud: ";
                        mensaje += latitud.toString();
                        enviarMensajes(numero,mensaje);
                        mensaje = "";
                        mensaje += "Longitud: ";
                        mensaje += longitud.toString();
                        enviarMensajes(numero,mensaje);


                    }else if(datos.getId_permiso() == 4 && datos.getId_estado()!=1){
                        mensaje +="";
                        mensaje += "Dispositivo:";
                        mensaje +="\n";
                        mensaje += "No compartida";
                        enviarMensajes(numero,mensaje);


                    }


                }



            }

            @Override
            public void onFailure(Call<List<DatosConPermiso_F_IUPP1A>> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });



    }

    private void checkSMSStatePermission() {
        int permissionCheck = ContextCompat.checkSelfPermission(
                getContext(), Manifest.permission.SEND_SMS);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            Log.i("Mensaje", "No se tiene permiso para enviar SMS.");
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.SEND_SMS}, 225);
        } else {
            Log.i("Mensaje", "Se tiene permiso para enviar SMS!");
        }
    }

    private void enviarMensajes(String numero, String mensaje) {

        try {

            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(numero,null,mensaje,null,null);
            Log.d(TAG, "Send SMS: "+numero+ "\n"+"Mensaje: "+mensaje);
            //Toast.makeText(getContext(), "Mensajes Enviados", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            //Toast.makeText(getContext(), "Mensajes NO enviados, datos incorrectos", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }



    /////////////////




    @Override
    public void onResume() {
        super.onResume();

        sm = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> sensors = sm.getSensorList(Sensor.TYPE_ACCELEROMETER);
        if(sensors.size() >0){

            sm.registerListener(this,sensors.get(0),SensorManager.SENSOR_DELAY_GAME);
        }
        sm1 = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> sensors1 = sm1.getSensorList(Sensor.TYPE_GYROSCOPE);
        if(sensors1.size() >0){

            sm1.registerListener(this,sensors1.get(0),SensorManager.SENSOR_DELAY_GAME);
        }


    }



        @Override
        public void onPause() {
            super.onPause();
            sm.unregisterListener(this);
            sm = null;
            sm1.unregisterListener(this);
            sm1 = null;

        }




    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        synchronized (this){
            switch (sensorEvent.sensor.getType()){
                case Sensor.TYPE_ACCELEROMETER:
                    Float Ax = sensorEvent.values[SensorManager.DATA_X];
                    Float Ay = sensorEvent.values[SensorManager.DATA_Y];
                    Float Az = sensorEvent.values[SensorManager.DATA_Z];


                    break;

                case Sensor.TYPE_GYROSCOPE:
                    Float Gx = sensorEvent.values[SensorManager.DATA_X];
                    Log.d(TAG, "Giroscopio Gx :"+Gx);
                    Float Gy = sensorEvent.values[SensorManager.DATA_Y];
                    Log.d(TAG, "Giroscopio Gy :"+Gy);
                    Float Gz = sensorEvent.values[SensorManager.DATA_Z];
                    Log.d(TAG, "Giroscopio Gz :"+Gz);

                    if(Gx > 10 || Gx < -5 && Gy > 10 || Gy <-5 && Gz > 3 || Gz < -3  )
                    {
                        if(flag == false)
                        {
                            flag = true;
                            NotificaAutomatica(1);
                            Log.d(TAG, "Notificación Giroscopio Gx :"+Gx);
                        }
                    }



                    break;
            }

        }


    }



    public void NotificaAutomatica(final Integer Tipo){

            AlertDialog.Builder alerta = new AlertDialog.Builder(getContext());

            alerta.setMessage("Se enviará una alerta a tus contactos.\nSi no quieres que se envie una notificación automática cancela")
                    .setCancelable(true)
                    .setPositiveButton("Enviar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            checkSMSStatePermission();



                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            String fecha = sdf.format(new Date());


                            Log.d(TAG, "id_bitacora: "+id_bitacora);
                            Log.d(TAG, "id_vehiculo: "+id_vehiculo);
                            Log.d(TAG, "fehca y hora: "+fecha);
                            Log.d(TAG, "latitud" +latitud);
                            Log.d(TAG, "latitud" +longitud);

                            CrearNotificacion(1,id_vehiculo,id_bitacora,fecha,latitud,longitud,2);

                            ObtenerNumeros(1,id_vehiculo);
                            Toast.makeText(getContext(), "Alerta enviada", Toast.LENGTH_SHORT).show();
                            flag=false;
                        }
                    })
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            flag=false;

                        }

                    });



            if(spinnerPalcas.getSelectedItem().toString().equals("Selecciona")){
                Toast.makeText(this.getActivity(), "Selecciona un Vehículo", Toast.LENGTH_SHORT).show();
                flag = false;
            }else {
                final AlertDialog titulo = alerta.create();
                titulo.setTitle("¡Alerta! Notificación Automática");
                titulo.show();
                ObtenerBitacora(1);
                final Handler handler  = new Handler();
                final Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        if (titulo.isShowing()) {
                            titulo.dismiss();

                        }

                    }
                };

                titulo.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        if(flag == true){
                            handler.removeCallbacks(runnable);
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            String fecha = sdf.format(new Date());


                            Log.d(TAG, "id_bitacora: "+id_bitacora);
                            Log.d(TAG, "id_vehiculo: "+id_vehiculo);
                            Log.d(TAG, "fehca y hora: "+fecha);
                            Log.d(TAG, "latitud" +latitud);
                            Log.d(TAG, "latitud" +longitud);

                            CrearNotificacion(1,id_vehiculo,id_bitacora,fecha,latitud,longitud,Tipo);

                            ObtenerNumeros(1,id_vehiculo);
                            Toast.makeText(getActivity(), "Alerta enviada", Toast.LENGTH_SHORT).show();
                            flag=false;
                        }else{

                        }
                    }
                });

                handler.postDelayed(runnable, 10000);
            }



    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }



    }
