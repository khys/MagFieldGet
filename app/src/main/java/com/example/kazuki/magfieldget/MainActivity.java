package com.example.kazuki.magfieldget;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor sensor;

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("X方向:" + sensorEvent.values[0] + "μT\n");
        stringBuilder.append("Y方向:" + sensorEvent.values[1] + "μT\n");
        stringBuilder.append("Z方向:" + sensorEvent.values[2] + "μT\n");
        TextView textView = findViewById(R.id.txt01);
        textView.setText(stringBuilder.toString());
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
