package com.example.practica_acelerometro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private static final String TAG = "Log";
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
    }



    @Override
    protected void onResume() {
        super.onResume();
        SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> sensors = sm.getSensorList(Sensor.TYPE_ACCELEROMETER);
        if(sensors.size() >0){

            sm.registerListener(this,sensors.get(0),SensorManager.SENSOR_DELAY_GAME);
        }
    }

    @Override
    protected void onPause() {

        SensorManager mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensorManager.unregisterListener(this,mAccelerometer);
        super.onPause();
    }
    @Override
    protected void onStop() {
        SensorManager mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensorManager.unregisterListener(this,mAccelerometer);
        super.onStop();
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {



        this.x.setText("X = "+sensorEvent.values[SensorManager.DATA_X]);
        Float x = sensorEvent.values[SensorManager.DATA_X];
        //Log.d(TAG, "Valor x :"+x);
        if(x>10){
            Toast.makeText(this, "X mayor a 10", Toast.LENGTH_SHORT).show();
            onStop();
        }
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
