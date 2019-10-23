package com.example.practica_acelerometro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private TextView x,y,z;
    private Sensor mAccelerometer;
    private Sensor sensor,giroscopio;
    private SensorManager sm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        x =  (TextView) findViewById(R.id.x);
        y =  (TextView) findViewById(R.id.y);
        z =  (TextView) findViewById(R.id.z);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT );


        //sensor = sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        //giroscopio = sm.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        //sm.registerListener(this,giroscopio,SensorManager.SENSOR_DELAY_NORMAL);


    }

    protected void onResume() {
        super.onResume();
        SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> sensors = sm.getSensorList(Sensor.TYPE_ACCELEROMETER);
        if(sensors.size() >0){
            sm.registerListener(this,sensors.get(0),SensorManager.SENSOR_DELAY_GAME);
        }
    }


    protected void onPause() {

        SensorManager mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensorManager.unregisterListener(this,mAccelerometer);
        super.onPause();
    }

    protected void onStop() {
        SensorManager mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensorManager.unregisterListener(this,mAccelerometer);
        super.onStop();
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {


        this.x.setText("X = "+sensorEvent.values[SensorManager.DATA_X]);
        this.y.setText("Y = "+sensorEvent.values[SensorManager.DATA_Y]);
        this.z.setText("Z = "+sensorEvent.values[SensorManager.DATA_Z]);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
