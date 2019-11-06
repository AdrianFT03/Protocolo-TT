package com.example.in_help.ui;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.in_help.R;

import java.util.ArrayList;

public class AdaptadorListaDeDispositivos extends ArrayAdapter<BluetoothDevice> {

    private LayoutInflater mLayoutInflater;
    private ArrayList<BluetoothDevice> mDevices;
    private int mViewResourceId;

   public AdaptadorListaDeDispositivos(Context context, int tvresourceId, ArrayList<BluetoothDevice> devices) {
        super(context, tvresourceId,devices);
        this.mDevices = devices;
        mLayoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId = tvresourceId;
    }
    /*
    public AdaptadorListaDeDispositivos(Context context, int vista_dipositivos_bt, ArrayList<BluetoothDevice> mBTDevices) {
        super(context,vista_dipositivos_bt,mBTDevices);
        this.mDevices = mDevices;
        mLayoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        this.mViewResourceId = mViewResourceId;

    }*/

    public View getView (int position, View convertView , ViewGroup parent){
        convertView = mLayoutInflater.inflate(mViewResourceId,null);
        BluetoothDevice device = mDevices.get(position);
        if(device != null){
            TextView deviceName = (TextView) convertView.findViewById(R.id.tvDeviceName);
            TextView deviceAddress = (TextView) convertView.findViewById(R.id.tvDeviceAddres);
            if (deviceName != null){
                deviceName.setText(device.getName());
            }
            if(deviceAddress != null){
                deviceAddress.setText(device.getAddress());
            }
        }
        return convertView;
   }

}
