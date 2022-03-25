package com.jsh.kr.alltest.model;


import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.TriggerEvent;
import android.hardware.TriggerEventListener;
import android.os.Build;

import java.util.List;

public class SensorTestManager {

   private SensorManager mSensorManager;

   private Sensor motionSensor;

   private OnTriggerListener triggerListener;

   public void init(Context context) {
      mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);

      if (mSensorManager != null) {
         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            motionSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_MOTION_DETECT);
         }
      }

   }

   public boolean isEnableMotion() {
      return motionSensor != null;
   }

   public List<Sensor> getSensorList() {
      if (mSensorManager != null) {
         return mSensorManager.getSensorList(Sensor.TYPE_ALL);
      }
      return null;
   }

   public void requestMotionTrigger() {
      if (motionSensor != null) {
         mSensorManager.requestTriggerSensor(triggerEventListener, motionSensor);
      }
   }

   public void setTriggerListener(OnTriggerListener triggerListener) {
      this.triggerListener = triggerListener;
   }

   private TriggerEventListener triggerEventListener = new TriggerEventListener() {
      @Override
      public void onTrigger(TriggerEvent triggerEvent) {
         switch (triggerEvent.sensor.getType()) {
            case Sensor.TYPE_MOTION_DETECT:
               // only value[0] -> 0 or 1
               if (triggerListener != null) {
                  triggerListener.ONMotionTrigger(triggerEvent.values[0] == 1);
               }
               break;
         }
      }
   };

   public interface OnTriggerListener {
      void ONMotionTrigger(boolean isMotionDetect);
   }

}

