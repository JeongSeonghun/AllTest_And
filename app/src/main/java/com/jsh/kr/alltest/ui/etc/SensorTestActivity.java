package com.jsh.kr.alltest.ui.etc;


import android.os.Bundle;

import com.jsh.kr.alltest.R;
import com.jsh.kr.alltest.databinding.ActivitySensorTestBinding;
import com.jsh.kr.alltest.model.SensorTestManager;
import com.jsh.kr.alltest.ui.BaseActivity;
import com.jsh.kr.alltest.util.LogUtil;
import com.jsh.kr.alltest.util.TestTextUtil;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

public class SensorTestActivity extends BaseActivity {

   private ActivitySensorTestBinding binding;

   private SensorTestManager manager;

   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      binding = DataBindingUtil.setContentView(this, R.layout.activity_sensor_test);

      init();
   }

   private void init() {
      manager = new SensorTestManager();
      manager.init(this);
      manager.setTriggerListener(triggerListener);

      setStatus();

      binding.btnMotion.setOnClickListener(v -> {
         manager.requestMotionTrigger();
      });
   }

   private void setStatus() {
      if (manager.isEnableMotion()) {
         binding.tvSensorStatus.setText("enable");
      } else {
         binding.tvSensorStatus.setText("disable");
      }
   }

   private SensorTestManager.OnTriggerListener triggerListener = new SensorTestManager.OnTriggerListener() {
      @Override
      public void ONMotionTrigger(boolean isMotionDetect) {
         LogUtil.d("test", "motion detect : "+isMotionDetect);
         if (isMotionDetect) {
            binding.tvMotionStatus.setText(TestTextUtil.makeCurrentTime() +"detect");
         } else {
            binding.tvMotionStatus.setText(TestTextUtil.makeCurrentTime() +"dis...");
         }
      }
   };
}

