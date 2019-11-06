package com.example.in_help.ui;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.in_help.R;

import java.util.ArrayList;

public class Bluetooth_MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private static final String TAG = "Main_Activity";
    BluetoothAdapter mBluetoothAdapter;
    Button VisibleInvisible;
    public ArrayList<BluetoothDevice> mBTDevices = new ArrayList<>();
    public AdaptadorListaDeDispositivos mAdaptadorListaDispositivos;
    ListView DispositivosEncontrados;



    // Create a BroadcastReceiver for ACTION_FOUND. Enceder/Apagar
    private final BroadcastReceiver mBroadcastReceiver1 = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(mBluetoothAdapter.ACTION_STATE_CHANGED)) {
                final int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE,mBluetoothAdapter.ERROR);
                switch (state){
                    case BluetoothAdapter.STATE_OFF:
                        Log.d(TAG, "onReceive: Estado Apagado");
                        break;
                    case BluetoothAdapter.STATE_TURNING_OFF:
                        Log.d(TAG, "onReceive: Estado Apagadose");
                        break;
                    case BluetoothAdapter.STATE_ON:
                        Log.d(TAG, "onReceive: Estado Encendido");
                        break;
                    case BluetoothAdapter.STATE_TURNING_ON:
                        Log.d(TAG, "onReceive: Estado Encendiendose");
                        break;


                }



            }}
    };
    // Create a BroadcastReceive2 Visible
    private final BroadcastReceiver mBroadcastReceiver2 = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(BluetoothAdapter.ACTION_SCAN_MODE_CHANGED)) {
                final int mode = intent.getIntExtra(BluetoothAdapter.EXTRA_SCAN_MODE, BluetoothAdapter.ERROR);
                switch (mode){
                    case BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE:
                        Log.d(TAG, "BroadcastReceiver2: Visibilidad activido");
                        break;
                    case BluetoothAdapter.SCAN_MODE_CONNECTABLE:
                        Log.d(TAG, "BroadcastReceiver 2: Visible Listo para recibir conexiones");
                        break;
                    case BluetoothAdapter.SCAN_MODE_NONE:
                        Log.d(TAG, "BroadcastReceiver 2: Deshabilitado NO recibe conexiones");
                        break;
                    case BluetoothAdapter.STATE_CONNECTING:
                        Log.d(TAG, "BroadcastReceiver 2: Conectadose");
                        break;
                    case BluetoothAdapter.STATE_CONNECTED:
                        Log.d(TAG, "BroadcastReceiver 2: Conectado");
                        break;


                }



            }}
    };

    // Create a BroadcastReceive3 Buscar
    private BroadcastReceiver mBroadcastReceiver3 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            Log.d(TAG, "onReceive: Action Found");
            if (action.equals(BluetoothDevice.ACTION_FOUND)){
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                mBTDevices.add(device);
                Log.d(TAG, "onReceive: "+device.getName() + ": "+ device.getAddress());
                mAdaptadorListaDispositivos = new AdaptadorListaDeDispositivos(context, R.layout.vista_dipositivos_bt,mBTDevices);
                DispositivosEncontrados.setAdapter(mAdaptadorListaDispositivos);
            }
        }

    };

    // Create a BroadcastReceive4 Conectar

    private BroadcastReceiver mBroadcastReceiver4 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            Log.d(TAG, "onReceive: Action Found");
            if (action.equals(BluetoothDevice.ACTION_BOND_STATE_CHANGED)){
                BluetoothDevice mDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                // 3 Casos para conectar
                // 1 Already

                if(mDevice.getBondState() == BluetoothDevice.BOND_BONDED){
                    Log.d(TAG, "BroadcastReceiver: BOND_BONDED  ");

                }

                // Creating a bond

                if(mDevice.getBondState() == BluetoothDevice.BOND_BONDING){
                    Log.d(TAG, "BroadcastReceiver: BOND_BONDING  ");


                }

                // Breaking a bond

                if (mDevice.getBondState()== BluetoothDevice.BOND_NONE){
                    Log.d(TAG, "BroadcastReceiver: BOND_NONE  ");


                }
            }
        }

    };




    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: called");
        super.onDestroy();

        //unregisterReceiver(mBroadcastReceiver1);
        //unregisterReceiver(mBroadcastReceiver2);
        //unregisterReceiver(mBroadcastReceiver3);
        //unregisterReceiver(mBroadcastReceiver4);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bluetooth_main);
        Button btnONOFF = (Button) findViewById(R.id.buttonON_OFF);
        Button VisibleInvisible = (Button) findViewById(R.id.Discoverability);
        DispositivosEncontrados = (ListView) findViewById(R.id.DispositivosEncontrados);
        mBTDevices = new ArrayList<>();
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
        registerReceiver(mBroadcastReceiver4,filter);
        DispositivosEncontrados.setOnItemClickListener(Bluetooth_MainActivity.this);

        setupActionBar();

        btnONOFF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Activando/Desactivando");
                ActivadoDesactivadoBT();            }
        });


    }

    public void ActivadoDesactivadoBT() {
        if (mBluetoothAdapter == null) {
            Log.d(TAG, "ActivadoDesactivadoBT: No cuenta con Bluetooth");
        }
        if (!mBluetoothAdapter.isEnabled()) {
            Log.d(TAG, "ActivadoDesactivadoBT: Activando BT.");
            Intent enableBTIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivity(enableBTIntent);

            IntentFilter BTIntent = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
            registerReceiver(mBroadcastReceiver1, BTIntent);
        }
        if (mBluetoothAdapter.isEnabled()) {
            Log.d(TAG, "ActivadoDesactivadoBT: Desactivando BT");
            mBluetoothAdapter.disable();
            IntentFilter BTIntent = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
            registerReceiver(mBroadcastReceiver1, BTIntent);

        }
    }

    public void BtnEnableDisable_Discoverable(View view) {
        Log.d(TAG, "BtnEnableDisable_Discoverable: Haciendose Reconocible por 300 segundos");
        Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION,300);
        startActivity(discoverableIntent);

        IntentFilter intentFilter = new IntentFilter(mBluetoothAdapter.ACTION_SCAN_MODE_CHANGED);
        registerReceiver(mBroadcastReceiver2,intentFilter);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void BtnDiscover(View view) {
        Log.d(TAG, "BtnDiscover: Observa los dispositivos Disponibles");
        if (mBluetoothAdapter.isDiscovering()){
            mBluetoothAdapter.cancelDiscovery();
            Log.d(TAG, "BtnDiscover: Cancelando Busqueda");
            // Checa Permisos de BT en manifest
            CheckBTPermissions();

            mBluetoothAdapter.startDiscovery();
            IntentFilter intentFilter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
            registerReceiver(mBroadcastReceiver3,intentFilter);
        }
        if (!mBluetoothAdapter.isDiscovering()){
            // Checa Permisos de BT en manifest
            CheckBTPermissions();
            mBluetoothAdapter.startDiscovery();
            IntentFilter intentFilter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
            registerReceiver(mBroadcastReceiver3,intentFilter);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void CheckBTPermissions(){
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.ICE_CREAM_SANDWICH){
            int permissionCheck = this.checkSelfPermission("Manifest.permission.ACCESS_FINE_LOCATION");
            permissionCheck += this.checkSelfPermission("Manifest.permission.ACCESS_COARSE_LOCATION");
            if(permissionCheck != 0) {
                this.requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},1001);
            }
            else {
                Log.d(TAG, "CheckBTPermissions: No necesita checar permisos el SO es menor al permitido");
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        // Cancelar busqueda
        mBluetoothAdapter.cancelDiscovery();
        Log.d(TAG, "onItemClick: Has seleccionado un dispositivo.");
        String deviceName = mBTDevices.get(i).getName();
        String deviceAdress = mBTDevices.get(i).getAddress();
        Log.d(TAG, "onItemClick: deviceName = "+deviceName);
        Log.d(TAG, "onItemClick: deviceAdress = "+deviceAdress);

        //Create Bond
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR2){
            Log.d(TAG, "onItemClick: Intentado Conectar con dispositivo: "+deviceName);
            mBTDevices.get(i).createBond();
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private void setupActionBar(){
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("");
        }
    }
}



