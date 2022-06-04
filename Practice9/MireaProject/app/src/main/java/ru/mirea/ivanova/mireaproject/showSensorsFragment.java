package ru.mirea.ivanova.mireaproject;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class showSensorsFragment extends Fragment implements SensorEventListener {
    private SensorManager sensorManager;
    private TextView lightSensorValue;
    private TextView magneticText;
    private TextView azimuthTextView;
    private TextView pitchTextView;
    private TextView rollTextView;
    private Sensor lightSensor;
    private Sensor magneticSensor;
    private Sensor accelerometerSensor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_sensors, container, false);

        lightSensorValue = view.findViewById(R.id.light_sensor);
        // magnetic
        magneticText = view.findViewById(R.id.magnetic_sensor);
        //акселерометр
        azimuthTextView = view.findViewById(R.id.acceler_x);
        pitchTextView = view.findViewById(R.id.acceler_y);
        rollTextView = view.findViewById(R.id.acceler_z);

        if (getActivity() != null) {
            sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);

            lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
            magneticSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
            accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        }
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, magneticSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            float valueMagnetic = event.values[0];
            magneticText.setText("Магнитное поле: " + valueMagnetic);
        } else if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float valueAzimuth = event.values[0];
            float valuePitch = event.values[1];
            float valueRoll = event.values[2];
            azimuthTextView.setText("Azimuth: " + valueAzimuth);
            pitchTextView.setText("Pitch: " + valuePitch);
            rollTextView.setText("Roll: " + valueRoll);
        } else if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
            String newValue = "Light: " + event.values[0] + " лк";
            lightSensorValue.setText(newValue);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}