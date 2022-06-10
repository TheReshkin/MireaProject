package ru.mirea.tereshkin.mireaproject;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class SensorFragment extends Fragment implements SensorEventListener {
    private TextView distTextView;
    private TextView accelerometerTextView;
    private TextView lightTextView;
    private SensorManager sensorManager;
    private Sensor distSensor;
    private Sensor accelerometerSensor;
    private Sensor lightSensor;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SensorFragment() {
        // Required empty public constructor
    }

    public static SensorFragment newInstance(String param1, String param2) {
        SensorFragment fragment = new SensorFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.l_sensor_fragment, container, false);
        distTextView = v.findViewById(R.id.dist);
        accelerometerTextView = v.findViewById(R.id.accelerometer);
        lightTextView = v.findViewById(R.id.light);
        sensorManager = (SensorManager)getActivity().getSystemService(Context.SENSOR_SERVICE);
        distSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        return v;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        if (sensorEvent.sensor.getType() == Sensor.TYPE_PROXIMITY){
            String value = String.valueOf(sensorEvent.values[0]);
            distTextView.setText(value);
        }
        else if(sensorEvent.sensor.getType() == Sensor.TYPE_LIGHT){
            String value = String.valueOf(sensorEvent.values[0]);
            lightTextView.setText(value);
        }
        else if(sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            String value = String.valueOf(sensorEvent.values[0]);
            accelerometerTextView.setText(value);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(this, distSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}
