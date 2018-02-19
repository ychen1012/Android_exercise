package com.example.yangchen.test;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.baidu.mapapi.map.MyLocationConfiguration;

/**
 * Created by yangchen on 2018/2/17.
 */

public class MyOrientationListen implements SensorEventListener {
    private SensorManager msensorManager;
    private Context mcontext;
    private Sensor mSensor;
    private float lastX;

    public MyOrientationListen(Context context){
        this.mcontext=context;
    }
    public void start(){
    msensorManager= (SensorManager) mcontext.getSystemService(Context.SENSOR_SERVICE);
    if(msensorManager!=null){
        //获得方向传感器；
       mSensor= msensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
    }
    if (mSensor!=null){
        msensorManager.registerListener(this,mSensor,SensorManager.SENSOR_DELAY_UI);
    }
    }
    public void stop(){
        msensorManager.unregisterListener(this);

    }

    public void setMsensorManager(SensorManager msensorManager) {
        this.msensorManager = msensorManager;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType()==Sensor.TYPE_ORIENTATION){
            float x =sensorEvent.values[SensorManager.DATA_X];
            if(Math.abs(x-lastX)>1.0){
                if(mOn!=null){
                    mOn.onOrientationChanged(x);
                }
            }
            lastX=x;
        }

    }
    private OnOrientationListener mOn;

    public void setmOn(OnOrientationListener mOn) {
        this.mOn = mOn;
    }

    public interface OnOrientationListener{
        void onOrientationChanged(float x);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
