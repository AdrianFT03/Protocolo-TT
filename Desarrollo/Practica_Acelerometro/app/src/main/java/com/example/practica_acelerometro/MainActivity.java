package com.example.practica_acelerometro;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import android.os.CountDownTimer;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Button;

import android.widget.TextView;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

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

    public  void  ejemplo(){
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Notification Title")
                .setMessage("Do you really want to delete the file?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO: Add positive button action code here
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .create();
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {

            private static final int AUTO_DISMISS_MILLIS = 6000;
            @Override
            public void onShow(final DialogInterface dialog) {
                final Button defaultButton = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_NEGATIVE);
                final CharSequence negativeButtonText = defaultButton.getText();
                new CountDownTimer(AUTO_DISMISS_MILLIS, 100) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        defaultButton.setText(String.format(
                                Locale.getDefault(), "%s (%d)",
                                negativeButtonText,
                                TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) + 1 //add one so it never displays zero
                        ));
                    }
                    @Override
                    public void onFinish() {
                        if (((AlertDialog) dialog).isShowing()) {
                            dialog.dismiss();
                        }
                    }
                }.start();
            }
        });
        dialog.show();
    }

}
